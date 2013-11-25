/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 17:06
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.edu.sjtu.se.dclab.haiercloud.web.dao.IBaseDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.dao.IVirtualMachineDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.VirtualMachine;

@Repository("virtualMachineDao")
public class VirtualMachineDaoImpl implements IVirtualMachineDao {
	// constructor
	public VirtualMachineDaoImpl() {
	}

	// import baseDao
	@Resource(name = "baseDao")
	private IBaseDao<VirtualMachine> baseDao;

	public IBaseDao<VirtualMachine> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao<VirtualMachine> baseDao) {
		this.baseDao = baseDao;
	}

	// operations
	public void save(VirtualMachine vm) {
		baseDao.save(vm);
	}

	public void update(VirtualMachine vm) {
		baseDao.update(vm);
	}

	public VirtualMachine queryById(long id) {
		return (VirtualMachine) baseDao.queryById(VirtualMachine.class, id);
	}

	
	public VirtualMachine queryByName(String name) {
		return baseDao.queryByProperty(VirtualMachine.class, "name", name).get(
				0);
	}

	public void delete(VirtualMachine vm) {
		baseDao.delete(vm);
	}

	public void deleteById(long id) {
		baseDao.deleteById(VirtualMachine.class, id);
	}

	public List<VirtualMachine> queryAll() {
		return (List<VirtualMachine>) baseDao.queryAll(VirtualMachine.class);
	}

	
	public long rowCount() {
		return baseDao.rowCount(VirtualMachine.class);
	}

	
	public List<VirtualMachine> queryByPage(int pageNum, int pageSize) {
		return baseDao.queryByPage(pageNum, pageSize, VirtualMachine.class);
	}

	
	public List<VirtualMachine> queryUnusedVM() {
		return baseDao.queryByProperty(VirtualMachine.class, "cluster", null);
	}
}