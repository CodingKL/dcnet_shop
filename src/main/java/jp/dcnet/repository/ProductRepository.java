package jp.dcnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findAll();


	Product findById(int id);


	List<Product> findByUserId(int userId);

	@Query(value="select code_id from product where code_id=:id", nativeQuery = true)
	List<Product> findByCodeId(@Param("id") int id);

	List<Product> findByNameLike(String name);

	@Query(value="select *  from product where code_id =:id", nativeQuery = true)
	Product getCodeId(@Param("id") int id);

	@Query(value="select code_id  from product where user_id =:id", nativeQuery = true)
	List<Product> getProductCodeId(@Param("id")int id);

}
