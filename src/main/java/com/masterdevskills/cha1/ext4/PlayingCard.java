package com.masterdevskills.cha1.ext4;

public class PlayingCard implements Card {
    private final Suit suit;
    private final Rank rank;

    public PlayingCard(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    public int hashCode() {
        return ((suit.value() - 1) * 13) + rank.value();
    }

    public int compareTo(Card o) {
        return this.hashCode() - o.hashCode();
    }

    @Override
    public String toString() {
        return suit + ":" + rank;
    }
}
