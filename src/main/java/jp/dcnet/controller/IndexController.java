package jp.dcnet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.dcnet.entity.Product;
import jp.dcnet.service.ProductService;
import jp.dcnet.service.UserService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class IndexController {

	@Autowired
	UserService userInfo;

	@Autowired
	ProductService productService;

	/*
	 * Productを探す、ホームページに表示する
	 */
	@RequestMapping({IndexUrl.INDEX_VIEW,"/"})
	public String index(Model model) {

		List<Product> productList = productService.findAll();

		for (int i = 0, x = productList.size(); i < x; i++) {
			String image = productList.get(i).getImage();
			if (image == null) {
				productList.get(i).setImage("../index/img/shopimage.png");
			}
		}
		model.addAttribute("productList", productList);

		return "index";
	}

}