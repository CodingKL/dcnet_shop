package jp.dcnet.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.entity.AccountSafeQuestion;
import jp.dcnet.object.RoleDto;
import jp.dcnet.object.SafeQuestionDto;
import jp.dcnet.service.AccountSafetyService;
import jp.dcnet.untils.IndexUrl;
import jp.dcnet.untils.PwdHashing;

@Controller
public class ForgetPasswordController {

	@Autowired
	AccountSafetyService accountSafetyService;

	@RequestMapping(IndexUrl.FORGET_PASSWORD)
	public ModelAndView forgetPassword() {

		ModelAndView mav = new ModelAndView("forgetpassword");
		return mav;
	}

	@PostMapping("/parts/userforgetfwd")
	@ResponseBody
	public ModelAndView userForgetPwd(@RequestBody String name , HttpSession session) {
		String objStr = name.replace("account=", "");
		String account = objStr.replace("%40", "@");
		RoleDto role = new RoleDto();

		AccountSafeQuestion asq = accountSafetyService.getQuestion(account);


		if (asq == null) {
			ModelAndView mav = new ModelAndView("forgetpassword");
			mav.addObject("msg", "验证失败(账号有错)");
			return mav;

		} else {

			role.setId(asq.getUserId());
			session.setAttribute("UserLogin", role);

			ModelAndView mav = new ModelAndView();
			mav.addObject("question", asq);

			return mav;

		}

	}

	@PostMapping("/parts/userchangepwd")
	@ResponseBody
	public ModelAndView userQuestionAnswer(
			@PathParam("qOne") String qOne ,
			@PathParam("qTow")  String qTow,
			@PathParam("qThree") String qThree,
			HttpSession session
			) {

		RoleDto role = (RoleDto)session.getAttribute("UserLogin");
		SafeQuestionDto sqDto = new SafeQuestionDto();
		sqDto.setQonean(qOne);
		sqDto.setQtwoan(qTow);
		sqDto.setQthreean(qThree);

		int res = accountSafetyService.getUserAnswer(sqDto, role.getId());

		if(res == 1) {

			ModelAndView mav = new ModelAndView("forgetpassword");
			mav.addObject("msg", "答案出错请重新验证");
			return mav;

		}else {

			ModelAndView mav = new ModelAndView();

			return mav;

		}



	}


	@PostMapping("/parts/pwdsuccess")
	@ResponseBody
	public ModelAndView userPwdSuccess(@PathParam("pwd") String pwd , HttpSession session) {

		RoleDto role = (RoleDto) session.getAttribute("UserLogin");

		SafeQuestionDto sqDto = new SafeQuestionDto();
		sqDto.setPwd(PwdHashing.pwdEnCode(pwd));

		int res =accountSafetyService.changeUserPwd(sqDto,role.getId());

		if(res == 1) {

			ModelAndView mav = new ModelAndView("404");
			return mav ;


		}else {
			ModelAndView mav = new ModelAndView("parts/pwdsuccess");

			session.removeAttribute("UserLogin");

			return mav ;
		}

	}

}
