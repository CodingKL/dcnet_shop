package jp.dcnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.dcnet.untils.IndexUrl;

@Controller
public class WarehouseController {

	@RequestMapping(IndexUrl.INDEX_USER_CENTER_WAREHOUSECONTORL)
	public ModelAndView warehouse() {

		ModelAndView mv = new ModelAndView("centerHTML/warehouse");

		return mv;
	}

}
