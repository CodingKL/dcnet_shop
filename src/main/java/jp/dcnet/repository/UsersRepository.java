package jp.dcnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

	 User findByUsername(String username);

	 @Query(value = "select status from user_tbl where (username=:data)", nativeQuery = true)
	int getUserId(@Param("data")String username);








}
