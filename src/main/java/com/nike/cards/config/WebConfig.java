/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import com.nike.cards.core.service.DeckService;
import com.nike.cards.web.adapter.DeckExceptionAdvice;
import com.nike.cards.web.adapter.DeckWebAdapter;
import com.nike.cards.web.adapter.SpringDeckWebAdapter;


/**
 * Spring WebConfig.
 *
 * @author pulkit.mehra
 */
@Configuration
@EnableWebMvc
@ComponentScan({ "com.nike.cards.web" })
@Import(ServiceConfig.class)
public class WebConfig extends WebMvcConfigurationSupport {

    /**
     * Deck web adapter.
     *
     * @param deckService the deck service
     * @return the deck web adapter
     */
    @Bean
    public DeckWebAdapter deckWebAdapter(DeckService deckService) {
        return new SpringDeckWebAdapter(deckService);
    }

    /**
     * Deck exception advice.
     *
     * @return the deck exception advice
     */
    @Bean
    public DeckExceptionAdvice deckExceptionAdvice() {
        return new DeckExceptionAdvice();
    }
}
