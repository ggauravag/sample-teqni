package org.pkb.springlogin.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.pkb.springlogin.dto.UserDTO;
import org.pkb.springlogin.security.JwtTokenUtil;
import org.pkb.springlogin.service.IUserService;
import org.pkb.springlogin.utils.EmailService;
import org.pkb.springlogin.utils.EncryptionUtility;
import org.pkb.springlogin.utils.GoogleRecaptchaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javassist.NotFoundException;

@Controller
public class MainController {

	@Autowired
	JwtTokenUtil tokenUtil;

	@Autowired
	Environment environment;

	@Autowired
	private GoogleRecaptchaUtils googleRecaptchaUtils;

	@Autowired
	private EmailService emailService;

	@Autowired
	private EncryptionUtility encryptionUtility;

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

	@RequestMapping(value = "/verify-email", method = RequestMethod.GET)
	public ModelAndView verifyEmail(@RequestParam("token") String token, @RequestParam("sign") String signature) {
		String hash = encryptionUtility.toHexString(DigestUtils.md5Digest(token.getBytes()));
		System.out.println("Token: " + token + " Hash: " + hash);
		ModelAndView view = new ModelAndView("email-verify");
		if (hash.equals(signature)) {
			String email = encryptionUtility.decrypt(token);
			try {
				boolean verified = this.userService.verifyUserAccount(email);
				view.addObject("title", "Email Verified");
				if (verified)
					view.addObject("message", "You can now proceed to login !");
				else
					view.addObject("message", "Your account has been already verified !");
			} catch (NotFoundException e) {
				view.addObject("title", "Invalid Email");
				view.addObject("message", "No user account exists to be verified !");
			}
		} else {
			view.addObject("title", "Invalid Link Signature");
			view.addObject("message", "Link has been tampered !");
		}
		return view;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute UserDTO user, HttpServletRequest request) {

		String gCaptchResponse = request.getParameter("g-recaptcha-response");

		boolean isHuman = false;
		try {
			isHuman = googleRecaptchaUtils.postClientResponse(gCaptchResponse);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (!isHuman) {
			ModelAndView view = new ModelAndView("user-created");
			view.addObject("messageTitle", "Unable to create user");
			view.addObject("messageBody", "You could not pass human verification test !");
			return view;
		}

		System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.JSON_STYLE));
		ModelAndView view = new ModelAndView("user-created");
		try {
			userService.registerUser(user);
			String encryptedEmail = encryptionUtility.encrypt(user.getEmail());
			String hash = encryptionUtility.toHexString(DigestUtils.md5Digest(encryptedEmail.getBytes()));

			System.out.println("Encrypted email: " + encryptedEmail + ", Hash: " + hash);
			String message = "Hi, " + user.getFirstName()
					+ "<br/>Kindly click the <a href='http://localhost:8080/SpringMVCAnnotationSecurity/verify-email?token="
					+ URLEncoder.encode(encryptedEmail, "UTF-8") + "&sign=" + URLEncoder.encode(hash, "UTF-8")
					+ "'>link</a> to verify your email account";

			emailService.sendEmail("teqnihome.gaurav@gmail.com", "Teqni Home", user.getEmail(), "Email Verification",
					message);

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