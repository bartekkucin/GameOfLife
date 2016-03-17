package com.kucin.bowling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.kucin.bowling.BowlingGameResultCalculator;
import com.kucin.bowling.BowlingGameResultCalculatorInterface;

public class BowlingGameTest {

	private BowlingGameResultCalculatorInterface bowlingGameCalculator;

	@Before
	public void createGame() {
		bowlingGameCalculator = new BowlingGameResultCalculator();
	}

	@Test
	public void shouldRollWithoutHit() {

		rollFewTimes(0, 20);

		assertEquals(0, bowlingGameCalculator.score());
	}

	@Test
	public void shouldRollWithOneHitOnly() {
		rollFewTimes(1, 20);
		
		assertEquals(20, bowlingGameCalculator.score());
	}

	@Test
	public void shouldRollWithSpare() {

		bowlingGameCalculator.roll(9);
		bowlingGameCalculator.roll(1);
		bowlingGameCalculator.roll(5);
		rollFewTimes(0, 17);

		assertEquals(20, bowlingGameCalculator.score());

	}
	
	@Test
	public void shouldRollAlmostPerfectGame() {

		rollFewTimes(10, 11);
		bowlingGameCalculator.roll(5);

		assertEquals(295, bowlingGameCalculator.score());
	}

	@Test
	public void shouldRollWithStrike() {

		bowlingGameCalculator.roll(10);
		bowlingGameCalculator.roll(5);
		bowlingGameCalculator.roll(5);
		bowlingGameCalculator.roll(10);
		bowlingGameCalculator.roll(10);
		bowlingGameCalculator.roll(10);
		bowlingGameCalculator.roll(10);
		bowlingGameCalculator.roll(10);
		bowlingGameCalculator.roll(10);
		bowlingGameCalculator.roll(10);
		bowlingGameCalculator.roll(5);
		bowlingGameCalculator.roll(5);
		bowlingGameCalculator.roll(5);

		assertEquals(250, bowlingGameCalculator.score());

	}

	@Test
	public void shouldNotEndGameWithOnly19Rolls() {
		rollFewTimes(4, 19);
		
		assertEquals(false, bowlingGameCalculator.isFinished());
	}
	
	@Test
	public void shouldEndGameWith4PinsPerRoll() {
		rollFewTimes(4, 20);
		bowlingGameCalculator.score();
		
		assertEquals(true, bowlingGameCalculator.isFinished());
	}
	
	@Test
	public void shouldEndGameWithoutSpareOrStrikeInLastRound() {
		rollFewTimes(10, 9);
		bowlingGameCalculator.roll(5);
		bowlingGameCalculator.roll(4);
		bowlingGameCalculator.score();

		assertEquals(true, bowlingGameCalculator.isFinished());
	}
	
	

	@Test
	public void shouldRollStrikeEverytime() {
		rollFewTimes(10, 12);

		assertEquals(300, bowlingGameCalculator.score());

	}

	private void rollFewTimes(int numberOfPins, int howManyTimes) {
		for (int i = 0; i < howManyTimes; i++) {
			bowlingGameCalculator.roll(numberOfPins);
		}
	}

}
