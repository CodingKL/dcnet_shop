package jp.dcnet.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jp.dcnet.object.ProductDto;
import jp.dcnet.object.RoleDto;
import jp.dcnet.service.ProductService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	/*
	 * 賣家上架商品處理
	 */
	@PostMapping(IndexUrl.INDEX_USER_CENTER_PRODUCTUPLOAD)
	public String productUpLoad(
			@RequestPart(name = "image") MultipartFile image,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "description") String description,
			@RequestParam(name = "quanity") int quanity,
			@RequestParam(name = "price") int price,
			HttpSession session) throws IOException {
		if (!image.isEmpty()) {

			RoleDto username = (RoleDto) session.getAttribute("UserLogin");

			String OriginalFilename = image.getOriginalFilename();
			image.transferTo(
					new File(
							"C:\\WindowWorks\\pleiades\\workspace\\dcnet_shop\\src\\main\\resources\\static\\productimage\\"
									+ OriginalFilename));

			ProductDto productDto = new ProductDto();
			productDto.setImage(OriginalFilename);
			productDto.setName(name);
			productDto.setDescription(description);
			productDto.setQuantity(quanity);
			productDto.setPrice(price);

			productService.saveProduct(productDto, username.getId());

		}

		return "redirect:/index/productup";
	}

}
