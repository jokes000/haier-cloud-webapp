package cn.edu.sjtu.se.dslab.haiercloud.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Perm;

public interface IPermDao {
	
	public void save(Perm perm);

    public void update(Perm perm);
    
    public long rowCount();

    public Perm queryById(long id);
    
    public Perm queryByPermName(String name);

    public void delete(Perm perm);

    public void deleteById(long id);

    public List<Perm> queryAll();
    
    public List<Perm> queryByPage(int pageNum, int pageSize);
}
