package com.ness.Game.minesweeper.consoleui;

import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ness.Game.minesweeper.core.Clue;
import com.ness.Game.minesweeper.core.MineField;
import com.ness.Game.minesweeper.core.Tile;
import com.ness.GameStudio.score.GameState;
import com.ness.GameStudio.score.HallOfFame;

public class MineConsoleUI {
	private MineField field;

	private HallOfFame hallOfFame;

	private String name;
	
	private static final Pattern INPUT_PATTERN = Pattern.compile("([O|M])([A-I])([0-8])");

	public MineConsoleUI(MineField field) {
		this.field = field;
		this.name = System.getProperty("user.name");
	}

	public void setHallOfFame(HallOfFame hallOfFame) {
		this.hallOfFame = hallOfFame;
	}

	public HallOfFame getHallOfFame() {
		return hallOfFame;
	}

	public void play() {
		
		show();
		while (field.getState() == GameState.PLAYING) {
			processInput();
			show();
		}
		show();
		if (field.getState() == GameState.SOLVED) {
			try {
				hallOfFame.addScore(name, field.getPlayingSeconds());
				hallOfFame.loadScore();
			} catch (Exception e) {
				System.err.println("Can not save score...");
				e.printStackTrace();
			}
			System.err.println("You WIN! :) ");
		} else 
			System.err.println("You LOOSE! :( ");
		
		inputFeedback();
		System.out.println(hallOfFame);
		System.exit(0);
	}

	
	@SuppressWarnings("resource")
	private void inputFeedback(){
		System.out.println("Feedback:");
		System.out.println(" <1> add feedback,  <X> close program");
		String line = new Scanner(System.in).nextLine().toUpperCase();
		if ("X".equals(line)) {
			System.exit(0);
		}
		else if("1".equals(line)){
			try {
				addFeedbackInput();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
			System.out.println("Bad input, try it again");
		
	}
	
	@SuppressWarnings("resource")
	private void addFeedbackInput(){
		System.out.println("Input the comment:");
		String comment = new Scanner(System.in).nextLine();
		System.out.println("Input rating:");
		int rating = new Scanner(System.in).nextInt();
		try {
			if(rating>=1 && rating<=10)
				hallOfFame.addFeedback(name, comment, rating);
			else
				System.out.println("rating should in range 1-10 !");
		} catch (Exception e) {
			System.out.println("Can not add the Feedback");
			e.printStackTrace();
		}
	}
	
	private void processInput() {
		System.out.println("Input");
		@SuppressWarnings("resource")
		String line = new Scanner(System.in).nextLine().toUpperCase();
		if ("X".equals(line)) {
			System.exit(0);
		}
		Matcher matcher = INPUT_PATTERN.matcher(line);
		if (matcher.matches()) {
			int row = matcher.group(2).charAt(0) - 'A';
			int column = Integer.parseInt(matcher.group(3));
			if ("O".equals(matcher.group(1))) {
				field.openTile(row, column);
			} else {
				field.markTile(row, column);
			}
		} else {
			System.out.println("Bad input, try it again");
		}
	}

	public void show() {
		showHeader();
		showField();
	}

	private void showField() {
		for (int row = 0; row < field.getRowCount(); row++) {
			System.out.print((char) (row + 'A'));

			for (int column = 0; column < field.getColumnCount(); column++) {
				System.out.print(" ");

				Tile tile = field.getTile(row, column);
				switch (tile.getState()) {
				case CLOSED:
					System.out.print("-");
					break;
				case MARKED:
					System.out.print("M");
					break;
				case OPEN:
					if (tile instanceof Clue) {
						System.out.print(((Clue) tile).getValue());
					} else {
						System.out.print("X");
					}
					break;
				default:
					break;
				}
			}

			System.out.println();
		}
	}

	private void showHeader() {
		System.out.println("Time: " + field.getPlayingSeconds() + " s");

		System.out.print(" ");
		for (int column = 0; column < field.getColumnCount(); column++) {
			System.out.print(" " + column);
		}
		System.out.println();
	}
}
