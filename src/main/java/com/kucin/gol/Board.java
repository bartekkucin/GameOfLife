package com.kucin.gol;

public class Board {

	private ICell[][] cells;
	CellStateEnum cellStateEnum;
	Coordinator coordinator;

	public Board(int boardSize) {
		cells = new CellImpl[boardSize][boardSize];
		fillBoardWithCells(CellStateEnum.DEAD);
	}

	public void fillBoardWithCells(CellStateEnum cellStateEnum) {
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				addCell(x, y, cellStateEnum);
			}
		}
	}

	public void setBoardSize(int size) {
		cells = new CellImpl[size][size];
		fillBoardWithCells(CellStateEnum.DEAD);
	}
	
	public int getBoardSize() {
		return cells.length;
	}

	public void addCell(int x, int y, CellStateEnum cellStateEnum) {
		cells[x][y] = new CellImpl(cellStateEnum, new Coordinator(x, y));
	}

	public boolean checkIfDead(int x, int y) {
		return cells[x][y].isDead();
	}

	public int countCellsByEnumParam(CellStateEnum cellStateEnum) {
		int cellsCounter = 0;
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				if (!(cells[x][y].isDead() && cellStateEnum == CellStateEnum.ALIVE)) {
					cellsCounter++;
				} else if (cells[x][y].isDead() && cellStateEnum == CellStateEnum.DEAD) {
					cellsCounter++;
				}
			}
		}
		return cellsCounter++;
	}

	public int countDeadCells() {
		int deadCellsCounter = 0;
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				if (cells[x][y].isDead()) {
					deadCellsCounter++;
				}
			}
		}

		return deadCellsCounter;
	}

	public int findLivingNeighbours(int x, int y) {
		int neighbourCounter = 0;
		int leftUpperX = Math.max(x - 1, 0);
		int leftUpperY = Math.max(y - 1, 0);
		int rightLowerX = Math.min(x + 1, cells.length - 1);
		int rightLowerY = Math.min(y + 1, cells.length - 1);
		
		if (!(cells[x][y].isDead())) {
			neighbourCounter--;
		}

		for (int i = leftUpperX; i <= rightLowerX; i++) {
			for (int j = leftUpperY; j <= rightLowerY; j++) {
				if (!(cells[i][j].isDead())) {
					neighbourCounter++;
				}

			}
		}

		return neighbourCounter;
	}

	public void evaluate() {
		CellImpl[][] tempCells = new CellImpl[cells.length][cells.length];

		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++) {
				CellImpl cell = (CellImpl) cells[x][y];
				int neighbours = findLivingNeighbours(x, y);
				tempCells[x][y] = cell.nextGeneration(neighbours);
			}
		}

		cells = tempCells;
	}

}
