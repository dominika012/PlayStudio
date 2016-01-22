package com.ness.Game.slidingPuzzle.consoleui;

import java.util.Scanner;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ness.Game.slidingPuzzle.core.Position;
import com.ness.Game.slidingPuzzle.core.PuzzleField;
import com.ness.GameStudio.score.HallOfFame;

public class PuzzleConsoleUI {
	
	private PuzzleField field;
	
	private HallOfFame hallOfFame;
	
	private String name;
	
	private String game;
	
	private static final Pattern INPUT_PATTERN = Pattern.compile("([4|6|8|2])");	
	

	public PuzzleConsoleUI(PuzzleField field) {
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
			try {
				hallOfFame.addScore(name, field.getPlayingSeconds());
				hallOfFame.loadScore();
			} catch (Exception e) {
				System.err.println("Can not save score...");
				e.printStackTrace();
			}
			System.err.println("You WIN! :) ");
		} else {
			System.err.println("You LOOSE :( ");
		}
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
	
	//FIXME
	@SuppressWarnings("resource")
	private void addFeedbackInput(){
		System.out.println("Input the comment:");
		String comment = new Scanner(System.in).nextLine();
		System.out.println("Input rating:");
		int rating = new Scanner(System.in).nextInt();
		try {
			if(rating>=1 && rating<=10)
				hallOfFame.addFeedback(name, comment, rating);
			else{
				System.out.println("rating should in range 1-10 !");
				//return;
			}
		} catch (Exception e) {
			System.out.println("Can not add the Feedback");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
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
