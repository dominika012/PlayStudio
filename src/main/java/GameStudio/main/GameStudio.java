package GameStudio.main;

import java.util.Scanner;

import minesweeper.consoleui.MineConsoleUI;
import slidingPuzzle.consoleui.PuzzleConsoleUI;

public class GameStudio {

	private MineConsoleUI mineConsoleUI;
	private PuzzleConsoleUI puzzleConsoleUI;
	
	
	public MineConsoleUI getMui() {
		return mineConsoleUI;
	}

	public void setMui(MineConsoleUI mui) {
		this.mineConsoleUI = mui;
	}

	public PuzzleConsoleUI getPui() {
		return puzzleConsoleUI;
	}

	public void setPui(PuzzleConsoleUI pui) {
		this.puzzleConsoleUI = pui;
	}


	static final Scanner sc = new Scanner(System.in);

	public void play() {
		System.out.println("Choose Game: ");
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
