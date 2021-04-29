package jp.dcnet.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.entity.Cart;
import jp.dcnet.entity.CompanyOrder;
import jp.dcnet.entity.Order;
import jp.dcnet.entity.Product;
import jp.dcnet.repository.CartRepository;
import jp.dcnet.repository.CompanyOrderRepository;
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
	@Autowired
	CompanyOrderRepository companyOrderRepository;

	/*
	 * 注文を送信して注文フォームを生成する
	 */
	public void saveSettlementOrder(int userid) {

		List<Cart> cartList = cartRepository.getCartInfo(userid);

		if (cartList.size() > 0) {
			for (Cart cart : cartList) {

				Product product = productRepository.getCodeId(cart.getCodeId());
				Product pro = new Product();
				pro.setCode_id(product.getCode_id());
				pro.setUserId(product.getUserId());
				pro.setImage(product.getImage());
				pro.setName(product.getName());
				pro.setDescription(product.getDescription());
				pro.setQuantity(product.getQuantity() - cart.getQuantity());
				pro.setPrice(product.getPrice());
				pro.setDataTime(new Timestamp(System.currentTimeMillis()));

				productRepository.save(pro);

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

	public int calOrder(int id) {

		List<Order> orderlist = orderRepository.findByTotal(id);
		int amount = 0;

		if (orderlist.size() > 0) {
			for (Order order : orderlist) {

				amount += order.getTotal();
			}
		}
		return amount;
	}

	public List<Order> getUserOrder(int userId) {

		List<Order> order = orderRepository.findByUserId(userId);
		return order;

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

	/*
	 * 管理者、全てのオーダーを検索機能
	 */
	public List<Order> getAllOrder() {

		return orderRepository.findAll();

	}

	/*
	 * ストア,オーダーを取る
	 */
	public List<CompanyOrder> getCompanyOrder(int id) {

		return companyOrderRepository.getCompanyOrderList(id);

	}

	/*
	 * ストア　商品を発送
	 */
	public void recompanyOrderSend(int orderId) {
		Order order = orderRepository.findByOrderId(orderId);

		Order or = new Order();
		or.setOrderId(order.getOrderId());
		or.setUserId(order.getUserId());
		or.setCodeId(order.getCodeId());
		or.setImage(order.getImage());
		or.setName(order.getName());
		or.setPrice(order.getPrice());
		or.setQuantity(order.getQuantity());
		or.setTotal(order.getTotal());
		or.setStatus(3);
		or.setOrderNumber(order.getOrderNumber());
		or.setPayTime(order.getPayTime());
		or.setDataTime(new Timestamp(System.currentTimeMillis()));

		orderRepository.saveAndFlush(or);

	}
}