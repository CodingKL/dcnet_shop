package jp.dcnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.untils.IndexUrl;

@Controller
public class OrderPayController {

	@RequestMapping(IndexUrl.INDEX_USER_ORDER_PAY)
	public ModelAndView orderPay() {


		ModelAndView mav = new ModelAndView("orderpay");

		return mav;
	}

}
