/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import com.nike.cards.core.DataAccessException;
import com.nike.cards.core.model.Deck;
import com.nike.cards.core.service.DeckDataRepository;

/**
 * In memory implementation of {@link DeckDataRepository}.
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
public class InMemoryDeckDataRepository implements DeckDataRepository {

    /** The data map. */
    private ConcurrentMap<String, Deck> dataMap;

    /**
     * Instantiates a new in memory deck data repository.
     *
     * @param dataMap the data map
     */
    public InMemoryDeckDataRepository(ConcurrentMap<String, Deck> dataMap) {
        this.dataMap = dataMap;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createDeck(@NotNull Deck deck) throws DataAccessException {
        if (StringUtils.isBlank(deck.getDeckName())) {
            throw new IllegalArgumentException("Deck name cannot be null or empty");
        }
        dataMap.putIfAbsent(deck.getDeckName().toLowerCase(), deck);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Deck> getDeck(@NotNull @NotBlank final String deckName) throws DataAccessException {
        return dataMap.entrySet().stream().filter((e) -> e.getKey().equals(deckName.toLowerCase()))
            .map(Map.Entry::getValue).findFirst();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDeck(@NotNull @NotBlank String deckName) throws DataAccessException {
        dataMap.remove(deckName.toLowerCase());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Deck> getAllDeck() throws DataAccessException {
        return (CollectionUtils.isEmpty(dataMap.values()) ? Collections.emptyList() : dataMap.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDeck(@NotNull @NotBlank String deckName, @NotNull Deck deck)
        throws DataAccessException {
        dataMap.replace(deckName, deck);
    }

}
