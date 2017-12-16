package org.sementsinsky.filmSearch.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sementsinsky.filmSearch.entities.Film;
import org.sementsinsky.filmSearch.entities.Mark;
import org.sementsinsky.filmSearch.entities.User;

import java.util.List;
import java.util.Set;

public class UserRepository extends Repository<User>{

    public UserRepository(Class<User> type) {
        super(type);
    }

    public List<Mark> getMarks(User user){
        Session session = openSession();
        Criteria criteria = session.createCriteria(Mark.class);
        criteria.add(Restrictions.eq("user",user.getId()));
        List<Mark> marks = criteria.list();
        closeSession();
        return marks;
    }

    public boolean markFilm(Mark mark){
        Repository<Mark> repository = new Repository<>(Mark.class);
        Session session = repository.openSessionWithTransaction();
        session.persist(mark);
        repository.closeSessionWithTransaction();
        return true;
    }

    public void deleteMark(Mark mark){
        Repository<Mark> repository = new Repository<>(Mark.class);
        Session session = repository.openSessionWithTransaction();
        session.delete(mark);
        repository.closeSessionWithTransaction();
    }

    public void holdOver(User user, Film film){
        Session session = openSessionWithTransaction();
        user.addHoldOver(film);
        session.update(user);
        closeSessionWithTransaction();
    }

    public Set<Film> getHoldOverSet(User user){
        openSession();
        Set<Film> holdOver = user.getHoldOver();
        closeSession();
        return holdOver;
    }

    public void delFromHoldOver(User user, Film film){
        openSessionWithTransaction();
        user.delHoldOver(film);
        closeSessionWithTransaction();
    }
}
