package jp.dcnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

	 User findByUsername(String account);


	User findByEmail(String email);



}
