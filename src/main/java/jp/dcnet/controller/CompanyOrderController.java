package jp.dcnet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.CompanyOrder;
import jp.dcnet.object.RoleDto;
import jp.dcnet.service.OrderService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class CompanyOrderController {
	@Autowired
	OrderService orderService;

	@RequestMapping(IndexUrl.INDEX_USER_COMPANY_ORDER)
	public ModelAndView companyOrderView( HttpSession session) {

		RoleDto role =(RoleDto)session.getAttribute("UserLogin");


		List<CompanyOrder> orderList = orderService.getCompanyOrder(role.getId());


		ModelAndView mav = new ModelAndView("centerHTML/companyorder");
		mav.addObject("orderList", orderList);

		return mav;

	}


	@RequestMapping(IndexUrl.INDEX_USER_COMPANY_ORDER_SEND)
	public String companyOrderSend(@PathVariable("id") int orderId) {


		orderService.recompanyOrderSend(orderId);


		return "redirect:/index/companyorder";

	}

}
