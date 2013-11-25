package cn.edu.sjtu.se.dclab.haiercloud.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dclab.haiercloud.web.dao.IVirtualMachineDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dclab.haiercloud.web.service.IVirtualMachineService;

@Service("virtualMachineService")
@Transactional
public class VirtualMachineServiceImpl implements IVirtualMachineService {

	// Properties
	@Resource(name = "virtualMachineDao")
	private IVirtualMachineDao dao;

	public void addVirtualMachine(VirtualMachine vm) {
		dao.save(vm);
	}

	public void updateVirtualMachine(VirtualMachine vm) {
		dao.update(vm);
	}

	public VirtualMachine getVirtualMachineById(long id) {
		return dao.queryById(id);
	}

	public VirtualMachine getVirtualMachineByName(String name) {
		return dao.queryByName(name);
	}

	public List<VirtualMachine> getVirtualMachineList() {
		return dao.queryAll();
	}

	public long totalNumber() {
		return dao.rowCount();
	}

	public List<VirtualMachine> getByPage(int pageNum, int pageSize) {
		return dao.queryByPage(pageNum, pageSize);
	}

	public List<VirtualMachine> queryUnusedVM() {
		return dao.queryUnusedVM();
	}
}
