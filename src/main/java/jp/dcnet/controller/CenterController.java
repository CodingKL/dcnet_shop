package jp.dcnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.service.UserService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class CenterController {

	@Autowired
	UserService userService;

	@RequestMapping(IndexUrl.INDEX_USER_CENTER)
	public ModelAndView usercenter() {

		ModelAndView modelAndView = new ModelAndView("center");

		return modelAndView;

	}

	//ユーザーのパスワードを変更画面遷移---------------(Center)
	@RequestMapping(IndexUrl.INDEX_USER_CENTER_PWD)
	public ModelAndView pwd() {
		ModelAndView modelAndView = new ModelAndView("centerHTML/changePwd");
		return modelAndView;

	}

	//ユーザーの情報を変更画面遷移---------------(Center)
	@RequestMapping(IndexUrl.INDEX_USER_CENTER_USERINFO)
	public ModelAndView userinfo() {
		ModelAndView modelAndView = new ModelAndView("centerHTML/userinfo");
		return modelAndView;
	}

	//ユーザーの住所を変更画面遷移---------------(Center)
	@RequestMapping(IndexUrl.INDEX_USER_CENTER_USERADDRESS)
	public ModelAndView useraddress() {
		ModelAndView modelAndView = new ModelAndView("centerHTML/useraddress");
		return modelAndView;
	}

	@RequestMapping(IndexUrl.INDEX_USER_CENTER_USERSAFE)
	public ModelAndView userSafe() {

		ModelAndView modelAndView = new ModelAndView("centerHTML/safe");
		return modelAndView;
	}

	@RequestMapping(IndexUrl.INDEX_USER_CENTER_USERCANCEL)
	public ModelAndView userCancel() {

		ModelAndView modelAndView = new ModelAndView("centerHTML/cancelaccount");
		return modelAndView;
	}

	@RequestMapping(IndexUrl.INDEX_USER_CENTER_PRODUCTUP)
	public ModelAndView productUp() {

		ModelAndView modelAndView = new ModelAndView("centerHTML/productup");

		return modelAndView;
	}

}
