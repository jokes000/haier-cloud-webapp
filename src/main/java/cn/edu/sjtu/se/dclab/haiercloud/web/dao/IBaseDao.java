/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 13:25
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T extends Serializable> {
    // create
    public void save(T t);
    
    // delete
    public void delete(T t);
    
    public void deleteById(Class<T> clazz, long id);
  
    // update
    public void update(T t);

    // query
    // query according to id
    public T queryById(Class<T> clazz, long id);
    
    // query according to property(seems only to support String)
    public List<T> queryByProperty(Class<T> clazz,
                                                           String property,
                                                           String value);
    
    //query all
    public long rowCount(Class<T> clazz);
    
    public List<T> queryAll(Class<T> clazz);

    /**
    * query objects according to the condition
    *
    * @param hql
    * @return
    */
    public List<T> queryAllBySql(Class<T> clazz, String sql);

    /**
    * query object according to condition
    *
    * @param hql
    * @return
    */
    public T queryObjectBySql(Class<T> clazz, String sql);
    
    /**
     * query objects according to pagination
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<T> queryByPage(int pageNum, int pageSize, Class<T> clazz);
}