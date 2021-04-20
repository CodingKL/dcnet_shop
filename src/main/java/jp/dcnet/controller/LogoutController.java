package jp.dcnet.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.dcnet.untils.IndexUrl;

@Controller
public class LogoutController {

	@RequestMapping(IndexUrl.INDEX_LOGOUT)
	public String logoout(HttpSession session) {

		//セッションを削除する
		session.removeAttribute("UserLogin");

		return "redirect:/index";

	}
}
