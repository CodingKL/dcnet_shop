package jp.dcnet.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.dcnet.object.RoleDto;
import jp.dcnet.object.SafeQuestionDto;
import jp.dcnet.service.AccountSafetyService;
import jp.dcnet.untils.IndexUrl;

@Controller
public class AccountSaftyController {
	@Autowired
	AccountSafetyService accountSafety;

	@RequestMapping(IndexUrl.INDEX_USER_ACCOUNT_SAFETY)
	public String  accountSafty(
			@RequestParam("qone") String qone,
			@RequestParam("qonean") String qonean,
			@RequestParam("qtwo") String qtwo,
			@RequestParam("qtwoan") String qtwoan,
			@RequestParam("qthree") String qthree,
			@RequestParam("qthreean") String qthreean,
			HttpSession session,
			ModelMap model
			) {
		RoleDto role = (RoleDto) session.getAttribute("UserLogin");

		SafeQuestionDto safe = new SafeQuestionDto();
		safe.setQone(qone);
		safe.setQonean(qonean);
		safe.setQtwo(qtwo);
		safe.setQtwoan(qtwoan);
		safe.setQthree(qthree);
		safe.setQthreean(qthreean);

		int res = accountSafety.saveAccountsafte(safe, role.getId(), role.getEmail(), role.getUserName());

		if (res == 1) {

			model.addAttribute("msg", "您已添加过安全问题了");

			return "redirect:/index/center";

		} else {

			return "redirect:/index/center";

		}

	}

}
