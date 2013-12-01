package cn.edu.sjtu.se.dclab.haiercloud.web.service;

import java.util.List;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.VirtualMachine;

public interface IVirtualMachineService {
    public void addVirtualMachine(VirtualMachine vm);
    
    public void deleteVirtualMachine(VirtualMachine vm);
    
    public void removeVirtualMachineFromCluster(VirtualMachine vm);

    public void updateVirtualMachine(VirtualMachine vm);

    public VirtualMachine getVirtualMachineById(long id);

    public VirtualMachine getVirtualMachineByName(String name);

    public List<VirtualMachine> getVirtualMachineList();
    
    public long totalNumber();
	
	public List<VirtualMachine> getByPage(int pageNum, int pageSize);
	
	public List<VirtualMachine> queryUnusedVM();
}
