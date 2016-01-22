package com.ness.GameStudio.score;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.ness.GameStudio.score.entities.Feedback;
import com.ness.GameStudio.score.entities.Score;

//@Component
public class HallOfFameHibernate extends HallOfFame {

	public HallOfFameHibernate(String game) {
		super(game);
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public void addScore(String name, int time) throws Exception {
		entityManager.persist(new Score(name, time, getGame()));
	}

	@Transactional
	@Override
	public List<Score> loadScore() throws Exception {
		return entityManager.createQuery("select s from Score s where s.game = :name order by time", Score.class)
				.setParameter("name", getGame()).getResultList();
	}

	@Transactional
	@Override
	public void addFeedback(String name, String comment, int rating) throws Exception {
		if(rating>=1 && rating<=10)
			entityManager.persist(new Feedback(name, getGame(), comment, rating));
	}

	@Transactional
	@Override
	public List<Feedback> loadFeedbacks() throws Exception {
		return entityManager.createQuery("select f from Feedback f where game = :name", Feedback.class)
				.setParameter("name", getGame()).getResultList();
	}

	@Transactional
	@Override
	public double avarageRating() throws Exception {
		Query q = entityManager.createQuery("select avg(f.rating) from Feedback f where f.game = :game").setParameter("game", getGame());
		Double result = (Double) q.getSingleResult();
		if (result==null)
			return 0d;
		else 
			return result;
	}
	
	 @Transactional
	 @Override
	 public Long votersCounter() throws Exception {
		 Query q = entityManager.createQuery("select count(f) from Feedback f where f.game = :game").setParameter("game", getGame());
		 Long result = (Long) q.getSingleResult();
			if (result==null)
				return 0L;
			else 
				return result;
		 
	 }
	
}
