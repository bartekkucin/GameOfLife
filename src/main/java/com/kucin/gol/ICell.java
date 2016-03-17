package com.kucin.gol;

public interface ICell {
	
	boolean isDead();
	CellImpl nextGeneration(int numberOfLivingNeighbours);

}
