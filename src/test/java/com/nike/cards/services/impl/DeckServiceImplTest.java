/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.services.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.nike.cards.core.ResourceAlreadyExistsException;
import com.nike.cards.core.ResourceNotFoundException;
import com.nike.cards.core.model.Card;
import com.nike.cards.core.model.Deck;
import com.nike.cards.core.service.DeckDataRepository;
import com.nike.cards.core.service.DeckService;
import com.nike.cards.core.shuffle.RandomizedShuffle;
import com.nike.cards.core.shuffle.Shuffle;
import com.nike.cards.service.impl.DeckServiceImpl;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
public class DeckServiceImplTest {

    @Mock
    private DeckDataRepository deckDataRepository;

    @Mock
    private Shuffle<Card> cardShuffler;

    private DeckService deckService;

    @Before
    public void setUpTest() {
        cardShuffler = new RandomizedShuffle<>();
        MockitoAnnotations.initMocks(this);
        deckService = new DeckServiceImpl(cardShuffler, deckDataRepository);
    }

    @Test
    public void shuffleDeckTest() throws Exception {
        String deckName = "Solitaire";
        Deck deck = Deck.getInstance();

        when(deckDataRepository.getDeck(deckName)).thenReturn(Optional.of(deck));
        doNothing().when(deckDataRepository).updateDeck(deckName, deck);

        deckService.shuffleDeck(deckName);

        verify(deckDataRepository).getDeck(deckName);
        verify(deckDataRepository).updateDeck(deckName, deck);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void shuffleDeckInvalidDeckNameTest() throws Exception {
        String deckName = "Invalid Deck Name";
        Deck deck = Deck.getInstance();

        when(deckDataRepository.getDeck(deckName)).thenReturn(Optional.empty());
        deckService.shuffleDeck(deckName);
    }

    @Test
    public void createDeckTest() throws Exception {
        String deckName = "Solitaire";

        when(deckDataRepository.getDeck(deckName)).thenReturn(Optional.empty());
        doNothing().when(deckDataRepository).createDeck(Mockito.any(Deck.class));
        doNothing().when(cardShuffler).shuffle(Mockito.anyListOf(Card.class));

        Deck createdDeck = deckService.createDeck(deckName);
        assertThat(createdDeck.getDeckName(), is(deckName));
        assertThat(createdDeck.getCards().length, is(52));

        verify(deckDataRepository).getDeck(deckName);
        verify(deckDataRepository).createDeck(Mockito.any(Deck.class));
        verify(cardShuffler).shuffle(Mockito.anyListOf(Card.class));
    }

    @Test(expected = ResourceAlreadyExistsException.class)
    public void createDeckResourceAlreadyExistTest() throws Exception {
        String deckName = "Rummy";
        when(deckDataRepository.getDeck(deckName)).thenReturn(Optional.of(Deck.getInstance()));
        deckService.createDeck(deckName);
    }

    @Test
    public void getAllDecksTest() throws Exception {
        List<Deck> deckList = Arrays.asList(Deck.getInstance());
        when(deckDataRepository.getAllDeck()).thenReturn(deckList);

        Collection<Deck> decks = deckService.getAllDecks();
        assertThat(deckList, is(decks));

        verify(deckDataRepository).getAllDeck();
    }

    @Test
    public void getDeckTest() throws Exception {
        String deckName = "Solitaire";
        Deck deck = Deck.getInstance();
        when(deckDataRepository.getDeck(deckName)).thenReturn(Optional.of(deck));

        Deck result = deckService.getDeck(deckName);

        assertThat(deck, is(result));
        verify(deckDataRepository).getDeck(deckName);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getDeckInvalidNameTest() throws Exception {
        String deckName = "Invalid-Name";
        Deck deck = Deck.getInstance();
        when(deckDataRepository.getDeck(deckName)).thenReturn(Optional.empty());
        deckService.getDeck(deckName);
    }

    @Test
    public void removeDeckTest() throws Exception {
        String deckName = "Solitaire";

        Deck instance = Deck.getInstance();
        when(deckDataRepository.getDeck(deckName)).thenReturn(Optional.of(instance));
        doNothing().when(deckDataRepository).removeDeck(deckName);

        deckService.remove(deckName);

        verify(deckDataRepository).getDeck(deckName);
        verify(deckDataRepository).removeDeck(deckName);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void removeDeckInvalidNameTest() throws Exception {
        String deckName = "InvalidName";
        when(deckDataRepository.getDeck(deckName)).thenReturn(Optional.empty());
        deckService.remove(deckName);
    }

}
