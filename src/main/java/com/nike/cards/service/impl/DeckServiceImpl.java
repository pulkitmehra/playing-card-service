/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import com.nike.cards.core.DataAccessException;
import com.nike.cards.core.ResourceAlreadyExistsException;
import com.nike.cards.core.ResourceNotFoundException;
import com.nike.cards.core.model.Card;
import com.nike.cards.core.model.Deck;
import com.nike.cards.core.service.DeckDataRepository;
import com.nike.cards.core.service.DeckService;
import com.nike.cards.core.service.DeckServiceException;
import com.nike.cards.core.shuffle.Shuffle;


/**
 * Implementation of {@link DeckService}
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
public class DeckServiceImpl implements DeckService {

    /** The card shuffler. */
    private Shuffle<Card> cardShuffler;

    /** The deck data repository. */
    private DeckDataRepository deckDataRepository;

    /**
     * Instantiates a new deck service impl.
     *
     * @param cardShuffler the card shuffler
     * @param deckDataRepository the deck data repository
     */
    public DeckServiceImpl(Shuffle<Card> cardShuffler, DeckDataRepository deckDataRepository) {
        this.cardShuffler = cardShuffler;
        this.deckDataRepository = deckDataRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck createDeck(@NotNull @NotBlank String deckName)
        throws DeckServiceException, ResourceAlreadyExistsException {

        if (getDeckByName(deckName).isPresent()) {
            throw new ResourceAlreadyExistsException("Deck already exist " + deckName);
        }

        Deck deck = Deck.getInstance();
        deck.setDeckName(deckName);

        try {
            deckDataRepository.createDeck(deck);
        }
        catch (DataAccessException e) {
            throw new DeckServiceException("Error occured in creating deck with name " + deckName);
        }

        cardShuffler.shuffle(Arrays.asList(deck.getCards()));

        return deck;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Deck> getAllDecks() throws DeckServiceException {
        try {
            return deckDataRepository.getAllDeck();
        }
        catch (DataAccessException e) {
            throw new DeckServiceException("Error occured while fetching all decks");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck getDeck(@NotBlank @NotNull String deckName)
        throws DeckServiceException, ResourceNotFoundException {
        Optional<Deck> deck = getDeckByName(deckName);
        if (!deck.isPresent()) {
            throw new ResourceNotFoundException("No deck found for given name " + deckName);
        }
        return deck.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(@NotBlank @NotNull String deckName)
        throws ResourceNotFoundException, DeckServiceException {
        validateDeck(deckName);
        try {
            deckDataRepository.removeDeck(deckName);
        }
        catch (DataAccessException e) {
            throw new DeckServiceException("Error occured in removing deck with name " + deckName);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck shuffleDeck(@NotBlank @NotNull String deckName)
        throws ResourceNotFoundException, DeckServiceException {
        Deck deck = getDeck(deckName);
        cardShuffler.shuffle(Arrays.asList(deck.getCards()));
        try {
            deckDataRepository.updateDeck(deckName, deck);
        }
        catch (DataAccessException e) {
            throw new DeckServiceException("Error occured while updating deck " + deckName, e);
        }
        return deck;
    }

    /**
     * Validate deck.
     *
     * @param deckName the deck name
     * @throws DeckServiceException the deck service exception
     * @throws ResourceNotFoundException the resource not found exception
     */
    private void validateDeck(String deckName) throws DeckServiceException, ResourceNotFoundException {
        if (!getDeckByName(deckName).isPresent()) {
            throw new ResourceNotFoundException("No deck found for given name " + deckName);
        }
    }

    /**
     * Gets the deck by name.
     *
     * @param deckName the deck name
     * @return the deck by name
     * @throws DeckServiceException the deck service exception
     */
    private Optional<Deck> getDeckByName(String deckName) throws DeckServiceException {
        try {
            return deckDataRepository.getDeck(deckName);
        }
        catch (DataAccessException e) {
            throw new DeckServiceException("Error in fetching Deck by name " + deckName, e);
        }
    }

}
