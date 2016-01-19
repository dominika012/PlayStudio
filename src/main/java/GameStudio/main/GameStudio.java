package GameStudio.main;

import java.util.Scanner;

import fifteenPuzle.consoleui.PuzzleConsoleUI;
import minesweeper.consoleui.MineConsoleUI;
import pexeso.consoleui.PexesoConsoleUI;

public class GameStudio {

	private MineConsoleUI mui;
	private PuzzleConsoleUI pui;
	private PexesoConsoleUI peui;
	
	

	public MineConsoleUI getMui() {
		return mui;
	}

	public void setMui(MineConsoleUI mui) {
		this.mui = mui;
	}

	public PuzzleConsoleUI getPui() {
		return pui;
	}

	public void setPui(PuzzleConsoleUI pui) {
		this.pui = pui;
	}

	public PexesoConsoleUI getPeui() {
		return peui;
	}

	public void setPeui(PexesoConsoleUI peui) {
		this.peui = peui;
	}

	static final Scanner sc = new Scanner(System.in);

	public void play() {
		System.out.println("Choose Game: ");
		System.out.println("[A] - for MINESWEEPER");
		System.out.println("[B] - for FIFTEEN PUZZLE");
		System.out.println("[C] - for PEXESO");

		String selection = sc.next().toUpperCase();
		switch (selection) {
		case "A":
			mui.play();
			break;

		case "B":
			pui.play();
			break;

		case "C":
			peui.play();
			break;

		default:
			break;
		}
	}

}