package jp.dcnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.untils.IndexUrl;

@Controller
public class ChcekOutController {

	@RequestMapping(IndexUrl.INDEX_USER_ORDER_CHECKOUT)
	public ModelAndView checkout() {

		ModelAndView mav = new ModelAndView("checkout");

		return mav;
	}
}
