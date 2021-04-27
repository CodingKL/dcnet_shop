package jp.dcnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	//ユーザーの最大IDを取得
	@Query(value = "select nextval(\'user_tbl_id_seq\')", nativeQuery = true)
	public int getSeq();

	//ユーザーの名前を取得
	@Query(value = "select username from user_tbl where (username=:data) or (email=:data)", nativeQuery = true)
	String findByUsername(@Param("data") String account);

	//ユーザーのメールを取得
	@Query(value = "select email from user_tbl where (username=:data) or (email=:data)", nativeQuery = true)
	String findByEmail(@Param("data") String email);

	@Query(value = "select role from user_tbl where (username=:data)", nativeQuery = true)
	public String getRole(@Param("data")String username);

	@Query(value = "select status from user_tbl where (username=':data')", nativeQuery = true)
	public int getStatus(@Param("data")String username);

}