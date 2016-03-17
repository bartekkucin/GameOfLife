package com.kucin.gol;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board board;

	@Before
	public void setUp() {
		board = new Board(3);
	}

	@Test
	public void shouldChangeGridSize() {
		board.setBoardSize(4);
		assertEquals(4, board.getBoardSize());
	}

	@Test
	public void shouldAddDeadCellWithCoordinates() {
		board.addCell(1, 1, CellStateEnum.DEAD);
		assertTrue(board.checkIfDead(1, 1));

	}

	@Test
	public void shouldAddAliveCellWithCoordinates() {
		board.addCell(1, 1, CellStateEnum.ALIVE);;
		assertFalse(board.checkIfDead(1, 1));

	}

	@Test
	public void shouldReturnNumberOfLivingCells() {
		board.addCell(1, 1, CellStateEnum.ALIVE);
		board.addCell(0, 1, CellStateEnum.ALIVE);
		assertEquals(2, board.countCellsByEnumParam(CellStateEnum.ALIVE));
	}
	
	@Test
	public void shouldFillGridWithDeadCells() {
		board.fillBoardWithCells(CellStateEnum.DEAD);
		assertEquals(9, board.countDeadCells());
	}
	
	@Test
	public void shouldFillGridWithLivingCells() {
		board.fillBoardWithCells(CellStateEnum.ALIVE);
		assertEquals(9, board.countCellsByEnumParam(CellStateEnum.ALIVE));
	}
	
	@Test
	public void shouldFindAllLivingNeighboursForCell()
	{
		board.fillBoardWithCells(CellStateEnum.ALIVE);
		assertEquals(8, board.findLivingNeighbours(1, 1));
	}
	@Test
	public void shouldfindOneLivingNeighbourForCell()
	{
		board.addCell(0, 0, CellStateEnum.ALIVE);
		assertEquals(1, board.findLivingNeighbours(1, 1));
	}
	
	@Test
	public void shouldDieIfHasFewerThanTwoNeighbours()
	{
		board.addCell(1, 1, CellStateEnum.ALIVE);
		board.addCell(1, 2, CellStateEnum.ALIVE);
		board.evaluate();
		assertEquals(0, board.countCellsByEnumParam(CellStateEnum.ALIVE));
	}
	
	@Test
	public void shouldLiveWithThreeLivingNeighbours()
	{
		board.addCell(1, 1, CellStateEnum.ALIVE);
		board.addCell(0, 0, CellStateEnum.ALIVE);
		board.addCell(2, 0, CellStateEnum.ALIVE);
		board.addCell(2, 2, CellStateEnum.ALIVE);
		board.evaluate();
		assertFalse(board.checkIfDead(1, 1));
	}
	
	@Test
	public void shouldDieWithOneLivingNeighbour()
	{
		board.addCell(1, 1, CellStateEnum.ALIVE);
		board.addCell(0, 0, CellStateEnum.ALIVE);
		board.addCell(2, 0, CellStateEnum.ALIVE);
		board.addCell(2, 2, CellStateEnum.ALIVE);
		board.evaluate();
		assertTrue(board.checkIfDead(0, 0));
	}
	
	@Test
	public void shouldLiveWithTwoLivingNeighbour()
	{
		board.addCell(1, 1, CellStateEnum.ALIVE);
		board.addCell(0, 0, CellStateEnum.ALIVE);
		board.addCell(2, 0, CellStateEnum.ALIVE);
		board.evaluate();
		assertFalse(board.checkIfDead(1, 1));
	}
	
	@Test
	public void shouldDieAfterSecondEvaluation()
	{
		board.addCell(1, 1, CellStateEnum.ALIVE);
		board.addCell(0, 0, CellStateEnum.ALIVE);
		board.addCell(2, 0, CellStateEnum.ALIVE);
		board.evaluate();
		board.evaluate();
		assertTrue(board.checkIfDead(1, 1));
	}
	
	@Test
	public void shouldLiveAfterThirdEvaluation()
	{
		board.addCell(1, 1, CellStateEnum.ALIVE);
		board.addCell(0, 0, CellStateEnum.ALIVE);
		board.addCell(1, 0, CellStateEnum.ALIVE);
		board.addCell(0, 1, CellStateEnum.ALIVE);
		board.evaluate();
		board.evaluate();
		board.evaluate();
		assertFalse(board.checkIfDead(1, 1));
	}
	
	@Test
	public void shouldResurrectWithThreeNeighbours()
	{
		board.addCell(0, 0, CellStateEnum.ALIVE);
		board.addCell(2, 2, CellStateEnum.ALIVE);
		board.addCell(0, 1, CellStateEnum.ALIVE);
		board.evaluate();
		assertFalse(board.checkIfDead(1, 1));
	}
	
	@Test
	public void shouldOscialteAfterEvaluations()
	{
		board.addCell(0, 1, CellStateEnum.ALIVE);
		board.addCell(1, 1, CellStateEnum.ALIVE);
		board.addCell(2, 1, CellStateEnum.ALIVE);
		board.evaluate();
		board.evaluate();
		board.evaluate();
		assertFalse(board.checkIfDead(1, 0));
		assertFalse(board.checkIfDead(1, 1));
		assertFalse(board.checkIfDead(1, 2));
		board.evaluate();
		assertFalse(board.checkIfDead(0, 1));
		assertFalse(board.checkIfDead(1, 1));
		assertFalse(board.checkIfDead(2, 1));
	}

	
	

}
