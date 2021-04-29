package jp.dcnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.dcnet.entity.CompanyOrder;

public interface CompanyOrderRepository extends JpaRepository<CompanyOrder,Integer >{

	@Query(value="select\r\n"
			+ "    o.order_id\r\n"
			+ ",   u.username\r\n"
			+ ",   p.name\r\n"
			+ ",   p.price\r\n"
			+ ",   o.quantity\r\n"
			+ ",   o.total\r\n"
			+ ",   o.status\r\n"
			+ ",   o.order_number\r\n"
			+ ",   o.pay_time\r\n"
			+ "\r\n"
			+ "from \r\n"
			+ "product p\r\n"
			+ "inner join   \r\n"
			+ "order_tbl  o\r\n"
			+ "on \r\n"
			+ "p.code_id =o.code_id\r\n"
			+ "left join user_tbl u\r\n"
			+ "on\r\n"
			+ "o.user_id = u.id\r\n"
			+ "where\r\n"
			+ "p.user_id =:id", nativeQuery = true)
	List<CompanyOrder> getCompanyOrderList(@Param("id")int id);


}
