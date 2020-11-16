package com.zendesk.ticketviewer.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zendesk.ticketviewer.constants.Constant;

@RestController
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePage(ModelMap model) {
		ModelAndView mav = new ModelAndView(Constant.HOME_PAGE);
		return mav;
	}
}
