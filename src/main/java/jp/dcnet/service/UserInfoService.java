package jp.dcnet.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.entity.UserInfo;
import jp.dcnet.object.UserInfoDto;
import jp.dcnet.repository.UserInfoRepository;
import jp.dcnet.repository.UserInfosRepository;

@Service
public class UserInfoService {

	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	UserInfosRepository userInfosRepository;

	/*
	 * 獲取用戶個人資料
	 */
	@Transactional
	public List<UserInfo> findAllInfo(String userName) {

		return userInfoRepository.findByUsername(userName);

	}

	@Transactional
	public UserInfo findById(int addressId) {

		return userInfoRepository.findById(addressId);

	}

	/*
	 * 保存個人資料
	 */
	@Transactional
	public int saveUserInfo(UserInfoDto userInfoDto) {

		UserInfo userInfoEntity = new UserInfo();
		userInfoEntity.setUsername(userInfoDto.getUsername());
		userInfoEntity.setName(userInfoDto.getName());
		userInfoEntity.setSex(userInfoDto.getSex());
		userInfoEntity.setBirthday(userInfoDto.getBirthday());
		userInfoEntity.setAddress(userInfoDto.getAddress());
		userInfoEntity.setHometown(userInfoDto.getHometown());
		userInfoEntity.setEmail(userInfoDto.getEmail());
		userInfoEntity.setDateTime(new Timestamp(System.currentTimeMillis()));
		userInfoRepository.saveAndFlush(userInfoEntity);
		return 0;

	}

	@Transactional
	public int userInfoEdit(UserInfoDto userInfoDto, int userInfoId) {

		UserInfo entity = new UserInfo();
		entity.setId(userInfoId);
		entity.setUsername(userInfoDto.getUsername());
		entity.setName(userInfoDto.getName());
		entity.setSex(userInfoDto.getSex());
		entity.setBirthday(userInfoDto.getBirthday());
		entity.setAddress(userInfoDto.getAddress());
		entity.setHometown(userInfoDto.getHometown());
		entity.setEmail(userInfoDto.getEmail());
		entity.setDateTime(new Timestamp(System.currentTimeMillis()));
		userInfoRepository.saveAndFlush(entity);
		return 0;

	}

	@Transactional
	public void saveUserIcon(String icon, String username) {

		UserInfo userInfo = userInfosRepository.findByUsername(username);
		//		icon = "../icon/"+icon;
		userInfo.setUsericon(icon);
		userInfoRepository.saveAndFlush(userInfo);
	}
}
