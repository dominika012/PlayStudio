package com.ness.Game.slidingPuzzle.core;

import java.util.Arrays;
import java.util.Random;

public class PuzzleField {

	private final int rowCount;
	private final int columnCount;
	private int[][] puzzleField;
	private long startMillis;

	public PuzzleField(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;

		if (!(rowCount > 2 && columnCount > 2)) {
			throw new IllegalArgumentException("Bad amount of rows or columns...");
		}

		puzzleField = new int[rowCount][columnCount];
		generate();
		mixField();
		startMillis = System.currentTimeMillis();
	}

	private int[][] defaultField() {
		int[][] orderedField = new int[rowCount][columnCount];
		int value = 0;
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				value += 1;
				orderedField[r][c] = value;
			}
		}
		orderedField[rowCount - 1][columnCount - 1] = 0;
		return orderedField;
	}

	private void swap(int row, int column, int row2, int column2) {
		int temp = puzzleField[row][column];
		puzzleField[row][column] = puzzleField[row2][column2];
		puzzleField[row2][column2] = temp;
	}

	public void move(int row, int column, int side) {
		switch (side) {
		case 6:
			if (canMoveLeft(row, column))
				swap(row, column, row, column - 1);
			break;
		case 4:
			if (canMoveRight(row, column))
				swap(row, column, row, column + 1);
			break;
		case 2:
			if (canMoveUp(row, column))
				swap(row, column, row - 1, column);
			break;
		case 8:
			if (canMoveDown(row, column))
				swap(row, column, row + 1, column);
			break;
		default:
			break;
		}
	}

	public Position getZeroPosition() {
		for (int r = 0; r < rowCount; r++) {
			for (int c = 0; c < columnCount; c++) {
				if (puzzleField[r][c] == 0)
					return new Position(r, c);
			}
		}
		// throw
		return null;
	}

	private boolean canMoveUp(int row, int column) {
		return row >= 1;
	}

	private boolean canMoveDown(int row, int column) {
		return row + 1 < rowCount;
	}

	private boolean canMoveLeft(int row, int column) {
		return column >= 1;
	}

	private boolean canMoveRight(int row, int column) {
		return column + 1 < columnCount;
	}

	private int randomSide() {
		Random random = new Random();
		int sub = random.nextInt(4) + 1;
		return sub * 2;
	}

	private void mixField() {
		/*
		 * for(int i = 0; i<50; i++){
		 * move(rowOfZero(),columnOfZero(),randomSide()); }
		 */
		Position position = getZeroPosition();
		move(position.getRow(), position.getColumn(), 2);
	}

	private void generate() {
		puzzleField = defaultField();
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getCell(int row, int column) {
		return puzzleField[row][column];
	}

	public boolean isSolved() {
		return Arrays.deepEquals(puzzleField, defaultField());
	}

	public int getPlayingSeconds() {
		return (int) ((System.currentTimeMillis() - startMillis) / 1000);
	}

}
