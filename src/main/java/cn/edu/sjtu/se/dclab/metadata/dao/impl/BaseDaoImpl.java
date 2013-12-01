package cn.edu.sjtu.se.dclab.metadata.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dclab.metadata.dao.intf.BaseDao;

@Repository("metaDataBaseDao")
public class BaseDaoImpl<T extends Serializable> implements BaseDao<T> {

	// constructor
    public BaseDaoImpl() {
    }

    @Autowired
	private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    // operations
    // create
    public void save(T t) {
        getSession().save(t);
    }

    // update
    public void update(T t) {
        getSession().update(t);
    }

    // delete
    public void deleteById(Class<T> clazz, long id) {
        getSession().delete(queryById(clazz, id));
    }

    public void delete(T t) {
        getSession().delete(t);
    }

    // query

    public long rowCount(Class<T> clazz) {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setProjection(Projections.rowCount());
        List list = criteria.list();
        long count = (Long)list.get(0);
        return count;
    }
    
    @SuppressWarnings("unchecked")
    public T queryById(Class<T> clazz, long id) {
        return (T) getSession().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> queryByProperty(Class<T> clazz, String property, Object value) {
        return (List<T>) getSession().createCriteria(clazz)
                .add(Restrictions.eq(property, value)).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> queryAll(Class<T> clazz) {
        return (List<T>) getSession().createCriteria(clazz).list();
    }

    @SuppressWarnings("unchecked")
	public T queryObjectBySql(Class<T> clazz, String sql) {
        T object = (T) getSession().createSQLQuery(sql).addEntity(clazz)
                .uniqueResult();

        return object;
    }

    @SuppressWarnings("unchecked")
	public List<T> queryAllBySql(Class<T> clazz, String sql) {
        return (List<T>) getSession().createSQLQuery(sql).list();
    }
}
