package com.kucin.gol;

public class CellImpl implements ICell {

	CellStateEnum cellState;
	Coordinator coordinator;

	public CellImpl() {
	}

	public CellImpl(CellStateEnum cellState, Coordinator coordinator) {
		super();
		this.cellState = cellState;
		this.coordinator = coordinator;
	}

	public CellImpl nextGeneration(int numberOfLivingNeighbours) {
		
		if (numberOfLivingNeighbours == 3 && cellState == CellStateEnum.DEAD) {
			return new CellImpl(CellStateEnum.ALIVE, coordinator);
		}
		if (numberOfLivingNeighbours < 2 || numberOfLivingNeighbours > 3) {
			return new CellImpl(CellStateEnum.DEAD, coordinator);
		}
		if (numberOfLivingNeighbours == 2 && cellState == CellStateEnum.DEAD) {
			return new CellImpl(CellStateEnum.DEAD, coordinator);
		}
		if ((numberOfLivingNeighbours == 3 || numberOfLivingNeighbours == 2) && cellState == CellStateEnum.ALIVE) {
			return new CellImpl(CellStateEnum.ALIVE, coordinator);
		}
		throw new IllegalArgumentException(numberOfLivingNeighbours + " " + cellState);
	}

	public CellStateEnum getCellState() {
		return cellState;
	}

	public void setCellState(CellStateEnum cellState) {
		this.cellState = cellState;
	}

	public Coordinator getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(Coordinator coordinator) {
		this.coordinator = coordinator;
	}

	@Override
	public boolean isDead() {
		return cellState == CellStateEnum.DEAD;
	}

}
