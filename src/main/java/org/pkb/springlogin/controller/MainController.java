package org.pkb.springlogin.controller;

import java.security.Principal;

import org.pkb.springlogin.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired
	JwtTokenUtil tokenUtil;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		return "welcomePage";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		return "adminPage";
	}

	@RequestMapping(value = "/logout-success", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logout-success";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView userInfo(Model model, @RequestParam("authToken") String authToken,
			Authentication authentication) {
		// After user login successfully.
		String userName = authentication.getName();

		String message = "Hello " + userName;

		ModelAndView view = new ModelAndView("user");
		view.addObject("authToken", authToken);
		view.addObject("message", message);
		return view;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			model.addAttribute("message",
					"Hi " + principal.getName() + "<br> You do not have permission to access this page!");
		} else {
			model.addAttribute("msg", "You do not have permission to access this page!");
		}
		return "403Page";
	}
}