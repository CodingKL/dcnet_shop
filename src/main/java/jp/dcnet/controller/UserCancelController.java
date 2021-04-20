package jp.dcnet.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jp.dcnet.object.RoleDto;
import jp.dcnet.service.UserCancelService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class UserCancelController {

	@Autowired
	UserCancelService userCancelService;

	@PostMapping(IndexUrl.INDEX_USER_CENTER_USERCANCELUP)
	public String usercancel(HttpSession session, Map<String, Object> map) {

		RoleDto userName = (RoleDto) session.getAttribute("UserLogin");

		userCancelService.deleteAccount(userName);

		map.put("msg", "用戶已注銷，请重新注册");
		session.removeAttribute("UserLogin");
		return "registered";

	}

}
