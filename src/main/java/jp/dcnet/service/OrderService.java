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
import jp.dcnet.untils.OrderNumber;

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
					order.setOrderNumber(OrderNumber.generateUniqueKey() + userid + cart.getCodeId());
					order.setDataTime(new Timestamp(System.currentTimeMillis()));

					orderRepository.save(order);
					shopCartRepository.deleteByUserId(userid);

			}

		}

	}

	public int calOrder(int id){

		List<Order> orderlist = orderRepository.findByTotal(id);
		int amount = 0;

		if(orderlist.size() > 0) {
			for(Order order :orderlist) {

				amount += order.getTotal();
			}
		}
		return amount;
	}

	public List<Order> getUserOrder(int userId) {

		List<Order> order = orderRepository.findByUserId(userId);
		return  order;

	}

		public Order getOrderId(int orderId) {

			Order order = orderRepository.findByOrderId(orderId);

			return order;

		}

		public void userPayFor(int orderId) {

			Order order = orderRepository.findByOrderId(orderId);

			Order or = new Order();
			or.setOrderId(orderId);
			or.setUserId(order.getUserId());
			or.setCodeId(order.getCodeId());
			or.setImage(order.getImage());
			or.setName(order.getName());
			or.setPrice(order.getPrice());
			or.setQuantity(order.getQuantity());
			or.setTotal(order.getTotal());
			or.setStatus(1);
			or.setOrderNumber(order.getOrderNumber());
			or.setPayTime(new Timestamp(System.currentTimeMillis()));
			or.setDataTime(new Timestamp(System.currentTimeMillis()));


			orderRepository.save(or);



		}

		public void deleteOrder(int orderId) {

			orderRepository.deleteByOrderId(orderId);
		}






}
