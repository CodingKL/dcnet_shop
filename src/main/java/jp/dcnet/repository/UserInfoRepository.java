package jp.dcnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {



	/*
	 * 獲取用戶個人資料
	 */
	List<UserInfo> findByUserId(int id);

	/*
	 * 獲取用戶資料ID
	 */
	UserInfo findById(int userInfoId);



}
