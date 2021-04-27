package jp.dcnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderSucessController {

	public ModelAndView orderSucess() {


		ModelAndView mav = new ModelAndView("orderSucess");
		return mav;

	}

}
