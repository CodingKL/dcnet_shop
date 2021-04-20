package jp.dcnet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.Product;
import jp.dcnet.object.ProductDto;
import jp.dcnet.object.RoleDto;
import jp.dcnet.service.ProductService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class productcontorler {

	@Autowired
	ProductService productService;

	/*
	 *
	 */
	@RequestMapping(IndexUrl.INDEX_USER_CENTER_PRODUCTCONTORLER)
	public ModelAndView productcontorlers(HttpSession session) {

		RoleDto role = (RoleDto) session.getAttribute("UserLogin");

		Product product = new Product();
		product.setUserId(role.getId());

		List<Product> productList = productService.findByUserId(product);

		ModelAndView mv = new ModelAndView("centerHTML/productcontorler");
		mv.addObject("productList", productList);

		return mv;
	}

	/*
	 *商戶刪除商品處理
	 */
	@RequestMapping(IndexUrl.INDEX_USER_CENTER_PRODUCTDELETE)
	public String productDelete(@PathVariable("id") int id) {

		ProductDto productDto = new ProductDto();
		productDto.setId(id);

		productService.deleteByProductId(productDto);

		return "redirect:/index/productcontorler";
	}

	/*
	 * 商家修改商品下架處理
	 */
	@GetMapping(IndexUrl.INDEX_USER_CENTER_COMPANYPRODUCTDOWN)
	public String productdown(@PathVariable("id") int id) {

		ProductDto productDto = new ProductDto();
		productDto.setId(id);

		productService.companyProductDown(productDto);

		return "redirect:/index/productcontorler";

	}

	/*
	 * 商家修改商品上架處理
	 */
	@GetMapping(IndexUrl.INDEX_USER_CENTER_COMPANYPRODUCTUPED)
	public String productuped(@PathVariable("id") int id) {

		ProductDto productDto = new ProductDto();
		productDto.setId(id);

		productService.companyProductUped(productDto);

		return "redirect:/index/productcontorler";

	}
}
