package jp.dcnet.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.entity.Product;
import jp.dcnet.object.ProductDto;
import jp.dcnet.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Transactional
	public List<Product> findAll() {

		return productRepository.findAll();

	}

	@Transactional
	public void saveProduct(ProductDto productDto, int userId) {

		String image = "../productimage/" + productDto.getImage();
		Product product = new Product();
		product.setUserId(userId);
		product.setImage(image);
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setQuantity(productDto.getQuantity());
		product.setPrice(productDto.getPrice());
		product.setDateTime(new Timestamp(System.currentTimeMillis()));
		product.setStatus(0);

		productRepository.saveAndFlush(product);
	}

	/*
	 * 根據商品ID查詢商品信息
	 */

	@Transactional
	public Product findById(int id) {

		return productRepository.findById(id);
	}

	/*
	 * 商戶刪除商品處理
	 */
	@Transactional
	public void deleteByProductId(ProductDto productDto) {

		productRepository.deleteById(productDto.getId());

	}

	/*
	 * 商戶查詢已上架商品處理
	 */

	public List<Product> findByUserId(Product userId) {

		return productRepository.findByUserId(userId.getUserId());
	}

	/*
	 * 管理員商品下架處理
	 */

	public void productDown(ProductDto productDto) {

		Product product = productRepository.findById(productDto.getId());

		product.setStatus(1);

		productRepository.save(product);

	}

	/*
	 * 管理員商品上架處理
	 */

	public void productUped(ProductDto productDto) {

		Product product = productRepository.findById(productDto.getId());

		product.setStatus(0);

		productRepository.save(product);

	}

	/*
	 * 管理員商品下架處理
	 */

	public void companyProductDown(ProductDto productDto) {

		Product product = productRepository.findById(productDto.getId());

		product.setStatus(1);

		productRepository.save(product);

	}

	/*
	 * 管理員商品上架處理
	 */

	public void companyProductUped(ProductDto productDto) {

		Product product = productRepository.findById(productDto.getId());

		product.setStatus(0);

		productRepository.save(product);

	}

	public List<Product> findByNameLike(String productName) {

		return productRepository.findByNameLike(productName);
	}

}
