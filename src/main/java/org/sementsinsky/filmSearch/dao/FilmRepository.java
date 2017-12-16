package org.sementsinsky.filmSearch.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sementsinsky.filmSearch.entities.Film;
import org.sementsinsky.filmSearch.entities.Mark;
import java.util.List;

public class FilmRepository extends Repository<Film>{

    public FilmRepository(Class<Film> type) {
        super(type);
    }

    public void updateRating(Film film){
        Session session = openSession();

        Criteria criteria = session.createCriteria(Mark.class);
        criteria.add(Restrictions.eq("film",film.getId()));

        List<Mark> marks = criteria.list();

        double rating = 0;
        int i = 0;
        for (Mark mark:marks) {
            rating += mark.getMark();
            i++;
        }
        rating /= i;
        film.setRating(rating);
        closeSession();
        update(film);
    }

}
