/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core;

/**
 * Exception class denoting resource already exists scenarios in the system.
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
public class ResourceAlreadyExistsException extends Exception {

    /** */
    private static final long serialVersionUID = -2616553606601401690L;

    /**
     * Instantiates a new resource already exists exception.
     *
     * @param msg the msg
     */
    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }

    /**
     * Instantiates a new resource already exists exception.
     *
     * @param msg the msg
     * @param ex the ex
     */
    public ResourceAlreadyExistsException(String msg, Exception ex) {
        super(msg, ex);
    }

}
