package jp.dcnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.Cart;
import jp.dcnet.entity.ShopCartKey;

@Repository
public interface CartRepository extends JpaRepository<Cart, ShopCartKey> {

	@Query(value="select\r\n"
			+ "    s.code_id\r\n"
			+ "    , p.image\r\n"
			+ "    , p.name\r\n"
			+ "    , p.price\r\n"
			+ "    , s.quantity \r\n"
			+ "    , s.quantity*p.price as total \r\n"
			+ "from\r\n"
			+ "    product p join shop_cart s \r\n"
			+ "        on p.code_id = s.code_id \r\n"
			+ "where\r\n"
			+ "    s.user_id = :id", nativeQuery = true)
	List<Cart> getCartInfo(@Param("id") int userId);

}
