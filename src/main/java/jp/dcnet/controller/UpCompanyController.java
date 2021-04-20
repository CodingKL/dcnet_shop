package jp.dcnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.untils.IndexUrl;

@Controller
public class UpCompanyController {

	@RequestMapping(IndexUrl.INDEX_USER_CENTER_UPCOMPANY)
	public ModelAndView upCompany() {

		ModelAndView mav = new ModelAndView("centerHTML/upcompany");
		return mav;
	}

}
