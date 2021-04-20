package jp.dcnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.UserInfo;

@Repository
public interface UserInfosRepository extends JpaRepository<UserInfo, Integer> {
	UserInfo findByUsername(String userName);
}
