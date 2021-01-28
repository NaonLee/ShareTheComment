package com.spring.shareComm.common.mail;



import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller("mailController")
@EnableAsync
public class MailController {
	@Autowired
	private MailService mailService;
	
	//Send email to the administrator (email has already set)
	@RequestMapping(value="/board/sendEmail.do", method=RequestMethod.POST)
	public ModelAndView sendMail(@RequestParam("email") String email, @RequestParam("title") String title, @RequestParam("body") String body,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		mailService.sendMail(email, title, body);				//send to admin email
		
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("redirect:/main.do");
		return mav;
	}
	
	//Send password to email stored in DB
	@RequestMapping(value="/member/sendPwd.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView sendMail(@RequestParam("email") String email, @RequestParam("pwd") String pwd,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		mailService.sendPwd(email, pwd);				//send to administrator email
		
		ModelAndView mav = new ModelAndView();	
		mav.addObject("fail", "succeed");
		mav.setViewName("redirect:/member/findPWForm.do");
		
		return mav;
	}
}
