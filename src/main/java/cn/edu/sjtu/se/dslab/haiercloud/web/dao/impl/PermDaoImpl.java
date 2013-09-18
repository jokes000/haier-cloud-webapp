package cn.edu.sjtu.se.dslab.haiercloud.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IBaseDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IPermDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Perm;

@Repository("permDao")
public class PermDaoImpl implements IPermDao {

	@Resource(name = "baseDao")
	IBaseDao<Perm> baseDao;
	
	public void save(Perm perm) {
		baseDao.save(perm);
	}

	public void update(Perm perm) {
		baseDao.update(perm);
	}

	public long rowCount() {
		return baseDao.rowCount(Perm.class);
	}

	public Perm queryById(long id) {
		return baseDao.queryById(Perm.class, id);
	}

	public Perm queryByPermName(String name) {
		return baseDao.queryByProperty(Perm.class, "permission", name).get(0);
	}

	public void delete(Perm perm) {
		baseDao.delete(perm);
	}

	public void deleteById(long id) {
		baseDao.deleteById(Perm.class, id);
	}

	public List<Perm> queryAll() {
		return baseDao.queryAll(Perm.class);
	}

	public List<Perm> queryByPage(int pageNum, int pageSize) {
		return baseDao.queryByPage(pageNum, pageSize, Perm.class);
	}

}
