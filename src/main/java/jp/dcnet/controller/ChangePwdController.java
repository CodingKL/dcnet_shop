package jp.dcnet.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.object.RoleDto;
import jp.dcnet.object.UserDto;
import jp.dcnet.service.UserService;
import jp.dcnet.untils.IndexUrl;
import jp.dcnet.untils.PwdHashing;

@Controller
public class ChangePwdController {

	@Autowired
	UserService userService;

	//ユーザーのパスワード変更処理---------------(Center)
	@PostMapping(IndexUrl.INDEX_USER_CENTER_CAHNGEPWD)
	public ModelAndView changePwd(
			@RequestParam(name = "oldpwd") String oldPwd,
			@RequestParam(name = "newpwd") String newPwd,
			Map<String, Object> map,
			HttpSession session) {

		//セッション
		RoleDto userName = (RoleDto) session.getAttribute("UserLogin");

		oldPwd = PwdHashing.pwdEnCode(oldPwd);
		newPwd = PwdHashing.pwdEnCode(newPwd);

		UserDto entity = new UserDto();
		entity.setName(userName.getUserName());
		entity.setPassword(oldPwd);

		int res = userService.updatePwd(entity, newPwd);

		if (res == 1) {
			map.put("msg", "旧密码不一致");
			ModelAndView modelAndView = new ModelAndView("centerHTML/changePwd");
			return modelAndView;

		} else {
			map.put("msg", "密碼已更改，請重新登錄");
			ModelAndView modelAndView = new ModelAndView("login");
			return modelAndView;

		}

	}

}
