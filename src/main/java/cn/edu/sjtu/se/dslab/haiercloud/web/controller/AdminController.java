package cn.edu.sjtu.se.dslab.haiercloud.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Group;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.Perm;
import cn.edu.sjtu.se.dslab.haiercloud.web.entity.User;
import cn.edu.sjtu.se.dslab.haiercloud.web.service.IAuthService;

@Controller
public class AdminController {

	/**
	 * Properties
	 */
	@Resource(name = "authService")
	private IAuthService authService;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminHome() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin");

		return mav;
	}

	@RequestMapping(value = "/admin/group", method = RequestMethod.GET)
	public ModelAndView groupView() {

		// set view
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/group");

		// business logic
		mav.addObject("groupsList", authService.getAllGroups());

		return mav;
	}
	
	@RequestMapping(value = "/admin/group/detail", method = RequestMethod.GET)
	public ModelAndView groupDetail(@PathVariable(value = "id") long id) {
		
		// set view
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/group/detail");
		
		// add object
		mav.addObject("group", authService.getGroup(id));
		
		return mav;
	}

	@RequestMapping(value = "/admin/group/add", method = RequestMethod.GET)
	public ModelAndView addGroupView() {

		// set view
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/group/add");

		// business logic
		mav.addObject("permsList", authService.getAllPerms());

		return mav;
	}

	@RequestMapping(value = "/admin/group/add/submit", method = RequestMethod.POST)
	public String addGroup(@RequestParam String name, @RequestParam long[] ids) {

		// business logic
		Group group = new Group();
		group.setName(name);

		Set<Perm> permsSet = new HashSet<Perm>();
		for (long id : ids) {
			permsSet.add(authService.getPermById(id));
		}
		group.setPermsList(permsSet);

		authService.saveGroup(group);

		return "redirect:/admin/group";
	}

	@RequestMapping(value = "/admin/group/delete", method = RequestMethod.GET)
	public ModelAndView deleteGroup() {

		return null;
	}

	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public ModelAndView userList() {

		// set view
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user");

		// business logic
		mav.addObject("usersList", authService.getAllUsers());

		return mav;
	}

	@RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
	public ModelAndView userView(@PathVariable(value = "id") long id,
			HttpSession session) {

		// set view
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user/detail");

		// business logic
		mav.addObject("user", authService.getUserById(id));

		List<Perm> permsList = authService.getAllPerms();
		session.setAttribute("permsList", permsList);
		mav.addObject("permsList", permsList);

		List<Group> groupsList = authService.getAllGroups();
		session.setAttribute("groupsList", groupsList);
		mav.addObject("groupsList", groupsList);

		return mav;
	}

	@RequestMapping(value = "/admin/user/{id}/permission/add", method = RequestMethod.POST)
	public String userAddPermission(@PathVariable(value = "id") long id,
			@RequestParam long[] ids, HttpSession session) {

		@SuppressWarnings("unchecked")
		List<Perm> permsList = (List<Perm>) session.getAttribute("permsList");

		User user = authService.getUserById(id);
		Set<Perm> oldList = user.getPermsList();

		for (long i : ids) {
			for (Perm perm : permsList) {
				if (perm.getId() == i) {
					oldList.add(perm);
					break;
				}
			}
		}

		authService.updateUser(user);

		return "redirect:/admin/user/" + id;
	}

	@RequestMapping(value = "/admin/user/{id}/group/add", method = RequestMethod.POST)
	public String userAddGroup(@PathVariable(value = "id") long id,
			@RequestParam long[] ids, HttpSession session) {

		@SuppressWarnings("unchecked")
		List<Group> groupsList = (List<Group>) session
				.getAttribute("groupsList");

		User user = authService.getUserById(id);
		Set<Group> oldList = user.getGroupsList();

		for (long i : ids) {
			for (Group group : groupsList) {
				if (group.getId() == i) {
					oldList.add(group);
					break;
				}
			}
		}

		authService.updateUser(user);

		return "redirect:/admin/user/" + id;
	}

	@RequestMapping(value = "/admin/user/add", method = RequestMethod.GET)
	public ModelAndView addUser() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user/add");

		return mav;
	}

	@RequestMapping(value = "/admin/user/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable(value = "id") long id) {

		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		authService.deleteUsers(ids);

		return "redirect:/admin/user";
	}

	@RequestMapping(value = "/admin/permission", method = RequestMethod.GET)
	public ModelAndView permissionView() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/permission");

		List<Perm> permsList = authService.getAllPerms();
		mav.addObject("permsList", permsList);

		return mav;
	}

	@RequestMapping(value = "/admin/permission/add", method = RequestMethod.POST)
	public String addPermission(@RequestParam String permission,
			@RequestParam String url) {

		// business logic
		Perm perm = new Perm();
		perm.setPermission(permission);
		perm.setValue(url);

		authService.savePerm(perm);

		return "redirect:/admin/permission";
	}

	@RequestMapping(value = "/admin/permission/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deletePermission(@PathVariable(value = "id") long id) {

		List<Long> temp = new ArrayList<Long>();
		temp.add(id);
		authService.deletePerms(temp);
		
		return null;
	}

}
