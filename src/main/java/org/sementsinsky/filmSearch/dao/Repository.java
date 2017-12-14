package org.sementsinsky.filmSearch.dao;

import java.lang.Integer;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.sementsinsky.filmSearch.entities.IEntity;
import org.sementsinsky.filmSearch.utils.HibernateUtil;

import javax.persistence.Query;

public class Repository<T extends IEntity> implements IRepository<T> {
    private Session session;
    private Transaction transaction;
    private Class<T> type;

    public Repository(Class<T> type){
        this.type = type;
    }

    private Session openSession(){
        session = HibernateUtil.buildSessionAnnotation();
        return session;
    }

    public Session openSessionWithTransaction(){
        session = HibernateUtil.buildSessionAnnotation();
        this.transaction = session.beginTransaction();
        return session;
    }

    private void closeSession(){
        session.close();
    }

    public void closeSessionWithTransaction(){
        transaction.commit();
        session.close();
    }

    public void persist(T entity){
        session.save(entity);
    }

    public T getByValue(String field, String value){
        Criteria criteria = session.createCriteria(type);
        criteria.add(Restrictions.eq(field,value));
        return (T) criteria.uniqueResult();
    }

    public List<T> getASetByValue(String field, String value){
        Criteria criteria = session.createCriteria(type);
        criteria.add(Restrictions.eq(field,value));
        return criteria.list();
    }

    public void update(T entity){
        session.update(entity);
    }

    public List<T> getAll() {
        return session.createCriteria(type).list();
    }

    public T getById(UUID id){
        return (T) session.get(type, id);
    }

    public void delete(T entity){
        session.delete(entity);
    }
}
