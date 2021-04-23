package jp.dcnet.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.entity.Order;
import jp.dcnet.object.OrderDto;
import jp.dcnet.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	@Autowired
	OrderRepository orderRepository;



	/*
	 * ショップカードの商品をOrderテーブルに保存処理
	 */
	public void saveSettlementOrder(OrderDto orderDto, int userid) {

		Order order = new Order();


		orderRepository.save(order);

	}

}
