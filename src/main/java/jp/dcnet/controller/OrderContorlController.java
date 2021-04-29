package jp.dcnet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.Order;
import jp.dcnet.service.OrderService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class OrderContorlController {
	@Autowired
	OrderService orderService;

	@RequestMapping(IndexUrl.INDEX_USER_CENTER_ORDERCONTORL)
	public ModelAndView ordercontorl() {


		List<Order> orderList =orderService.getAllOrder();

		ModelAndView mv = new ModelAndView("centerHTML/ordercontorl");
		mv.addObject("orderList", orderList);
		return mv;
	}

}
