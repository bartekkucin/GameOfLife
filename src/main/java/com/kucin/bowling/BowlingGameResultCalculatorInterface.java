package com.kucin.bowling;

public interface BowlingGameResultCalculatorInterface {
	
	/**
	 * Register a thrown a ball.
	 * @param numberOfPins number of knocked down pins
	 */
	public void roll(int numberOfPins);

	/**
	 * @return current game score
	 */
	public int score();

	/**
	 * @return true if a game is over, otherwise false
	 */
	public boolean isFinished();

}
