package jp.dcnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer>{

}
