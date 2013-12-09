package cn.edu.sjtu.se.dclab.haiercloud.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dclab.haiercloud.web.dao.IVirtualMachineDao;
import cn.edu.sjtu.se.dclab.haiercloud.web.entity.VirtualMachine;
import cn.edu.sjtu.se.dclab.haiercloud.web.monitor.GangliaPropertyProvider;
import cn.edu.sjtu.se.dclab.haiercloud.web.monitor.StreamProvider;
import cn.edu.sjtu.se.dclab.haiercloud.web.monitor.URLStreamProvider;
import cn.edu.sjtu.se.dclab.haiercloud.web.monitor.VMStatus;
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
	
	public void deleteVirtualMachine(VirtualMachine vm){
		dao.delete(vm);
	}
	
	public void removeVirtualMachineFromCluster(VirtualMachine vm){
		dao.update(null);
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
		List<VirtualMachine> vms = dao.queryAll();
		List<VirtualMachine> ret = new ArrayList<VirtualMachine>();
		
		for (VirtualMachine vm : vms) {
			if (vm.getCluster() == null) {
				ret.add(vm);
			}
		}
		
		return ret; 
	}

	public List<VMStatus> getStatusList(List<VirtualMachine> list) throws Exception {
		StreamProvider metricProvider = new URLStreamProvider(1500, 10000,
				"ssl.trustStore.path", "ssl.trustStore.password",
				"ssl.trustStore.type");
		GangliaPropertyProvider gp = new GangliaPropertyProvider(metricProvider);
		//List<VirtualMachine> list = vmService.getByPage(page, pageSize);
		
		List<VMStatus> statusList = new ArrayList<VMStatus>();
		for (VirtualMachine vm : list) {
			System.out.println("Server name is:" + vm.getName());
			VMStatus status = gp.getMetrics(vm.getName());
			
			status.setName(vm.getName());
			status.setIp(vm.getIp());
			status.setCpu(vm.getCpu());
			status.setMemory(vm.getMemory() + "");
			status.setStorage(vm.getStorage() + "");
			status.setBoardWidth(vm.getBoardWidth() + "");
			statusList.add(status);
		}
		
		return statusList;
	}

	public VMStatus getStatus(VirtualMachine vm) throws Exception {
		StreamProvider metricProvider = new URLStreamProvider(1500, 10000,
				"ssl.trustStore.path", "ssl.trustStore.password",
				"ssl.trustStore.type");
		GangliaPropertyProvider gp = new GangliaPropertyProvider(metricProvider);
		
		VMStatus status = gp.getMetrics(vm.getName());
		
		status.setName(vm.getName());
		status.setIp(vm.getIp());
		status.setCpu(vm.getCpu());
		status.setMemory(vm.getMemory() + "");
		status.setStorage(vm.getStorage() + "");
		status.setBoardWidth(vm.getBoardWidth() + "");
		
		return status;
	}

	public List<VMStatus> getStatusList(Set<VirtualMachine> list)
			throws Exception {
		StreamProvider metricProvider = new URLStreamProvider(1500, 10000,
				"ssl.trustStore.path", "ssl.trustStore.password",
				"ssl.trustStore.type");
		GangliaPropertyProvider gp = new GangliaPropertyProvider(metricProvider);
		//List<VirtualMachine> list = vmService.getByPage(page, pageSize);
		
		List<VMStatus> statusList = new ArrayList<VMStatus>();
		for (VirtualMachine vm : list) {
			System.out.println("Server name is:" + vm.getName());
			VMStatus status = gp.getMetrics(vm.getName());
			
			status.setName(vm.getName());
			status.setIp(vm.getIp());
			status.setCpu(vm.getCpu());
			status.setMemory(vm.getMemory() + "");
			status.setStorage(vm.getStorage() + "");
			status.setBoardWidth(vm.getBoardWidth() + "");
			statusList.add(status);
		}
		
		return statusList;
	}
}
