package com.almostkbal.web.services.workflow.controllers;

//@CrossOrigin(origins="http://localhost:4200")
//@RestController
public class UserController {

//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private SecurityService securityService;
//
//	@Autowired
//	private UserValidator userValidator;

	
	
	////////////////////////////////////////////////////////////////////////////////////
//	@GetMapping("/registration")
//	public String registration(Model model) {
//		model.addAttribute("userForm", new User());
//
//		return "registration";
//	}
//
//	@PostMapping("/registration")
//	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
//		userValidator.validate(userForm, bindingResult);
//
//		if (bindingResult.hasErrors()) {
//			return "registration";
//		}
//
//		userService.save(userForm);
//
//		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
//
//		return "redirect:/welcome";
//	}
//
//	@GetMapping("/login")
//	public String login(Model model, String error, String logout) {
//		if (error != null)
//			model.addAttribute("error", "Your username and password is invalid.");
//
//		if (logout != null)
//			model.addAttribute("message", "You have been logged out successfully.");
//
//		return "login";
//	}
//
//	@GetMapping({ "/", "/welcome" })
//	public String welcome(Model model) {
//		return "welcome";
//	}
}
