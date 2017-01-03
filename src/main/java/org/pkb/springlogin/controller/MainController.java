package org.pkb.springlogin.controller;

import java.security.Principal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.pkb.springlogin.dto.UserDTO;
import org.pkb.springlogin.security.JwtTokenUtil;
import org.pkb.springlogin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired
	JwtTokenUtil tokenUtil;

	@Autowired
	Environment environment;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		return "welcomePage";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage(Model model, @RequestParam("authToken") String authToken,
			Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView("adminPage");
		modelAndView.addObject("authToken", authToken);
		modelAndView.addObject("authParameter", environment.getProperty("jwt.parameter"));
		return modelAndView;
	}

	@RequestMapping(value = "/logout-success", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logout-success";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView userInfo(Model model, @RequestParam("authToken") String authToken,
			Authentication authentication) {
		String userName = authentication.getName();
		String message = "Hello " + userName;
		ModelAndView view = new ModelAndView("user");
		view.addObject("authToken", authToken);
		view.addObject("authParameter", environment.getProperty("jwt.parameter"));
		view.addObject("message", message);
		return view;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute UserDTO user) {

		System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.JSON_STYLE));
		ModelAndView view = new ModelAndView("user-created");
		try {
			userService.registerUser(user);
			view.addObject("messageTitle", "User Successfully Created");
			view.addObject("messageBody", "Kindly check your registered email for verification link !");
		} catch (Exception e) {
			view.addObject("messageTitle", "Error while Registering user !");
			view.addObject("messageBody", "Error Message: " + e.getMessage());
		}

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