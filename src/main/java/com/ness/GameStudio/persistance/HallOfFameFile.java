package com.ness.GameStudio.persistance;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ness.GameStudio.score.HallOfFame;
import com.ness.GameStudio.score.entities.Feedback;
import com.ness.GameStudio.score.entities.Score;

public class HallOfFameFile extends HallOfFame {
	private final String SCORES_FILE = System.getProperty("user.home") + "/mnsw.scores";

	public HallOfFameFile(String game) {
		super(game);
	}

	public void addScore(String name, int time) {
		try {
			List<Score> scores = loadScore();
			scores.add(new Score(name, time, getGame()));
			Collections.sort(scores);
			save(scores);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void save(List<Score> scores) throws IOException {
		File file = new File(SCORES_FILE);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(scores);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Score> loadScore() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(SCORES_FILE);
		if (file.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
				return (List) ois.readObject();
			}
		}
		return new ArrayList<Score>();

	}

	@Override
	public void addFeedback(String name, String comments, int rating) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Feedback> loadFeedbacks() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double avarageRating() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long votersCounter() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
