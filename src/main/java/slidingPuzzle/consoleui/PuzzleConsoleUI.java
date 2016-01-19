package slidingPuzzle.consoleui;

import java.util.Scanner;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import GameStudio.score.GameState;
import GameStudio.score.HallOfFame;
import slidingPuzzle.core.Position;
import slidingPuzzle.core.PuzzleField;

public class PuzzleConsoleUI {
	
	private PuzzleField field;
	
	private HallOfFame hallOfFame;
	
	private static final Pattern INPUT_PATTERN = Pattern.compile("([4|6|8|2])");	
	

	public PuzzleConsoleUI(PuzzleField field) {
		this.field = field;
	}

	public void setHallOfFame(HallOfFame hallOfFame) {
		this.hallOfFame = hallOfFame;
	}

	public void play() {
		
		//rows and columns are mapped at spring-context.xml
		
		/*System.out.println("Input amount of rows:");
		int row = new Scanner(System.in).nextInt();
		System.out.println("Input amount of columns:");
		int column = new Scanner(System.in).nextInt();
		*/
		
		show();
		while(!field.isSolved()) {
			processInput();
			show();
		}
		show();

		if (field.isSolved()) {
			String name = System.getProperty("user.name");
			try {
				hallOfFame.addScore(name, field.getPlayingSeconds());
				hallOfFame.loadScore();
			} catch (Exception e) {
				System.err.println("Can not save score...");
				e.printStackTrace();
			}

			System.out.println("You WIN! :) ");
			System.out.println(hallOfFame);
		} else {
			System.err.println("You LOOSE :( ");
		}

	}

	private void processInput() {
		System.out.println("......   <4> left,  <6> right,  <8> up,  <2> down");
		String line = new Scanner(System.in).nextLine().toUpperCase();
		
		if("X".equals(line)) {
			System.exit(0);
		}
		Matcher matcher = INPUT_PATTERN.matcher(line);
		if(matcher.matches()) {
			int input = Integer.parseInt(matcher.group(1));
			Position position = field.getZeroPosition(); 
			field.move(position.getRow(), position.getColumn(),input);
			
		} else {
			System.out.println("Bad input...");
		}
	}
	public void show() {
		showHeader();		
		showField();
		
	}

	private void showField() {
		System.out.println();	
		
		for(int row = 0; row < field.getRowCount(); row++) {
			for(int column = 0; column < field.getColumnCount(); column++) {
				System.out.print(field.getCell(row, column) + "\t");
			}
			
			System.out.println();						
		}
		System.out.println();	
	}

	private void showHeader() {
		System.out.println("Time: " + field.getPlayingSeconds() + " s");
	}
}
