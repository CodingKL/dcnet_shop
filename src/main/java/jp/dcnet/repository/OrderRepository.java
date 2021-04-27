package jp.dcnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer>{


	@Query(value="select total from order_tbl where user_id=:id", nativeQuery = true)
	List<Order> findByTotal(@Param("id") int id);


	List<Order> findByUserId(int userId);

	Order findByOrderId(int orderId);

	void  deleteByOrderId(int orderId);



}
