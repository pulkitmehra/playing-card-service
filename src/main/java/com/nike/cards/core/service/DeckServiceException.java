/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.service;

/**
 * Exception scenarios for {@link DeckService}
 *
 * @author pulkit.mehra
 * Created: Dec 3, 2015
 */
public class DeckServiceException extends Exception {

    /** */
    private static final long serialVersionUID = -4350689758283617318L;

    /**
     * Instantiates a new deck service exception.
     *
     * @param msg the msg
     */
    public DeckServiceException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new deck service exception.
     *
     * @param msg the msg
     * @param ex the ex
     */
    public DeckServiceException(String msg, Exception ex) {
        super(msg, ex);
    }
}
