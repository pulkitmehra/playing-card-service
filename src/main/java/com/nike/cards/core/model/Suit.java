/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.model;

/**
 * The Enum Suit.
 *
 * @author pulkit.mehra
 * Created: Dec 3, 2015
 */
public enum Suit {

    /** The spades. */
    SPADES(Color.BLACK),
    /** The hearts. */
    HEARTS(Color.RED),
    /** The diamonds. */
    DIAMONDS(Color.RED),
    /** The clubs. */
    CLUBS(Color.BLACK);

    /**
     * The Enum Color.
     */
    public enum Color {

        /** The red. */
        RED,
        /** The black. */
        BLACK;
    }

    /** The color. */
    private Color color;

    /**
     * Instantiates a new suit.
     *
     * @param color the color
     */
    private Suit(Color color) {
        this.color = color;
    }

    /**
     * Gets the color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

}
