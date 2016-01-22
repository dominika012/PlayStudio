package com.ness.GameStudio.score;

import java.util.ArrayList;
import java.util.List;

import com.ness.GameStudio.score.entities.Feedback;
import com.ness.GameStudio.score.entities.Score;

public abstract class HallOfFame {
	private String game;
	
	public HallOfFame(String game) {
		this.game = game;
	}

	public abstract void addScore(String name, int time) throws Exception;

	public abstract List<Score> loadScore() throws Exception;

	public abstract void addFeedback(String name, String comment, int rating) throws Exception;
	
	public abstract List<Feedback> loadFeedbacks() throws Exception;
	
	public abstract double avarageRating() throws Exception;
	
	public abstract Long votersCounter() throws Exception;
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		List<Score> scores;
		try {
			scores = loadScore();
		} catch (Exception e) {
			scores = new ArrayList<Score>();
		}

		sb.append("\n SCORES \n");
		
		int index = 1;
		for (Score score : scores) {
			sb.append(index + ". " + score.getName() + " " + score.getTime() + "\n");
			index++;
		}
		
		
		sb.append("\n\n FEEDBACK \n");
		
		List<Feedback> feedbacks;
		try {
			feedbacks = loadFeedbacks();
		} catch (Exception e) {
			feedbacks = new ArrayList<Feedback>();
		}

		for (Feedback feedback : feedbacks) {
			sb.append("  User name:  " + feedback.getName() + "\t Comment:  " + feedback.getComment() + "\t Rating:  " + feedback.getRating() + "\n");
		}
		return sb.toString();
	}
	
	
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}

}