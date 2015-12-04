/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core;

/**
 * The Class ResourceNotFoundException.
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
public class ResourceNotFoundException extends Exception {

    /** */
    private static final long serialVersionUID = 1950437292671406930L;

    /**
     * Instantiates a new resource not found exception.
     *
     * @param msg the msg
     */
    public ResourceNotFoundException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new resource not found exception.
     *
     * @param msg the msg
     * @param ex the ex
     */
    public ResourceNotFoundException(String msg, Exception ex) {
        super(msg, ex);
    }

}
