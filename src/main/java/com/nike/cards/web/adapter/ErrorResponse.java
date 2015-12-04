/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.web.adapter;

/**
 * The Class ErrorResponse.
 *
 * @author pulkit.mehra
 * Created: Dec 4, 2015
 */
public class ErrorResponse {

    /** The error message. */
    private String errorMessage;

    /** The status code. */
    private int statusCode;

    /** The requested uri. */
    private String requestedURI;

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage the new error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the status code.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the status code.
     *
     * @param statusCode the new status code
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * Gets the requested uri.
     *
     * @return the requested uri
     */
    public String getRequestedURI() {
        return requestedURI;
    }

    /**
     * Sets the requested uri.
     *
     * @param requestedURI the new requested uri
     */
    public void setRequestedURI(String requestedURI) {
        this.requestedURI = requestedURI;
    }

}
