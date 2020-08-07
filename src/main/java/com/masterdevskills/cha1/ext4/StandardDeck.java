package com.masterdevskills.cha1.ext4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class StandardDeck implements Deck {
    private final List<Card> entireDeck;

    public StandardDeck() {
        entireDeck = new ArrayList<>();
    }

    public void sort() {
        Collections.sort(entireDeck);
    }

    @Override
    public void sort(final Comparator<Card> c) {

    }

    @Override
    public String deckToString() {
        return null;
    }

    @Override
    public Map<Integer, Deck> deal(final int players, final int numberOfCards) throws IllegalArgumentException {
        return null;
    }

    @Override
    public List<Card> getCards() {
        return null;
    }

    @Override
    public Deck deckFactory() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void addCard(final Card card) {

    }

    @Override
    public void addCards(final List<Card> cards) {

    }

    @Override
    public void addDeck(final Deck deck) {

    }

    public void shuffle() {
        Collections.shuffle(entireDeck);
    }
}
