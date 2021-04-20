package jp.dcnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.untils.IndexUrl;

@Controller
public class ChenckOutController {

	/*
	 * Orderペイジー
	 */
	@RequestMapping(IndexUrl.INDEX_USER_CENTER_CHECKOUT)
	public ModelAndView checkout() {

		ModelAndView mav = new ModelAndView("checkout");

		return mav;

	}

}
