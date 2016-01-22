package com.ness.GameStudio.main;

import java.util.Scanner;

import com.ness.Game.minesweeper.consoleui.MineConsoleUI;
import com.ness.Game.slidingPuzzle.consoleui.PuzzleConsoleUI;
import com.ness.GameStudio.score.HallOfFame;

public class GameStudio {

	private MineConsoleUI mineConsoleUI;
	private PuzzleConsoleUI puzzleConsoleUI;
	
	public MineConsoleUI getMineConsoleUI() {
		return mineConsoleUI;
	}

	public void setMineConsoleUI(MineConsoleUI mineConsoleUI) {
		this.mineConsoleUI = mineConsoleUI;
	}

	public PuzzleConsoleUI getPuzzleConsoleUI() {
		return puzzleConsoleUI;
	}

	public void setPuzzleConsoleUI(PuzzleConsoleUI puzzleConsoleUI) {
		this.puzzleConsoleUI = puzzleConsoleUI;
	}

	
	
	static final Scanner sc = new Scanner(System.in);

	public void play() {
		
		double avarageMines=0;
		Long votersMines = 0L;
		double avaragePuzzle=0;
		Long votersPuzzle = 0L;
		try {
			avarageMines = mineConsoleUI.getHallOfFame().avarageRating();
			votersMines = mineConsoleUI.getHallOfFame().votersCounter();
			avaragePuzzle = puzzleConsoleUI.getHallOfFame().avarageRating();
			votersPuzzle = puzzleConsoleUI.getHallOfFame().votersCounter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\n\nMinesweeper:");
		System.out.println("Avarage Rating: " + avarageMines + "\t Voters Counter: " + votersMines + "\n");
		System.out.println("Sliding Puzzle:");
		System.out.println("Avarage Rating: " + avaragePuzzle + "\t Voters Counter: " + votersPuzzle + "\n");
		
		System.out.println("\nChoose Game: ");
		System.out.println("[A] - for MINESWEEPER");
		System.out.println("[B] - for SLIDING PUZZLE");

		String selection = sc.next().toUpperCase();
		switch (selection) {
		case "A":
			mineConsoleUI.play();
			break;

		case "B":
			puzzleConsoleUI.play();
			break;

		default:
			break;
		}
	}

}
