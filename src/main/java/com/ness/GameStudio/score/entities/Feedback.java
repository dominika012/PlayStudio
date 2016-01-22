package com.ness.GameStudio.score.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "feedback")
public class Feedback implements Serializable{
	private static long serialVersionUID = 1L;

	//, Comparable<Feedback>
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	private String game;
	
	private String comment;
	
	private int rating;
	
	public Feedback(){
	}
	
	public Feedback(String name, String game, String comment, int rating) {
		super();
		this.game=game;
		this.name = name;
		this.comment = comment;
		this.rating = rating;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}
/*
	@Override
	public int compareTo(Feedback o) {
		return rating - o.rating;
	}
*/
	@Override
	public String toString() {
		return "Feedback [name=" + name + ", comment=" + comment + ", rating=" + rating + "]";
	}
	
	
	
}
