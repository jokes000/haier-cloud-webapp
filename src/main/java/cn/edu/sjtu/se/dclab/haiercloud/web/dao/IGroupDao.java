package cn.edu.sjtu.se.dclab.haiercloud.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.Group;

public interface IGroupDao {

	public void save(Group group);

    public void update(Group group);
    
    public long rowCount();

    public Group queryById(long id);

    public void delete(Group Group);

    public void deleteById(long id);

    public List<Group> queryAll();
    
    public List<Group> queryByPage(int pageNum, int pageSize);
}
