package com.kucin.pokerHands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

public class PokerHandsImpl implements PokerHands{
	
	File file;
	Scanner scanner;
	Player player1;
	Player player2;
	int numberofPlayer1Wins;
	static Logger log = Logger.getLogger(PokerHandsImpl.class.getName());
	
	
	public PokerHandsImpl(String file) throws FileNotFoundException
	{
		this.file = new File(file);
		this.scanner = new Scanner(this.file);
	}

	public void createPlayers(String[] cards) {
		player1 = new PlayerImpl(Arrays.copyOfRange(cards, 0, 5));
		player2 = new PlayerImpl(Arrays.copyOfRange(cards, 5, 10));

	}

	public void startPoker() {
		try
		{
			while (scanner.hasNextLine()) {
				createPlayers(scanner.nextLine().split("\\s+"));
				if(checkIfPlayer1Wins())
				{
					setNumberOfWinsForPlayer1();
				}
			}
		}
		catch(Exception e)
		{
			log.info(e.getMessage());
		}
	}

	public boolean checkIfPlayer1Wins() {
		if (player1.checkStraightAndFlush() && player2.checkStraightAndFlush())	return checkCardsValue(1);
		if (player1.checkStraightAndFlush() || player2.checkStraightAndFlush())	return player1.checkStraightAndFlush();
		
		if (player1.checkIfFourCards() && player2.checkIfFourCards()) return checkCardsValue(3);
		if (player1.checkIfFourCards() || player2.checkIfFourCards()) return player1.checkIfFourCards();

		if (player1.checkIfFull() && player2.checkIfFull()) return checkCardsValue(2);
		if (player1.checkIfFull() || player2.checkIfFull()) return player1.checkIfFull();

		if (player1.checkIfFlush() && player2.checkIfFlush()) return checkCardsValue(0);
		if (player1.checkIfFlush() || player2.checkIfFlush()) return player1.checkIfFlush();

		if (player1.checkIfStraight() && player2.checkIfStraight()) return checkCardsValue(1);
		if (player1.checkIfStraight() || player2.checkIfStraight()) return player1.checkIfStraight();

		if (player1.checkIfThreeCards() && player2.checkIfThreeCards()) return checkCardsValue(2);
		if (player1.checkIfThreeCards() || player2.checkIfThreeCards()) return player1.checkIfThreeCards();

		if (player1.checkIfTwoPairs() && player2.checkIfTwoPairs()) return checkCardsValue(1);
		if (player1.checkIfTwoPairs() || player2.checkIfTwoPairs()) return player1.checkIfTwoPairs();

		if (player1.checkIfPair() && player2.checkIfPair()) return checkCardsValue(1);
		if (player1.checkIfPair() || player2.checkIfPair()) return player1.checkIfPair();

		return checkCardsValue(0);
	}

	public boolean checkCardsValue(int card) {
		for (int i = card; i < 5; i++) {
			if (player1.getCardValue(i) > player2.getCardValue(i))
				return true;
			if (player1.getCardValue(i) < player2.getCardValue(i))
				return false;
		}
		return false;
	}

	public void setNumberOfWinsForPlayer1() {
		this.numberofPlayer1Wins++;
		
	}

	public int getNumberOfWinsForPlayer1() {
		
		return this.numberofPlayer1Wins;
	}

}
