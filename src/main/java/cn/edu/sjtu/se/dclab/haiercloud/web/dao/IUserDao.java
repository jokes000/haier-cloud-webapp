package cn.edu.sjtu.se.dclab.haiercloud.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.User;

public interface IUserDao {
	
	public void save(User user);

    public void update(User user);
    
    public long rowCount();

    public User queryById(long id);
    
    public User queryByUserName(String username);

    public void delete(User user);

    public void deleteById(long id);

    public List<User> queryAll();
    
    public List<User> queryByPage(int pageNum, int pageSize);

}
