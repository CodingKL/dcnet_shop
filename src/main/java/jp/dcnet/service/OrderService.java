package jp.dcnet.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.entity.Cart;
import jp.dcnet.entity.Order;
import jp.dcnet.repository.CartRepository;
import jp.dcnet.repository.OrderRepository;
import jp.dcnet.repository.ProductRepository;
import jp.dcnet.repository.ShopCartRepository;

@Service
@Transactional
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ShopCartRepository shopCartRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductRepository productRepository;

	/*
	 * 注文を送信して注文フォームを生成する
	 */
	public void saveSettlementOrder(int userid) {

		List<Cart> cartList = cartRepository.getCartInfo(userid);

		if (cartList.size() > 0) {
			for (Cart cart : cartList) {

					Order order = new Order();
					order.setUserId(userid);
					order.setCodeId(cart.getCodeId());
					order.setImage(cart.getImage());
					order.setName(cart.getName());
					order.setPrice(cart.getPrice());
					order.setQuantity(cart.getQuantity());
					order.setTotal(cart.getTotal());
					order.setStatus(0);
					order.setDataTime(new Timestamp(System.currentTimeMillis()));

					orderRepository.save(order);
					shopCartRepository.deleteByUserId(userid);

			}

		}

	}

}
