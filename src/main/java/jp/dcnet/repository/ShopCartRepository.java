package jp.dcnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.ShopCart;
import jp.dcnet.entity.ShopCartKey;

@Repository
public interface ShopCartRepository extends JpaRepository<ShopCart, ShopCartKey> {

	@Query(value = "SELECT  count (s.code_id) as count from shop_cart s where s.user_id=:user", nativeQuery = true)
	int getCartcn(@Param("user") int userId);

	List<ShopCart> findByUserId(int id);

	ShopCart findByUserIdAndCodeId(int userId, int productId);

	@Query(value="select code_id as id from shop_cart where user_id=:id", nativeQuery = true)
	public int findByCodeId(@Param("id") int id);

	public int deleteByUserId(int id);


}
