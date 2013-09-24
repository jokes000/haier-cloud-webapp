package cn.edu.sjtu.se.dslab.haiercloud.web.auth;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IGroupDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.dao.IPermDao;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Group;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Perm;

/**
 * use spring {@link FactoryBean} to dynamically create shiro's permission
 * 
 * @author smile
 *
 */
@Component("chainDefinitionSectionMetaSource")
@Transactional
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

	@Resource(name = "permDao")
	private IPermDao permDao;
	
	@Resource(name = "groupDao")
	private IGroupDao groupDao;
	
	private String filterChainDefinitions;
	
	
	public Section getObject() throws Exception {
		
		// get all permissions
		List<Perm> permsList = permDao.queryAll();
		//List<Group> groupsList = groupDao.queryAll();
		
		Ini ini = new Ini();
		// load default urls
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		// 循环Perm的url，逐个添加到section中，section就是filterChainDefinitionMap，
		// 里面的键就是链接URL，值为存在什么样的条件才能访问该链接
		for (Iterator<Perm> it = permsList.iterator(); it.hasNext(); ) {
			Perm perm = it.next();
			// 如果不为空值添加到section中。
			if (StringUtils.isNotEmpty(perm.getValue()) && StringUtils.isNotEmpty(perm.getPermission())) {
				section.put(perm.getValue(), perm.getPermission());
			}
		}
		
		// 循环数据库组的url
//		for (Iterator<Group> it = groupsList.iterator(); it.hasNext(); ) {
//			Group group = it.next();
//			if (StringUtils.isNotEmpty(group.getValue()) && StringUtils.isNotEmpty(group.getRole())) {
//				for (Perm perm : group.getPermsList()) {
//					section.put(perm.getValue(), perm.getPermission());
//				}
//			} 
//		}
		
		return section;
	}

	/**
	 * 通过filterChainDefinitions对默认的url的过滤定义
	 * 
	 * @param filterChainDefinitions 默认的url过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}
	
	public Class<?> getObjectType() {
		return this.getClass();
	}

	public boolean isSingleton() {
		return false;
	}


}
