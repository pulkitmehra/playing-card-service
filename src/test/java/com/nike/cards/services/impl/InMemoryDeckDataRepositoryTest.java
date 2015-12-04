/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.services.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.junit.Before;
import org.junit.Test;
import com.nike.cards.core.DataAccessException;
import com.nike.cards.core.model.Deck;
import com.nike.cards.service.impl.InMemoryDeckDataRepository;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * 
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
public class InMemoryDeckDataRepositoryTest {

    private static final Deck SOLITAIRE_DECK = Deck.getInstance();
    private static final Deck RUMMY_DECK = Deck.getInstance();
    private static final String RUMMY = "rummy";
    private static final String SOLITAIRE = "solitaire";
    private InMemoryDeckDataRepository inMemoryDeckDataRepository;
    private ConcurrentMap<String, Deck> dataMap;

    @Before
    public void setUp() {
        dataMap = getTestMap();
        inMemoryDeckDataRepository = new InMemoryDeckDataRepository(dataMap);
    }

    @Test
    public void createDeckTest() throws Exception {
        Deck deck = Deck.getInstance();
        deck.setDeckName("a-new-deck");
        inMemoryDeckDataRepository.createDeck(deck);
        assertThat(dataMap.size(), is(3));
    }

    @Test
    public void getDeckTest() throws Exception {
        Optional<Deck> deck = inMemoryDeckDataRepository.getDeck(SOLITAIRE);

        assertTrue(deck.isPresent());
        assertThat(deck.get(), is(SOLITAIRE_DECK));
    }

    @Test
    public void getDeckInvalidNameTest() throws Exception {
        Optional<Deck> deck = inMemoryDeckDataRepository.getDeck("Invalid-Name");
        assertFalse(deck.isPresent());
    }

    @Test
    public void getDeckCaseInsensitiveTest() throws Exception {
        Optional<Deck> deck = inMemoryDeckDataRepository.getDeck("SOLITAIRE");
        assertTrue(deck.isPresent());
        assertThat(deck.get(), is(SOLITAIRE_DECK));
    }

    @Test
    public void removeDeckTest() throws Exception {
        inMemoryDeckDataRepository.removeDeck(SOLITAIRE);
        assertThat(dataMap.size(), is(1));
    }

    @Test
    public void getAllDeckTest() throws DataAccessException {
        Collection<Deck> allDeck = inMemoryDeckDataRepository.getAllDeck();
        assertThat(allDeck.size(), is(2));
    }

    /**
     * @return
     */
    private ConcurrentMap<String, Deck> getTestMap() {
        ConcurrentMap<String, Deck> map = new ConcurrentHashMap<>();
        map.put(SOLITAIRE, SOLITAIRE_DECK);
        map.put(RUMMY, RUMMY_DECK);
        return map;
    }

}
