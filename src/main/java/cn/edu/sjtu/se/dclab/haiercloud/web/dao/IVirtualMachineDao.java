/* ===================================
 * author: Huiyi Li 
 * Last modified time: 2013-8-12 17:06
 * version: 0.0.1
 * ===================================
 */
package cn.edu.sjtu.se.dclab.haiercloud.web.dao;

import java.util.List;

import cn.edu.sjtu.se.dclab.haiercloud.web.entity.VirtualMachine;

public interface IVirtualMachineDao {

    public void save(VirtualMachine vm);

    public void update(VirtualMachine vm);
    
    public long rowCount();

    public VirtualMachine queryById(long id);
    
    public VirtualMachine queryByName(String name);

    public void delete(VirtualMachine vm);

    public void deleteById(long id);

    public List<VirtualMachine> queryAll();
    
    public List<VirtualMachine> queryByPage(int pageNum, int pageSize);
    
    public List<VirtualMachine> queryUnusedVM();
}