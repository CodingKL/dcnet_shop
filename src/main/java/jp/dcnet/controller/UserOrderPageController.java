package jp.dcnet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.Order;
import jp.dcnet.object.RoleDto;
import jp.dcnet.service.OrderService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class UserOrderPageController {
	@Autowired
	OrderService orderService;

	@RequestMapping(IndexUrl.INDEX_USER_ORDER_PAGE)
	public ModelAndView userOrderPage(HttpSession session) {

		RoleDto role = (RoleDto) session.getAttribute("UserLogin");

		if (role == null) {

			ModelAndView mav = new ModelAndView("login");
			return mav;

		} else {
			
			List<Order> orderList = orderService.getUserOrder(role.getId());

			ModelAndView mav = new ModelAndView("userorderpage");
			mav.addObject("orderList", orderList);
			return mav;
		}

	}
	
	
	

}
