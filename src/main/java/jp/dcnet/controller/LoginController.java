package jp.dcnet.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.object.RoleDto;
import jp.dcnet.object.UserDto;
import jp.dcnet.service.UserService;
import jp.dcnet.untils.IndexUrl;
import jp.dcnet.untils.PwdHashing;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	/*
	 * ユーザーの権限ない場合errorペイジー
	 */
	@RequestMapping("/jujuefanwen")
	public ModelAndView jujuefanwen() {
		ModelAndView mv = new ModelAndView("jujuefanwen");

		return mv;
	}

	@RequestMapping(IndexUrl.LOGIN_VIEW)
	public ModelAndView login() {

		ModelAndView modelAndView = new ModelAndView("login");

		return modelAndView;

	}

	/*
	 * ユーザーのログイン機能処理
	 */
	@PostMapping(IndexUrl.LOGIN_VIEW)
	public String login(@RequestParam(name = "username") String username,
			@RequestParam(name = "pwd") String passWord,
			Model model,
			HttpSession session) {
		/*
		 * ユーザーのデータを処理
		 */
		UserDto user = new UserDto();
		user.setName(username);
		user.setPassword(PwdHashing.pwdEnCode(passWord));
		int res = userService.UserLogin(user);

		if (res == 1) {

			model.addAttribute("msg", "用戶不存在");
			return "login";

		} else if (res == 2) {

			model.addAttribute("msg", "用戶密碼錯誤");
			return "login";

		} else {

			/*
			 * ユーザーのデータをセションに保存処理
			 */

			RoleDto role = new RoleDto();
			role.setId(userService.getUserId(username));
			role.setUserName(username);
			role.setRole(userService.getRole(role.getId()));
			role.setEmail(userService.getUserEmail(role.getId()));
			role.setStatus(userService.getStatus(role.getId()));

			if (role.getStatus() == 1) {

				return "redirect:/jujuefanwen";

			} else {
				session.setAttribute("UserLogin", role);
				return "redirect:/index";
			}

		}
	}
}
