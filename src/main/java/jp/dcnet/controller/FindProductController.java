package jp.dcnet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.Product;
import jp.dcnet.service.ProductService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class FindProductController {
	@Autowired
	ProductService ProductService;


	/*
	 * ユーザーを　商品の名前として、曖昧な検索機能　と　表示
	 */
	@PostMapping(IndexUrl.INDEX_USER_SEARCHF_FOR_PRODUCT)
	public ModelAndView findProduct(@RequestParam("searchforproduct") String productName) {


		List<Product> searchforproduct = ProductService.findByNameLike("%"+productName+"%");

		ModelAndView mav = new ModelAndView("searchproduct");
		mav.addObject("SearchProduct", searchforproduct);
		return mav;

	}

}
