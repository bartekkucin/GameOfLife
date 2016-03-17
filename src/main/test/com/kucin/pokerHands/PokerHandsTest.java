package com.kucin.pokerHands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class PokerHandsTest {

	private PokerHands pokerHands;

	@Before
	public void setUpFileForGame() throws FileNotFoundException {
		pokerHands = new PokerHandsImpl("../GoL_Poker_Bowling_byKucinski/src/main/java/com/kucin/pokerHands/poker.txt");
	}
	
	@Test
	public void shouldReturnTrueForSumOfPlayer1Wins() {
		pokerHands.startPoker();
		assertEquals(376, pokerHands.getNumberOfWinsForPlayer1());
	}
	
	@Test 
	public void shouldReturnFalseForOneHighestCardInPlayer2Hand() {
		String[] cards = {"5C","KS","QD","4C","3S", "AC","TD","KD","3C","JD" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test 
	public void shouldReturnTrueForOneHighestCardInPlayer1Hand() {
		String[] cards = {"AC","KS","QD","4C","3S", "2C","TD","KD","3C","JD" };
		pokerHands.createPlayers(cards);
		assertTrue(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test 
	public void shouldReturnFalseForOneBetterPairInPlayer2Hand() {
		String[] cards = {"5C","KS","QD","3C","3S", "AC","TD","KD","KC","JD" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test 
	public void shouldReturnTrueForOneBetterPairInPlayer1Hand() {
		String[] cards = {"AC","AD","KC","QC","JC", "AC","TD","KD","KC","JD" };
		pokerHands.createPlayers(cards);
		assertTrue(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnFalseForThreeBetterCardsInPlayer2Hand() {
		String[] cards = { "5H", "5C", "6S", "5S", "KD", "8C", "3S", "8S", "8D", "TD" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnTrueForThreeBetterCardsInPlayer1Hand() {
		String[] cards = { "QH", "QC", "QS", "5S", "KD", "8C", "3S", "8S", "8D", "TD" };
		pokerHands.createPlayers(cards);
		assertTrue(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnFalseForBetterTwoPairsInPlayer2Hand() {
		String[] cards = { "4D", "6S", "4H", "6H", "QC", "3D", "7D", "7H", "3D", "QS" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnTrueForBetterTwoPairsInPlayer1Hand() {
		String[] cards = { "8D", "6S", "8H", "6H", "QC", "3D", "7D", "7H", "3D", "QS" };
		pokerHands.createPlayers(cards);
		assertTrue(pokerHands.checkIfPlayer1Wins());
	}

	@Test
	public void shouldReturnFalseForFlushInPlayer2Hand() {
		String[] cards = { "2D", "9C", "AS", "AH", "AC", "3D", "6D", "7D", "TD", "QD" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnFalseForBetterFourCardsInPlayer2Hand() {
		String[] cards = { "2H", "2D", "2C", "2S", "QS", "3H", "3D", "3C", "3S", "QD" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnTrueForBetterFourCardsInPlayer1Hand() {
		String[] cards = { "4H", "4D", "4C", "4S", "QS", "3H", "3D", "3C", "3S", "QD" };
		pokerHands.createPlayers(cards);
		assertTrue(pokerHands.checkIfPlayer1Wins());
	}

	@Test
	public void shouldReturnTrueForBetterFullInPlayer1Hand() {
		String[] cards = { "2H", "2D", "4C", "4D", "4S", "3C", "3D", "3S", "9S", "9D" };
		pokerHands.createPlayers(cards);
		assertTrue(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnFalseForBetterFullInPlayer2Hand() {
		String[] cards = { "2H", "2D", "4C", "4D", "4S", "5C", "5D", "5S", "9S", "9D" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnFalseForBetterFlushInPlayer2Hand() {
		String[] cards = { "2S", "4S", "5S", "TS", "QS", "KS", "AS", "3S", "7S", "9S" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnFalseForBetterFlushInPlayer2HandWithOneMoreValuableCard() {
		String[] cards = { "KS", "AS", "TS", "9S", "7S", "KD", "AD", "TD", "8D", "9D" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}

	@Test
	public void shouldReturnTrueForStraightForPlayer1Hand() {
		String[] cards = { "6C", "8S", "TC", "7C", "9D", "AC", "KD", "QS", "JD", "9D" };
		pokerHands.createPlayers(cards);
		assertTrue(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnTrueForBetterStraightForPlayer1Hand() {
		String[] cards = { "JC", "8S", "TC", "7C", "9D", "2C", "3D", "4S", "5D", "6D" };
		pokerHands.createPlayers(cards);
		assertTrue(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnFalseForBetterStraightForPlayer2Hand() {
		String[] cards = { "TC", "8S", "TC", "7C", "9D", "AC", "QD", "KS", "JD", "TD" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnTrueForLowestStraightInPlayer1Hand() {
		String[] cards = { "AC", "2S", "3C", "4C", "5D", "3C", "3D", "6S", "4D", "5D" };
		pokerHands.createPlayers(cards);
		assertTrue(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnFalseForBetterStraightInPlayer2Hand() {
		String[] cards = { "AC", "2S", "3C", "4C", "5D", "3C", "4D", "5S", "6D", "2D" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnTrueForBetterStraightInPlayer1HandWithAce() {
		String[] cards = { "AC", "2S", "3C", "4C", "5D", "AD", "2D", "3S", "4D", "4H" };
		pokerHands.createPlayers(cards);
		assertTrue(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnFalseForLowestStraightFlushInPlayer1Hand() {
		String[] cards = { "AC", "2C", "3C", "4C", "5C", "6D", "2D", "3D", "4D", "5D" };
		pokerHands.createPlayers(cards);
		assertFalse(pokerHands.checkIfPlayer1Wins());
	}
	
	@Test
	public void shouldReturnTrueForBetterStraightFlushInPlayer1Hand() {
		String[] cards = { "AC", "KC", "QC", "JC", "TC", "KD", "QD", "JD", "TD", "9D" };
		pokerHands.createPlayers(cards);
		assertTrue(pokerHands.checkIfPlayer1Wins());
	}
	

}
