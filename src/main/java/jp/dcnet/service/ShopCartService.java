package jp.dcnet.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.dcnet.entity.Cart;
import jp.dcnet.entity.ShopCart;
import jp.dcnet.entity.ShopCartKey;
import jp.dcnet.repository.CartRepository;
import jp.dcnet.repository.ProductRepository;
import jp.dcnet.repository.ShopCartRepository;

@Service
public class ShopCartService {

	@Autowired
	ShopCartRepository shopCartRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CartRepository cartRepository;

	public List<Cart> findById(int userId) throws Exception {

		List<Cart> list = cartRepository.getCartInfo(userId);

		return list;
	}

	/*
	 * ユーザーはホームページから商品をショップカードに追加処理
	 */
	@Transactional
	public void saveShopCart(int productId, int userId) {

		//		Product product = productRepository.getOne(productId);

		ShopCart shopCart = new ShopCart();
		shopCart = shopCartRepository.findByUserIdAndCodeId(userId, productId);

		if (shopCart == null) {
			ShopCart shopCartt = new ShopCart();
			shopCartt.setCodeId(productId);
			shopCartt.setUserId(userId);
			shopCartt.setQuantity(1);
			shopCartt.setDate_time(new Timestamp(System.currentTimeMillis()));

			shopCartRepository.save(shopCartt);
		} else {

			shopCart.setCodeId(productId);
			shopCart.setUserId(userId);
			shopCart.setQuantity(shopCart.getQuantity() + 1);
			shopCart.setDate_time(new Timestamp(System.currentTimeMillis()));

			shopCartRepository.save(shopCart);

		}

	}

	public int UserCartManager(int userId) {

		return shopCartRepository.getCartcn(userId);
	}

	/*
	 * ユーザーのショップカードの商品を削除する処理
	 */
	public void UserDeleteProductFromCart(ShopCartKey shopCartKey) {

		shopCartRepository.deleteById(shopCartKey);
	}

	/*
	 * ユーザーのショップカードの商品の数量を減らす処理
	 */
	public void stockMin(ShopCartKey shopCartKey) {
		ShopCart shopCart = shopCartRepository.findById(shopCartKey).get();
		shopCart.setQuantity(shopCart.getQuantity() - 1);
		//商品の数量を0なるときこの商品をショップカードから削除する処理
		if (shopCart.getQuantity() == 0) {

			shopCartRepository.deleteById(shopCartKey);
		} else {

			shopCartRepository.save(shopCart);
		}
	}

	/*
	 * ユーザーのショップカードの商品を追加処理
	 */
	public void stockAdd(ShopCartKey shopCartKey) {

		ShopCart shopCart = shopCartRepository.findById(shopCartKey).get();
		shopCart.setQuantity(shopCart.getQuantity() + 1);
		shopCartRepository.save(shopCart);
	}

	public int findByQuantity(ShopCartKey shopCartKey) {

		shopCartRepository.findById(shopCartKey).get().getQuantity();

		return 0;

	}

	//计算购物商品总价格
	public int calShopCart(int userId) {

		List<Cart> list = cartRepository.getCartInfo(userId);
		int amount = 0;
				if (list.size() > 0) {
					for (Cart cart : list) {
						amount += cart.getTotal();
					}
				}
		return amount;
	}



}
