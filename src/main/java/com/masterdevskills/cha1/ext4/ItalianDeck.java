package com.masterdevskills.cha1.ext4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 08 August 2020
 */
public class ItalianDeck  implements Deck{
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
	public void addCard(Card card) {

	}

	@Override
	public void addCards(List<Card> cards) {

	}

	@Override
	public void addDeck(Deck deck) {

	}

	@Override
	public void shuffle() {

	}

	@Override
	public void sort() {

	}

	@Override
	public void sort(Comparator<Card> c) {

	}

	@Override
	public String deckToString() {
		return null;
	}

	@Override
	public Map<Integer, Deck> deal(int players, int numberOfCards) throws IllegalArgumentException {
		return null;
	}
}
