/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.core.model;

/**
 * The Class Deck.
 *
 * @author pulkit.mehra
 * Created: Dec 3, 2015
 */
public class Deck {

    /** The deck name. */
    private String deckName;

    /** The cards. */
    private Card[] cards = new Card[52];

    /**
     * Instantiates a new deck.
     */
    public Deck() {

    }

    /**
     * Gets the single instance of Deck.
     *
     * @return single instance of Deck
     */
    public static Deck getInstance() {
        Deck deck = new Deck();
        Card[] cards = deck.getCards();
        int pos = 0;
        for (Suit suit : Suit.values()) {
            for (int i = 1; i <= 13; i++) {
                Card card = new Card(i, suit);
                cards[pos++] = card;
            }
        }
        return deck;
    }

    /**
     * Gets the cards.
     *
     * @return the cards
     */
    public Card[] getCards() {
        return cards;
    }

    /**
     * Sets the cards.
     *
     * @param cards the new cards
     */
    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    /**
     * Gets the deck name.
     *
     * @return the deck name
     */
    public String getDeckName() {
        return deckName;
    }

    /**
     * Sets the deck name.
     *
     * @param deckName the new deck name
     */
    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

}
