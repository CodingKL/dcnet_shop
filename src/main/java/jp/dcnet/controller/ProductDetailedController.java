package jp.dcnet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.Product;
import jp.dcnet.service.ProductService;
import jp.dcnet.untils.IndexUrl;

@Controller

public class ProductDetailedController {

	@Autowired
	ProductService productService;

	@GetMapping(IndexUrl.INDEX_USER_CENTER_PRODUCTDETAILED)
	public ModelAndView productdetailedd(@PathVariable("id") int id) {

		Product productdetailedd = productService.findById(id);
		ModelAndView mv = new ModelAndView("productdetailed");
		mv.addObject("product", productdetailedd);
		return mv;

	}

}
