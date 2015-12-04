/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.model;

/**
 * The Class Card.
 *
 * @author pulkit.mehra
 * Created: Dec 3, 2015
 */
public class Card {

    /** The val. */
    private int val;

    /** The suit. */
    private Suit suit;

    /**
     * Instantiates a new card.
     */
    public Card() {

    }

    /**
     * Instantiates a new card.
     *
     * @param val the val
     * @param suit the suit
     */
    public Card(int val, Suit suit) {
        this.val = val;
        this.suit = suit;
    }

    /**
     * Gets the val.
     *
     * @return the val
     */
    public int getVal() {
        return val;
    }

    /**
     * Sets the val.
     *
     * @param val the new val
     */
    public void setVal(int val) {
        this.val = val;
    }

    /**
     * Gets the suit.
     *
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Sets the suit.
     *
     * @param suit the new suit
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Card [" + val + "-" + suit + "-" + suit.getColor() + "]";
    }

}
