package jp.dcnet.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.dcnet.entity.Product;
import jp.dcnet.entity.ShopCart;
import jp.dcnet.entity.ShopCartKey;
import jp.dcnet.repository.ProductRepository;
import jp.dcnet.repository.ShopCartRepository;

@Service
public class ShopCartService {

	@Autowired
	ShopCartRepository shopCartRepository;
	@Autowired
	ProductRepository productRepository;

	public List<ShopCart> findById(int user_id) {

		return shopCartRepository.findByUserId(user_id);
	}

	@Transactional
	public void saveShopCart(int productId, int userId) {

		Product product = productRepository.getOne(productId);

		ShopCart shopCart = new ShopCart();
		shopCart = shopCartRepository.findByUserIdAndCodeId(userId, productId);

		if (shopCart == null) {
			ShopCart shopCartt = new ShopCart();
			shopCartt.setCodeId(productId);
			shopCartt.setUserId(userId);
			shopCartt.setImage(product.getImage());
			shopCartt.setProduct_name(product.getName());
			shopCartt.setQuantity(1);
			shopCartt.setPrice(product.getPrice());
			shopCartt.setTotal(product.getPrice() * 1);
			shopCartt.setDate_time(new Timestamp(System.currentTimeMillis()));

			shopCartRepository.save(shopCartt);
		} else {
			shopCart.setCodeId(productId);
			shopCart.setUserId(userId);
			shopCart.setImage(product.getImage());
			shopCart.setProduct_name(product.getName());
			shopCart.setQuantity(shopCart.getQuantity() + 1);
			shopCart.setPrice(product.getPrice());
			shopCart.setTotal(product.getPrice() * shopCart.getQuantity());
			shopCart.setDate_time(new Timestamp(System.currentTimeMillis()));

			shopCartRepository.save(shopCart);

		}

	}

	public int UserCartManager(int userId) {

		return shopCartRepository.getCartcn(userId);
	}

	public int UserDeleteProductFromCart(int userId, int productId) {

		shopCartRepository.deleteByUserIdAndCodeId(userId, productId);

		return 0;
	}

	public void stockMin(ShopCartKey shopCartKey) {

		ShopCart shopCart = shopCartRepository.findById(shopCartKey).get();
		shopCart.setQuantity(shopCart.getQuantity()-1);
		shopCart.setTotal(shopCart.getPrice()*shopCart.getQuantity());

		if( shopCart.getQuantity() == 0 ) {

			shopCartRepository.deleteById(shopCartKey);
		} else {

			shopCartRepository.save(shopCart);
		}
	}




	public void stockAdd(ShopCartKey shopCartKey) {

		ShopCart shopCart = shopCartRepository.findById(shopCartKey).get();
		shopCart.setQuantity(shopCart.getQuantity()+1);
		shopCart.setTotal(shopCart.getPrice()*shopCart.getQuantity());

		shopCartRepository.save(shopCart);
	}




	public int findByQuantity(ShopCartKey shopCartKey) {

		shopCartRepository.findById(shopCartKey).get().getQuantity();

		return 0;

	}

}
