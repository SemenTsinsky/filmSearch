package org.sementsinsky.filmSearch.dao;

import java.lang.Integer;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.sementsinsky.filmSearch.entities.IEntity;
import org.sementsinsky.filmSearch.utils.HibernateUtil;

import javax.persistence.Query;

public class Repository<T extends IEntity> implements IRepository<T, Integer> {
    private Session session;
    private Transaction transaction;
    private Class<T> type;

    public Repository(Class<T> type){
        this.type = type;
    }

    public Session OpenSession(){
        session = HibernateUtil.buildSessionAnnotation();
        return session;
    }

    public Session openSessionWithTransacion(){
        session = HibernateUtil.buildSessionAnnotation();
        this.transaction = session.beginTransaction();
        return session;
    }

    public void closeSession(){
        session.close();
    }

    public void closeSessionWithTransaction(){
        transaction.commit();
        session.close();
    }

    public Session getSession(){
        return session;
    }

    public void setSession(Session session){
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction){
        this.transaction = transaction;
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
//        ClassMetadata hibernateMetaData = session.getSessionFactory().getClassMetadata(type);
//        AbstractEntityPersister persister = (AbstractEntityPersister) hibernateMetaData;
//        String tableName = persister.getTableName();
//        Query query = session.createQuery("from "+tableName);
        return session.createCriteria(type).list();
    }

    public T getById(Integer id){
        return (T) session.get(type, id);
    }

    public void delete(T entity){
        session.delete(entity);
    }
}
