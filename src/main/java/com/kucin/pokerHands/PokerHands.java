package com.kucin.pokerHands;

public interface PokerHands {
	
	void createPlayers(String[] cards);
	
	void startPoker();
	
	boolean checkIfPlayer1Wins();
	
	boolean checkCardsValue(int cardValue);
	
	int getNumberOfWinsForPlayer1();

}
