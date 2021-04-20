package jp.dcnet.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String login(@RequestParam(name = "accounts") String accounts,
			@RequestParam(name = "pwd") String passWord, Map<String, Object> map, HttpSession session) {

		/*
		 * ユーザーのデータをセションに保存処理
		 */
		RoleDto role = new RoleDto();
		role.setId(userService.getUserId(accounts));
		role.setUserName(userService.getUserName(role.getId()));
		role.setEmail(userService.getUserEmail(role.getId()));
		role.setRole(userService.getRole(role.getId()));
		role.setStatus(userService.getStatus(role.getId()));

		/*
		 * ユーザーのデータを処理
		 */
		UserDto user = new UserDto();
		passWord = PwdHashing.pwdEnCode(passWord);
		user.setName(userService.getName(accounts));
		user.setEmail(userService.getEmail(accounts));
		user.setPassword(passWord);
		int res = userService.userLogin(user);

		//ユーザー存在しません
		if (res == 1) {
			map.put("msg", "用戶不存在");

			return "redirect:/index/login";
			//ユーザーパスワードエーラ
		} else if (res == 2) {

			map.put("msg", "用戶密碼錯誤");

			return "redirect:/index/login";

		} else if (role.getStatus() == 1) {

			return "redirect:/jujuefanwen";
		} else {

			//セッションを保存
			session.setAttribute("UserLogin", role);

			return "redirect:/index";
		}

	}
}
