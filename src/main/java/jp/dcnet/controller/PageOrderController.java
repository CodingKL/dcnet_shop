package jp.dcnet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.Cart;
import jp.dcnet.object.RoleDto;
import jp.dcnet.service.OrderService;
import jp.dcnet.service.ShopCartService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class PageOrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	ShopCartService shopCartService;

	/*
	 * ショップカードの商品をOrderペイジーへに表示する
	 */
	@RequestMapping(IndexUrl.INDEX_USER_ORDER_SETTLEMENTORDER)
	public ModelAndView settlementorder(HttpSession session) throws Exception {

		RoleDto role = (RoleDto) session.getAttribute("UserLogin");
		List<Cart> shopCartList = shopCartService.findById(role.getId());
		ModelAndView mav = new ModelAndView("order");
		mav.addObject("shopCartList", shopCartList);

		//商品の総価格計算
		String amount = "￥" + Integer.toString(shopCartService.calShopCart(role.getId()));
		mav.addObject("amount", amount);

		return mav;
	}

	/*
	 * 注文を送信して注文フォームを生成する
	 */
	@RequestMapping(IndexUrl.INDEX_USER_CENTER_ORDERCHECK)
	public ModelAndView orderCheck(HttpSession session) {

		RoleDto role = (RoleDto) session.getAttribute("UserLogin");

		orderService.saveSettlementOrder(role.getId());

		ModelAndView mav = new ModelAndView("orderSucess");
		return mav;
	}

}
