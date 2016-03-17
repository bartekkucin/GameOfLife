package com.kucin.pokerHands;

public interface Player {

	boolean checkIfPair();

	boolean checkIfThreeCards();
	
	boolean checkIfFourCards();

	boolean checkIfTwoPairs();

	boolean checkIfFull();

	boolean checkIfFlush();

	boolean checkIfStraight();

	boolean checkStraightAndFlush();

	int getCardValue(int cardValue);

}
