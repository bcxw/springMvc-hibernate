package controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import service.LoginService;

@Controller  
@RequestMapping("/loginAction")  
public class LoginAction {
	
//	@Autowired
//	private LoginService loginService;
	
	/**
	 * 注销登录
	 * @param httpSession
	 */
	@ResponseBody
	@RequestMapping("/logout.action")
	public void logout(HttpSession httpSession){
		httpSession.removeAttribute("userName");
	}
	
	/**
	 * 登录
	 * @param req
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping("/login.action")
//	public ModelAndView login(HttpSession httpSession,@RequestParam Map<String,String> paramMap){
//		Map<String, Object> result=loginService.login(paramMap);
//		String redirectUrl="";
//		if((boolean) result.get("success")){
//			httpSession.setAttribute("userName", paramMap.get("userName"));
//			redirectUrl= "index.jsp";
//		}else{
//			httpSession.setAttribute("message", result.get("message"));
//			redirectUrl= "login.jsp";
//		}
//		return new ModelAndView("redirect:/" + redirectUrl);
//	}
	
}
