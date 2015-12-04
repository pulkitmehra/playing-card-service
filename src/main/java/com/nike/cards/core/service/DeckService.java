/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.service;

import java.util.Collection;
import com.nike.cards.core.ResourceAlreadyExistsException;
import com.nike.cards.core.ResourceNotFoundException;
import com.nike.cards.core.model.Deck;

/**
 * Deck service operations.
 *
 * @author pulkit.mehra
 * Created: Dec 3, 2015
 */
public interface DeckService {

    /**
     * Creates the deck.
     *
     * @param deckName the deck name
     * @return the deck
     * @throws DeckServiceException the deck service exception
     * @throws ResourceAlreadyExistsException the resource already exists exception
     */
    public Deck createDeck(String deckName) throws DeckServiceException, ResourceAlreadyExistsException;

    /**
     * Gets the all decks.
     *
     * @return the all decks
     * @throws DeckServiceException the deck service exception
     */
    public Collection<Deck> getAllDecks() throws DeckServiceException;

    /**
     * Gets the deck.
     *
     * @param deckName the deck name
     * @return the deck
     * @throws DeckServiceException the deck service exception
     * @throws ResourceNotFoundException the resource not found exception
     */
    public Deck getDeck(String deckName) throws DeckServiceException, ResourceNotFoundException;

    /**
     * Removes the.
     *
     * @param deckName the deck name
     * @throws ResourceNotFoundException the resource not found exception
     * @throws DeckServiceException the deck service exception
     */
    public void remove(String deckName) throws ResourceNotFoundException, DeckServiceException;

    /**
     * Shuffle deck.
     *
     * @param deckName the deck name
     * @return the deck
     * @throws ResourceNotFoundException the resource not found exception
     * @throws DeckServiceException the deck service exception
     */
    public Deck shuffleDeck(String deckName) throws ResourceNotFoundException, DeckServiceException;

}
