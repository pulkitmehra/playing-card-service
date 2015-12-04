/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.web.adapter;

import com.nike.cards.core.ResourceAlreadyExistsException;
import com.nike.cards.core.ResourceNotFoundException;
import com.nike.cards.core.model.Deck;
import com.nike.cards.core.service.DeckService;
import com.nike.cards.core.service.DeckServiceException;


/**
 * Web adapter for exposing {@link DeckService} to World.
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
public interface DeckWebAdapter {

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
     * Gets the all decks.
     *
     * @return the all decks
     * @throws DeckServiceException the deck service exception
     */
    public DeckNamesWrapper getAllDecks() throws DeckServiceException;

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
     * Removes the deck.
     *
     * @param deckName the deck name
     * @throws DeckServiceException the deck service exception
     * @throws ResourceNotFoundException the resource not found exception
     */
    public void removeDeck(String deckName) throws DeckServiceException, ResourceNotFoundException;

    /**
     * Shuffle.
     *
     * @param deckName the deck name
     * @return the deck
     * @throws DeckServiceException the deck service exception
     * @throws ResourceNotFoundException the resource not found exception
     */
    public Deck shuffle(String deckName) throws DeckServiceException, ResourceNotFoundException;

}
