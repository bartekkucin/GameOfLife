package com.kucin.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameResultCalculator implements BowlingGameResultCalculatorInterface {

	private List<Integer> rollList = new ArrayList<Integer>();
	private static final int MAX_FRAMES = 10;
	private static final int REAL_NUMBER_OF_ROLLS = 20;
	private int actualRollNumber = 0;
	private int strikeCount = 0;

	public void roll(int numberOfPins) {
		actualRollNumber++;
		rollList.add(numberOfPins);
	}

	public int score() {

		int score = 0;
		int rollIndex = 0;
		for (int i = 0; i < MAX_FRAMES; i++) {
			if (isSpare(rollIndex)) {
				score = score + countSpareHitPoints(rollIndex);
				rollIndex += 2;
			} else if (isStrike(rollIndex)) {
				score = score + countStrikeHitPoints(rollIndex);
				strikeCount++;
				rollIndex++;
			} else {
				score = score + countNormalHitPoints(rollIndex);
				rollIndex += 2;
			}
		}
		return score;
	}

	public boolean isFinished() {
		return checkTotalNumberOfRolls(rollList) + strikeCount == REAL_NUMBER_OF_ROLLS;

	}

	private int countNormalHitPoints(int rollIndex) {
		int sumOfPoints = rollList.get(rollIndex) + rollList.get(rollIndex+1);
		return sumOfPoints;
	}

	private int countSpareHitPoints(int rollIndex) {
		int sumOfPoints = rollList.get(rollIndex) + rollList.get(rollIndex+1) + rollList.get(rollIndex+2);
		return sumOfPoints;
	}

	private int countStrikeHitPoints(int rollIndex) {
		int sumOfPoints = rollList.get(rollIndex) + rollList.get(rollIndex+1) + rollList.get(rollIndex+2);
		return sumOfPoints;
	}

	private boolean isSpare(int rollIndex) {
		return rollList.get(rollIndex) + rollList.get(rollIndex+1) == 10;
	}

	private boolean isStrike(int rollIndex) {
		return rollList.get(rollIndex) == 10;
	}

	//This is best way to create standard value(20) of total rolls.
	private int checkTotalNumberOfRolls(List<Integer> list) {
		int totalNumberOfRolls = actualRollNumber;
		int lastButTwo = list.get(list.size() - 3);
		int penultimate = list.get(list.size() - 2);
		int last = list.get(list.size() - 1);

		if (lastButTwo + penultimate == 10) {
			totalNumberOfRolls--;
		}

		if (penultimate == 10 || last == 10) {
			totalNumberOfRolls = totalNumberOfRolls - 2;
		}

		return totalNumberOfRolls;

	}

}
