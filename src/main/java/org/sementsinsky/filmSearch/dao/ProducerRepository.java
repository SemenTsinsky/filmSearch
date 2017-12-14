package org.sementsinsky.filmSearch.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sementsinsky.filmSearch.entities.Film;
import org.sementsinsky.filmSearch.entities.Producer;
import java.util.List;

public class ProducerRepository extends Repository<Producer> {

    ProducerRepository(Class<Producer> type){
        super(type);
    }

    public List<Film> getFilms(Producer producer){
        Session session = openSession();

        Criteria criteria = session.createCriteria(Film.class);
        criteria.add(Restrictions.eq("producer_id",producer.getId()));
        List<Film> films = criteria.list();

        closeSession();
        return films;
    }
}
