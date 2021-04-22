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


//	User findByUserId(String username);

	//ユーザーのIDを取得

//	public int findByUserId(String username);


	//	User findById(int id);

	//	@Query(value = "SELECT SCHEMA.user_tbl_id_seq.nextval FROM user_tbl", nativeQuery = true)
	//    public BigDecimal getNextValMySequence();

	//	List<User> findByEmailAddressAndLastname(String emailAddress, String lastname);

	//	List<User> findByLastname(String lastname);

	//	User findByEmailAddress(String emailAddress);

	//		@Query("select u from User u where u.emailAddress = ?1")
	//		User findByEmailAddress(String emailAddress);

	//	@Query("select u from User u where u.firstname like %?1")
	//	  List<User> findByFirstnameEndsWith(String firstname);

	//	  @Query(value = "SELECT * FROM USERS WHERE LASTNAME = ?1",
	//			    countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",
	//			    nativeQuery = true)
	//			  Page<User> findByLastname(String lastname, Pageable pageable);

}
