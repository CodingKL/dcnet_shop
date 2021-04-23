package jp.dcnet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.Cart;
import jp.dcnet.entity.ShopCartKey;
import jp.dcnet.object.RoleDto;
import jp.dcnet.service.OrderService;
import jp.dcnet.service.ShopCartService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class ShopCartController {
	@Autowired
	ShopCartService shopCartService;
	@Autowired
	OrderService orderService;

	/*
	 * ショップカードを表示する処理
	 */
	@RequestMapping(IndexUrl.INDEX_USER_CENTER_SHOPCART)
	public ModelAndView productCart(HttpSession session) throws Exception {

		RoleDto role = (RoleDto) session.getAttribute("UserLogin");

		if (role == null) {

			ModelAndView mv = new ModelAndView("login");

			return mv;
		} else {

			List<Cart> shopCartList = shopCartService.findById(role.getId());
			ModelAndView modelAndView = new ModelAndView("shopcart");

			modelAndView.addObject("shopCartList", shopCartList);
			//ショップカードの商品総価格計算
			String amount = "￥" + Integer.toString(shopCartService.calShopCart(role.getId()));
			modelAndView.addObject("amount", amount);
			return modelAndView;

		}

	}

	/*
	 * 商品をショップカードに添加処理
	 */
	@RequestMapping(value = "/parts/cart")
	public ModelAndView renderTopNavCartPart(@RequestParam(value = "productId") int productId,
			HttpSession session) {

		RoleDto role = (RoleDto) session.getAttribute("UserLogin");

		if (role == null) {

			ModelAndView mav = new ModelAndView("login");

			return mav;

		} else {

			shopCartService.saveShopCart(productId, role.getId());

			int res = shopCartService.UserCartManager(role.getId());

			ModelAndView mav = new ModelAndView();
			mav.addObject("currentCartItemCount", res);
			return mav;

		}

	}

	/*
	 *ユーザーのショップカード削除する処理
	 */
	@ResponseBody
	@RequestMapping(value = "/parts/cartlist")
	public ModelAndView deleteProductFromCart(@RequestParam(value = "productId") int productId,
			HttpSession session) {

		RoleDto role = (RoleDto) session.getAttribute("UserLogin");

		ShopCartKey shopCartKey = new ShopCartKey();
		shopCartKey.setCodeId(productId);
		shopCartKey.setUserId(role.getId());
		shopCartService.UserDeleteProductFromCart(shopCartKey);

		int res = shopCartService.UserCartManager(role.getId());

		ModelAndView mav = new ModelAndView();
		mav.addObject("currentCartItemCount", res);

		return mav;

	}

	/*
	 * ユーザーのショップカード、商品数量を減らす
	 */
	@ResponseBody
	@RequestMapping(value = "/parts/cartMinQuantity")
	public ModelAndView stockMinsubmit(@RequestParam(value = "productId") int productId,
			HttpSession session) {

		RoleDto role = (RoleDto) session.getAttribute("UserLogin");

		ShopCartKey shopCartkey = new ShopCartKey();
		shopCartkey.setCodeId(productId);
		shopCartkey.setUserId(role.getId());
		shopCartService.stockMin(shopCartkey);

		int res = shopCartService.findByQuantity(shopCartkey);
		ModelAndView mav = new ModelAndView();
		mav.addObject("productquantity", res);
		//		mav.addObject("total",shopCartService.calShopCart(shopCartkey.getUserId()));
		mav.addObject("amount", shopCartService.calShopCart(shopCartkey.getUserId()));

		return mav;

	}

	/*
	 * ユーザーのショップカード、商品数量をアップ
	 */
	@ResponseBody
	@RequestMapping(value = "/parts/cartAddQuantity")
	public ModelAndView stockAddsubmit(@RequestParam(value = "productId") int productId,
			HttpSession session) {

		RoleDto role = (RoleDto) session.getAttribute("UserLogin");

		ShopCartKey shopCartkey = new ShopCartKey();
		shopCartkey.setCodeId(productId);
		shopCartkey.setUserId(role.getId());
		shopCartService.stockAdd(shopCartkey);
		int res = shopCartService.findByQuantity(shopCartkey);

		ModelAndView mav = new ModelAndView();
		mav.addObject("productquantity", res);
		//		mav.addObject("total", shopCartService.calShopCart(shopCartkey.getUserId()));
		mav.addObject("amount", shopCartService.calShopCart(shopCartkey.getUserId()));

		return mav;
	}

}
