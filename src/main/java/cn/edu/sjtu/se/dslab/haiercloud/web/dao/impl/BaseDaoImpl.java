/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 16:40
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dslab.haiercloud.web.dao.impl;

// Utils
import java.io.Serializable;
import java.util.List;


// Add hibernate support
import static org.hibernate.criterion.Restrictions.eq;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
// Add Spring support
import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IBaseDao;

//Add user-defined bean
import javax.annotation.Resource;

@Repository("baseDao")
public class BaseDaoImpl<T extends Serializable> implements IBaseDao<T> {
    // constructor
    public BaseDaoImpl() {
    }

    // Properties
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    // getters and setters
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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
    public List<T> queryByProperty(Class<T> clazz, String property, String value) {
        return (List<T>) getSession().createCriteria(clazz)
                .add(eq(property, value)).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> queryAll(Class<T> clazz) {
        return (List<T>) getSession().createCriteria(clazz).list();
    }

    public T queryObjectBySql(Class<T> clazz, String sql) {
        T object = (T) getSession().createSQLQuery(sql).addEntity(clazz)
                .uniqueResult();

        return object;
    }

    public List<T> queryAllBySql(Class<T> clazz, String sql) {
        return (List<T>) getSession().createSQLQuery(sql).list();
    }

    @SuppressWarnings("unchecked")
    public List<T> queryByPage(int pageNum, int pageSize, Class<T> clazz) {
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.setFirstResult( (pageNum-1) * pageSize);
        criteria.setMaxResults(pageSize);
        
        return (List<T>)criteria.list();
    }
}