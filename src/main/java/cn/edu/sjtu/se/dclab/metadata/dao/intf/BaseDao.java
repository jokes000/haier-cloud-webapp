package cn.edu.sjtu.se.dclab.metadata.dao.intf;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T extends Serializable> {

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
    
    // query according to property
    public List<T> queryByProperty(Class<T> clazz, String property, Object value);
    
    //query all
    public long rowCount(Class<T> clazz);
    
    public List<T> queryAll(Class<T> clazz);

    public List<T> queryAllBySql(Class<T> clazz, String sql);

    public T queryObjectBySql(Class<T> clazz, String sql);

}
