package jp.dcnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.dcnet.entity.ShopCart;
import jp.dcnet.entity.ShopCartKey;

@Repository
public interface ShopCartRepository extends JpaRepository<ShopCart, ShopCartKey> {

	@Query(value = "SELECT  count (s.code_id) as count from shop_cart s where s.user_id=:user", nativeQuery = true)
	int getCartcn(@Param("user") int userId);

	/*List<ShopCart> findById(ShopCartKey shopCartKey);*/

	List<ShopCart> findByUserId(int userId);

	ShopCart findByUserIdAndCodeId(int userId, int productId);

	@Modifying
	@Transactional
	@Query(value = "delete from shop_cart where user_id= :userId  and code_id= :productId", nativeQuery = true)
	void deleteByUserIdAndCodeId(@Param("userId") int userId, @Param("productId") int productId);

}
