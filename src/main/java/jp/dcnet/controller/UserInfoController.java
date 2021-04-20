package jp.dcnet.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jp.dcnet.entity.UserInfo;
import jp.dcnet.object.RoleDto;
import jp.dcnet.object.UserInfoDto;
import jp.dcnet.service.UserInfoService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class UserInfoController {

	@Autowired
	UserInfoService userInfoService;

	/*
	 * ユーザーの情報を編集、保存処理
	 */
	@PostMapping(IndexUrl.INDEX_USER_CENTER_USERINFOUP)
	public String userInfoUp(
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "sex") int sex,
			@RequestParam(name = "birthday") Date biethday,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "hometown") String hometown,
			@RequestParam(name = "email") String email) {

		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setUsername(userName);
		userInfoDto.setName(name);
		userInfoDto.setSex(sex);
		userInfoDto.setBirthday(biethday);
		userInfoDto.setAddress(address);
		userInfoDto.setHometown(hometown);
		userInfoDto.setEmail(email);

		  userInfoService.saveUserInfo(userInfoDto);

		return "redirect:/index/userInfo";
		//redirect:/index
	}

	/*
	 * ユーザーの情報を取得すると、ペイジーへ表示する
	 */
	@GetMapping(IndexUrl.INDEX_USER_CENTER_USERINFO)
	public String userInfo(Model model, HttpSession session) {

		RoleDto userName = (RoleDto) session.getAttribute("UserLogin");

		List<UserInfo> userInfoList = userInfoService.findAllInfo(userName.getUserName());
		model.addAttribute("userInfoList", userInfoList);
		return "centerHTML/userinfo";
	}

	/*
	 * ペイジーからIDを取得、ID対応の情報を編集ペイジーへ
	 */
	@GetMapping(IndexUrl.INDEX_USER_CENTER_USERINFEDIT)
	public String addressModify(@PathVariable("id") int id, Model model) {

		UserInfo userInfoId = userInfoService.findById(id);

		model.addAttribute("userInfoEdit", userInfoId);

		return "centerHTML/userInfoEdit";

	}

	/*
	 * ユーザーの情報保存処理
	 */
	@PostMapping(IndexUrl.INDEX_USER_CENTER_USERINFUPEDIT)
	public String userInfoUpEdit(
			@PathVariable("id") int id,
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "sex") int sex,
			@RequestParam(name = "birthday") Date biethday,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "hometown") String hometown,
			@RequestParam(name = "email") String email) {

		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setUsername(userName);
		userInfoDto.setName(name);
		userInfoDto.setSex(sex);
		userInfoDto.setBirthday(biethday);
		userInfoDto.setAddress(address);
		userInfoDto.setHometown(hometown);
		userInfoDto.setEmail(email);

		 userInfoService.userInfoEdit(userInfoDto, id);

		return "redirect:/index/userInfo";
	}

	/*
	 *  ユーザーのアイコン、アップロード
	 */
	@PostMapping(IndexUrl.INDEX_USER_CENTER_ICONUPLOAD)
	public String imageUpload(
			@RequestPart(name = "iconImage") MultipartFile icon,
			HttpSession session) throws IOException {

		if (!icon.isEmpty()) {

			RoleDto username = (RoleDto) session.getAttribute("UserLogin");

			String OriginalFilename = icon.getOriginalFilename();

			icon.transferTo(
					new File("C:\\WindowWorks\\pleiades\\workspace\\dcnet_shop\\src\\main\\resources\\static\\icon\\"
							+ OriginalFilename));

			userInfoService.saveUserIcon(OriginalFilename, username.getUserName());
		}

		return "redirect:/index/userInfo";

	}
}
