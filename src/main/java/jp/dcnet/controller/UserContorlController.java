package jp.dcnet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.User;
import jp.dcnet.object.UserDto;
import jp.dcnet.service.UserService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class UserContorlController {

	@Autowired
	UserService userService;

	/*
	 * 管理者を全てユーザーの情報表示処理
	 */
	@RequestMapping(IndexUrl.INDEX_USER_CENTER_USERCONTORL)
	public ModelAndView usercontorl() {

		List<User> user = userService.findAll();

		ModelAndView mv = new ModelAndView("centerHTML/usercontorl");

		mv.addObject("userList", user);

		return mv;
	}

	/*
	 * 管理者ユーザーのRoleを編集処理
	 */
	@GetMapping(IndexUrl.INDEX_USER_CENTER_USERCONTORLUP)
	public String usercontorlup(
			@PathVariable("id") int id,
			@RequestParam("role") String role) {

		UserDto userDto = new UserDto();
		userDto.setRole(role);

		userService.usercontorlup(userDto, id);

		return "redirect:/index/usercontorl";
	}

	/*
	 * 管理者は、ユーザーのアカウントを削除する処理
	 */
	@GetMapping(IndexUrl.INDEX_USER_CENTER_USERCONTORLDELETE)
	public String usercontorldelete(@PathVariable("id") int id) {

		UserDto userDto = new UserDto();
		userDto.setId(id);

		userService.usercontorldelete(userDto);

		return "redirect:/index/usercontorl";

	}

	/*
	 * 管理者、ユーザーを　PullBack　処理
	 */
	@GetMapping(IndexUrl.INDEX_USER_CENTER_USERPULLBACK)
	public String userPullBack(@PathVariable("id") int id) {

		UserDto userDto = new UserDto();
		userDto.setId(id);

		userService.userPullBackList(userDto);

		return "redirect:/index/usercontorl";
	}

	/*
	 *管理者、ユーザーを　RemoveBack　処理
	 */
	@GetMapping(IndexUrl.INDEX_USER_CENTER_USERREMOVEBACKLIST)
	public String userRemoveBack(@PathVariable("id") int id) {

		UserDto userDto = new UserDto();
		userDto.setId(id);

		userService.userRemoveBackList(userDto);

		return "redirect:/index/usercontorl";
	}

}
