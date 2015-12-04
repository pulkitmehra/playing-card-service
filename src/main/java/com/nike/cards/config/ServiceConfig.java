/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.config;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.nike.cards.core.model.Card;
import com.nike.cards.core.service.DeckDataRepository;
import com.nike.cards.core.service.DeckService;
import com.nike.cards.core.shuffle.RandomizedShuffle;
import com.nike.cards.core.shuffle.Shuffle;
import com.nike.cards.core.shuffle.SplittingInterleavingShuffle;
import com.nike.cards.service.impl.DeckServiceImpl;
import com.nike.cards.service.impl.InMemoryDeckDataRepository;

/**
 * Service side spring config.
 *
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
@Configuration
public class ServiceConfig {

    /**
     * Deck data repository.
     *
     * @return the deck data repository
     */
    @Bean
    public DeckDataRepository deckDataRepository() {
        return new InMemoryDeckDataRepository(new ConcurrentHashMap<>());
    }

    /**
     * Deck service.
     *
     * @param deckDataRepository the deck data repository
     * @param cardShuffler the card shuffler
     * @return the deck service
     */
    @Bean
    public DeckService deckService(DeckDataRepository deckDataRepository, Shuffle<Card> cardShuffler) {
        return new DeckServiceImpl(cardShuffler, deckDataRepository);
    }

    /**
     * Randomized shuffle.
     *
     * @return the shuffle
     */
    @Bean
    @Profile("randomized-shuffle")
    public Shuffle<Card> randomizedShuffle() {
        return new RandomizedShuffle<>();
    }

    /**
     * Splitting interleaving shuffle.
     *
     * @return the shuffle
     */
    @Bean
    @Profile("splitting-interleaving")
    public Shuffle<Card> splittingInterleavingShuffle() {
        return new SplittingInterleavingShuffle<>();
    }

}
