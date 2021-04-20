package jp.dcnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.untils.IndexUrl;

@Controller
public class OrderContorlController {

	@RequestMapping(IndexUrl.INDEX_USER_CENTER_ORDERCONTORL)
	public ModelAndView ordercontorl() {

		ModelAndView mv = new ModelAndView("centerHTML/ordercontorl");
		return mv;
	}

}
