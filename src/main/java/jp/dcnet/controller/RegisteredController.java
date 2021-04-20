package jp.dcnet.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.object.UserDto;
import jp.dcnet.service.UserService;
import jp.dcnet.untils.IndexUrl;
import jp.dcnet.untils.PwdHashing;

@Controller
public class RegisteredController {

	@Autowired
	UserService userService;

	@RequestMapping(IndexUrl.REGISTERED_VIEW)
	public ModelAndView registered() {

		ModelAndView modelAndView = new ModelAndView("registered");

		return modelAndView;

	}

	//新規登録ペイジーのデータを取得する
	@PostMapping(IndexUrl.REGISTERED_VIEW)
	public ModelAndView registered(@RequestParam(name = "accounts") String userName,
			@RequestParam(name = "pwd") String pwd, @RequestParam(name = "email") String email,
			Map<String, Object> map) {

		UserDto user = new UserDto();
		//ユーザーのパスワードをハッシュ化にします
		pwd = PwdHashing.pwdEnCode(pwd);
		//データを　user　に　保存
		user.setEmail(email);
		user.setName(userName);
		user.setPassword(pwd);
		//データをService層に転送
		int res = userService.createUserInfo(user);
		//新規登録できない
		if (res == 1) {
			map.put("msg", "ユーザーの名前まだメールを存在しました");
			ModelAndView modelAndView = new ModelAndView("registered");
			return modelAndView;
		} else {
			//新規登録成功
			ModelAndView modelAndView = new ModelAndView("login");
			return modelAndView;
		}
	}
}
