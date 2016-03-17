 package com.kucin.pokerHands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlayerImpl implements Player {

	private static final int MAX_CARDS_IN_HAND = 5;
	private static final int LOW_STRAIGHT_CARD_DIFFERENCE = 9;
	private List<Card> cardList;

	public PlayerImpl(String[] cardList) {
		this.cardList = new ArrayList<Card>();
		addCardsToHand(cardList);
		setPairsFactorForCardsInHand();
	}

	public void addCardsToHand(String[] cardList) {
		for (String string : cardList) {
			this.cardList.add(new Card("" + string.charAt(0), "" + string.charAt(1)));
		}

	}

	public void setPairsFactorForCardsInHand() {
		for (int i = 0; i < MAX_CARDS_IN_HAND; i++) {
			for (int j = 0; j < MAX_CARDS_IN_HAND; j++) {
				if (cardList.get(i).getValue() == cardList.get(j).getValue()) {
					cardList.get(i).setPairsFactor();
				}
			}
		}

	}

	public boolean checkIfPair() {
		sortCardsInHandByPairFactor();
		return getFirstCardValueInHand() == getSecondCardValueInHand();
	}

	public boolean checkIfThreeCards() {
		sortCardsInHandByPairFactor();
		return checkIfPair() && (getSecondCardValueInHand() == getThirdCardValueInHand());
	}

	public boolean checkIfFourCards() {
		sortCardsInHandByPairFactor();
		return checkIfThreeCards() && (getThirdCardValueInHand() == getFourthCardValueInHand());
	}

	public boolean checkIfTwoPairs() {
		sortCardsInHandByPairFactor();
		return checkIfPair() && (getThirdCardValueInHand() == getFourthCardValueInHand());
	}

	public boolean checkIfFull() {
		sortCardsInHandByPairFactor();
		return checkIfThreeCards() && (getFourthCardValueInHand() == getFifthCardValueInHand());
	}

	public boolean checkIfFlush() {
		sortCardsInHandByValue();

		for (int i = 1; i < cardList.size(); i++) {
			if (!(cardList.get(0).getColor().equals(cardList.get(i).getColor()))) {
				return false;
			}
		}

		return true;
	}

	public boolean checkIfStraight() {
		sortCardsInHandByValue();
		for (int i = 1; i < 5; i++) {
			if (((getFirstCardValueInHand() - i) != cardList.get(i).getValue())
					&& (getFirstCardValueInHand() - getSecondCardValueInHand() != LOW_STRAIGHT_CARD_DIFFERENCE))
				return false;
		}
		return true;
	}

	public boolean checkStraightAndFlush() {
		return checkIfStraight() && checkIfFlush();
	}

	public void sortCardsInHandByPairFactor() {

		Collections.sort(cardList, new Comparator<Card>() {
			public int compare(Card c1, Card c2) {
				if (c1.getPairsFactor() > c2.getPairsFactor())
					return -1;
				if (c1.getPairsFactor() == c2.getPairsFactor())
					return 0;
				return 1;
			}
		});
	}

	public void sortCardsInHandByValue() {

		Collections.sort(cardList, new Comparator<Card>() {
			public int compare(Card c1, Card c2) {
				if (c1.getValue() > c2.getValue())
					return -1;
				if (c1.getValue() == c2.getValue())
					return 0;
				return 1;
			}
		});
	}

	public int getFirstCardValueInHand() {
		return cardList.get(0).getValue();
	}

	public int getSecondCardValueInHand() {
		return cardList.get(1).getValue();
	}

	public int getThirdCardValueInHand() {
		return cardList.get(2).getValue();
	}

	public int getFourthCardValueInHand() {
		return cardList.get(3).getValue();
	}

	public int getFifthCardValueInHand() {
		return cardList.get(4).getValue();
	}

	public int getCardValue(int cardValue) {
		return cardList.get(cardValue).getValue();
	}

}
