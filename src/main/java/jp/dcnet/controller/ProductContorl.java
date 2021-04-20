package jp.dcnet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.Product;
import jp.dcnet.object.ProductDto;
import jp.dcnet.service.ProductService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class ProductContorl {

	@Autowired
	ProductService productService;

	@RequestMapping(IndexUrl.INDEX_USER_CENTER_PRODUCTCONTORL)
	public ModelAndView productcontorl() {

		List<Product> product = productService.findAll();

		ModelAndView mv = new ModelAndView("centerHTML/productcontorl");
		mv.addObject("product", product);
		return mv;
	}

	@GetMapping(IndexUrl.INDEX_USER_CENTER_PRODUCTDOWN)
	public String productdown(@PathVariable("id") int id) {

		ProductDto productDto = new ProductDto();
		productDto.setId(id);

		productService.productDown(productDto);

		return "redirect:/index/productcontorl";

	}

	@GetMapping(IndexUrl.INDEX_USER_CENTER_PRODUCTUPED)
	public String productuped(@PathVariable("id") int id) {

		ProductDto productDto = new ProductDto();
		productDto.setId(id);

		productService.productUped(productDto);

		return "redirect:/index/productcontorl";

	}

}
