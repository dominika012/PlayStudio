package GameStudio.score;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

//@Component
public class HallOfFameHibernate extends HallOfFame {

	public HallOfFameHibernate(String game) {
		super(game);
	}

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void addScore(String name, int time) throws Exception {
		em.persist(new UserScore(name, time, getGame()));

	}

	@Transactional
	@Override
	public List<UserScore> loadScore() throws Exception {
		return em.createQuery("select s from UserScore s where s.game = :name order by time", UserScore.class)
				.setParameter("name", getGame()).getResultList();
	}

}
