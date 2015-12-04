/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.service;

import java.util.Collection;
import java.util.Optional;
import com.nike.cards.core.DataAccessException;
import com.nike.cards.core.model.Deck;


/**
 * Deck data persistence repository API.
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
public interface DeckDataRepository {

    /**
     * Creates the deck.
     *
     * @param deck the deck
     * @throws DataAccessException the data access exception
     */
    public void createDeck(Deck deck) throws DataAccessException;

    /**
     * Gets the deck.
     *
     * @param deckName the deck name
     * @return the deck
     * @throws DataAccessException the data access exception
     */
    public Optional<Deck> getDeck(String deckName) throws DataAccessException;

    /**
     * Removes the deck.
     *
     * @param deckName the deck name
     * @throws DataAccessException the data access exception
     */
    public void removeDeck(String deckName) throws DataAccessException;

    /**
     * Gets the all deck.
     *
     * @return the all deck
     * @throws DataAccessException the data access exception
     */
    public Collection<Deck> getAllDeck() throws DataAccessException;

    /**
     * Update deck.
     *
     * @param deckName the deck name
     * @param deck the deck
     * @throws DataAccessException the data access exception
     */
    public void updateDeck(String deckName, Deck deck) throws DataAccessException;

}
