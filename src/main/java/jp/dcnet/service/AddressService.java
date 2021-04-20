package jp.dcnet.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.dcnet.entity.UserAddress;
import jp.dcnet.object.RoleDto;
import jp.dcnet.object.UserAddressDto;
import jp.dcnet.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addRessRepository;

	//ユーザーの(收货地址)保存処理
	@Transactional
	public void userAddressUp(UserAddressDto userAddDto, RoleDto userName) {

		UserAddress entity = new UserAddress();
		entity.setUsername(userName.getUserName());
		entity.setName(userAddDto.getName());
		entity.setTelephone(userAddDto.getTelephone());
		entity.setPostcode(userAddDto.getPostcode());
		entity.setAddress(userAddDto.getAddress());
		entity.setDetaddress(userAddDto.getDetaddress());
		entity.setDateTime(new Timestamp(System.currentTimeMillis()));
		addRessRepository.saveAndFlush(entity);

	}

	/**
	 * 取得當前登錄用户所有收貨地址
	 * @return
	 */
	@Transactional
	public List<UserAddress> findAllAddress(String usreName) {

		return addRessRepository.findByUsername(usreName);
	}

	//アドレスIDを取得と、ユーザー情報取る
	@Transactional
	public UserAddress findById(int addressId) {

		return addRessRepository.findById(addressId);

	}

	//アドレスIDを取得と，情報を削除する
	@Transactional
	public UserAddress deletById(int addressId) {

		return addRessRepository.deleteById(addressId);

	}

	/*
	 * ユーザーのアドレスを保存処理
	 */
	@Transactional
	public int updateAddress(UserAddressDto userAddDto, int addressId, String userName) {

		UserAddress entity = new UserAddress();
		entity.setId(addressId);
		entity.setUsername(userName);
		entity.setName(userAddDto.getName());
		entity.setTelephone(userAddDto.getTelephone());
		entity.setPostcode(userAddDto.getPostcode());
		entity.setAddress(userAddDto.getAddress());
		entity.setDetaddress(userAddDto.getDetaddress());
		entity.setDateTime(new Timestamp(System.currentTimeMillis()));
		addRessRepository.saveAndFlush(entity);
		return 0;

	}
}
