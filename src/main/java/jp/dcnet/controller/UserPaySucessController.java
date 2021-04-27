package jp.dcnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.service.OrderService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class UserPaySucessController {
	@Autowired
	OrderService orderService;

	@RequestMapping(IndexUrl.INDEX_USER_PAY_SUCESS)
	public ModelAndView paySucess(@PathVariable("id") int orderId) {

		orderService.userPayFor(orderId);

		ModelAndView mav = new ModelAndView("paysucess");
		return mav ;

	}

}
