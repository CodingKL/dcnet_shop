package jp.dcnet.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.dcnet.entity.UserAddress;
import jp.dcnet.object.RoleDto;
import jp.dcnet.object.UserAddressDto;
import jp.dcnet.service.AddressService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class AddressController {

	@Autowired
	AddressService addressService;

	/*
	 * 用戶收貨地址保存處理
	 */
	@PostMapping(IndexUrl.INDEX_USER_CENTER_USERADDRESSUP)
	public String useraddressup(
			@RequestParam(name = "name") String name,
			@RequestParam(name = "telephone") String tel,
			@RequestParam(name = "postcode") String postcode,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "detaddress") String detaddress,
			HttpSession session) {

		RoleDto userName = (RoleDto) session.getAttribute("UserLogin");

		RoleDto role = new RoleDto();
		role.setUserName(userName.getUserName());

		UserAddressDto useradd = new UserAddressDto();
		useradd.setName(name);
		useradd.setTelephone(tel);
		useradd.setPostcode(postcode);
		useradd.setAddress(address);
		useradd.setDetaddress(detaddress);

		addressService.userAddressUp(useradd, role);

		return "redirect:/index/address";
	}

	/*
	 * 獲取當前登錄用戶所有收貨地址
	 */
	@GetMapping(IndexUrl.INDEX_USER_CENTER_USERADDRESS)
	public String userAddress(Model model, HttpSession session) {

		RoleDto userName = (RoleDto) session.getAttribute("UserLogin");

		List<UserAddress> userAddressList = addressService.findAllAddress(userName.getUserName());
		model.addAttribute("userAddressList", userAddressList);

		return "centerHTML/useraddress";

	}

	/*
	 * 修改用戶地址頁面并取得當前對應的ID
	 */
	@RequestMapping(IndexUrl.INDEX_USER_CENTER_ADDRESSMODIFY)
	public String addressModify(@PathVariable("id") int addressId, Model model) {

		UserAddress userAddress = addressService.findById(addressId);

		model.addAttribute("addresss", userAddress);

		return "centerHTML/addressupdate";

	}

	/*
	 * 更新修改后的地址
	 */
	@PostMapping(IndexUrl.INDEX_USER_CENTER_ADDRESSUPDATE)
	public String addressupdate(@PathVariable("id") int addressId,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "telephone") String tel,
			@RequestParam(name = "postcode") String postcode,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "detaddress") String detaddress,
			HttpSession session) {

		RoleDto userName = (RoleDto) session.getAttribute("UserLogin");

		UserAddressDto useradd = new UserAddressDto();
		useradd.setName(name);
		useradd.setTelephone(tel);
		useradd.setPostcode(postcode);
		useradd.setAddress(address);
		useradd.setDetaddress(detaddress);

		addressService.updateAddress(useradd, addressId, userName.getUserName());

		return "redirect:/index/address";
	}

	/*
	 * 刪除用戶已經添加的地址
	 */

	@GetMapping(IndexUrl.INDEX_USER_CENTER_ADDRESSDELETE)
	public String addressDelete(@PathVariable("id") int addressId) {

		addressService.deletById(addressId);

		return "redirect:/index/address";
	}

}
