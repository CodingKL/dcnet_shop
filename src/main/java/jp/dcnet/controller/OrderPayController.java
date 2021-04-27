package jp.dcnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.Order;
import jp.dcnet.service.OrderService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class OrderPayController {
	@Autowired
	OrderService orderService;

	@RequestMapping(IndexUrl.INDEX_USER_ORDER_PAY_PAGE)
	public ModelAndView orderPay(@PathVariable("id")int orderId) {

		Order order = orderService.getOrderId(orderId);


		ModelAndView mav = new ModelAndView("orderpay");
		mav.addObject("order", order);

		return mav;
	}

	@RequestMapping(IndexUrl.INDEX_USER_ORDER_PAY_PAGE_DELETE)
	public String orderDelete(@PathVariable("id")int orderId) {

		orderService.deleteOrder(orderId);

		return "redirect:/index/userorderpage";
	}



}
