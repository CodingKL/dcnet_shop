package jp.dcnet.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.entity.Product;
import jp.dcnet.entity.ShopCart;
import jp.dcnet.entity.User;
import jp.dcnet.entity.UserAddress;
import jp.dcnet.entity.UserInfo;
import jp.dcnet.object.RoleDto;
import jp.dcnet.repository.AddressRepository;
import jp.dcnet.repository.ProductRepository;
import jp.dcnet.repository.ShopCartRepository;
import jp.dcnet.repository.UserInfoRepository;
import jp.dcnet.repository.UserRepository;

@Transactional
@Service
public class UserCancelService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	ProductRepository ProductRepository;
	@Autowired
	ShopCartRepository ShopCartRepository;

	/*
	 * 管理員刪除用戶處理
	 */
	public void deleteAccount(RoleDto role) {

		User user = new User();
		user.setId(role.getId());
		userRepository.delete(user);

		UserAddress us = new UserAddress();
		us.setUsername(role.getUserName());
		addressRepository.delete(us);

		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(role.getId());
		userInfoRepository.delete(userInfo);

		Product product = new Product();
		product.setUserId(role.getId());
		ProductRepository.delete(product);

		ShopCart shopCart = new ShopCart();
		shopCart.setUserId(role.getId());
		ShopCartRepository.delete(shopCart);
	}

}
