/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.web.adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class DeckNamesWrapper.
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
public class DeckNamesWrapper {

    /** The decks. */
    private List<String> decks;

    /**
     * Gets the decks.
     *
     * @return the decks
     */
    public List<String> getDecks() {
        if (decks == null) {
            decks = new ArrayList<>();
        }
        return decks;
    }

}
