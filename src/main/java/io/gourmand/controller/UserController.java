//package io.gourmand.controller;
//
//<<<<<<< Updated upstream
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.support.SessionStatus;
//
//
//import io.gourmand.dao.UserRepository;
//
//import io.gourmand.domain.User;
//import io.gourmand.service.UserService;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@SessionAttributes("user")
//@Controller
//public class UserController {
//
//	@Autowired
//	private UserService userService;
//	private final UserRepository userRepository;
//	
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(User user, Model model) {
//		User findUser = userService.getUser(user);
//
//		if (findUser != null && findUser.getPassword().equals(user.getPassword())) {
//			model.addAttribute("user", findUser);
//			return "forward:getBoardList";
//		} else {
//			return "redirect:login.html";
//		}
//	}
//
//	@GetMapping("/logout")
//	public String logout(SessionStatus status) {
//		status.setComplete();
//		return "redirect:index.html";
//	}
//
//}
//=======
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import io.gourmand.dao.UserRepository;
//import io.gourmand.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//@Validated
//@RequiredArgsConstructor
//public class UserController {
//   
//   private final UserService userService;
//   private final UserRepository userRepository;
//   
//   /*
//    * 유저 회원가입
//    */
//   @PostMapping("/user/signup")
//   @ResponseStatus(HttpStatus.CREATED)
//   public void signup(@RequestBody UserService.signupRequest request) {
//      userService.join(request);
//   }
//   
//}
//>>>>>>> Stashed changes
