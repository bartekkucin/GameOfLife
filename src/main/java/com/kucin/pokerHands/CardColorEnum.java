package com.kucin.pokerHands;

public enum CardColorEnum {

	CLUBS("C"), DIAMONDS("D"), HEARTS("H"), SPADES("S");

	private String cardColor;

	private CardColorEnum(String cardColor) {
		this.cardColor = cardColor;
	}

	public String getCardColor() {
		return cardColor;
	}

}
