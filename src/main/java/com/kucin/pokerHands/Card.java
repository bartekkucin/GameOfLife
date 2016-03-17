package com.kucin.pokerHands;

public class Card {

	private CardColorEnum cardColor;
	private CardValueEnum cardValue;
	private int pairsFactor;

	public Card(String cardCode, String cardColor) {
		this.pairsFactor = 0;
		setColor(cardColor);
		setValue(cardCode);
	}

	public void setValue(String cardCode) {
		for (CardValueEnum cv : CardValueEnum.values()) {
			if (cv.getCardCode().equals(cardCode))
				this.cardValue = cv;
		}
	}

	public void setColor(String cardColor) {
		for (CardColorEnum cc : CardColorEnum.values()) {
			if (cc.getCardColor().equals(cardColor))
				this.cardColor = cc;
		}
	}

	public String getColor() {
		return cardColor.getCardColor();
	}

	public int getValue() {
		return cardValue.getCardValue();
	}

	public int getPairsFactor() {
		return pairsFactor;
	}

	public void setPairsFactor() {
		this.pairsFactor++;
	}
	
	

}
