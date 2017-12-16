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

    protected Session openSession(){
        session = HibernateUtil.buildSessionAnnotation();
        return session;
    }

    protected Session openSessionWithTransaction(){
        session = HibernateUtil.buildSessionAnnotation();
        this.transaction = session.beginTransaction();
        return session;
    }

    protected void closeSession(){
        session.close();
    }

    protected void closeSessionWithTransaction(){
        transaction.commit();
        session.close();
    }

    public void persist(T entity){
        openSessionWithTransaction();
        session.persist(entity);
        closeSessionWithTransaction();
    }

    public T getByValue(String field, String value){
        openSession();
        Criteria criteria = session.createCriteria(type);
        criteria.add(Restrictions.eq(field,value));
        T object = (T) criteria.uniqueResult();
        closeSession();
        return object;
    }

    public List<T> getASetByValue(String field, String value){
        openSession();
        Criteria criteria = session.createCriteria(type);
        criteria.add(Restrictions.eq(field,value));
        List<T> objects = criteria.list();
        closeSession();
        return objects;
    }

    public void update(T entity){
        openSessionWithTransaction();
        session.update(entity);
        closeSessionWithTransaction();
    }

    public List<T> getAll() {
        openSession();
        List<T> objects = session.createCriteria(type).list();
        closeSession();
        return objects;
    }

    public T getById(UUID id){
        openSession();
        T object = (T) session.get(type, id);
        closeSession();
        return object;
    }

    public void delete(T entity){
        openSessionWithTransaction();
        session.delete(entity);
        closeSessionWithTransaction();
    }
}
