package com.spring.shareComm.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.shareComm.member.service.MemberService;
import com.spring.shareComm.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	@Autowired
	MemberService memberService;
	@Autowired
	MemberVO memberVO;
	
	@RequestMapping(value= {"/","/main.do"}, method=RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("main");
		
		return mav;
	}
	
	//list all members
	@Override	
	@RequestMapping(value="/member/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text; charset=utf-8");
		
		ModelAndView mav = new ModelAndView();
		List membersList = memberService.selectAll();
		String viewName = getViewName(request);
		
		//Bind members list 
		mav.addObject("membersList", membersList);
		mav.setViewName(viewName);
		System.out.println("viewName: " +viewName);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value="/member/idCheck", method=RequestMethod.POST)
	public void checkUser(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter pt = response.getWriter();
		System.out.println("id: " + id);
		
		if(id == null || id == "") {				//if user isn't enter an id
			pt.write("empty");
		} else {			
			
			memberVO = memberService.select(id);
			
			if(memberVO != null){	//there is member with the ID
				pt.write("exist");
			} else {
				pt.write("none");
			}
		}
			
	}
	
	//insert a new member
	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) {
		memberService.addMember(member);
		
		ModelAndView mav = new ModelAndView();
		//after adding a new member, going back to the list (will be updated to going back to the main)
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;
	}

	//delete a member
	@Override
	@RequestMapping(value="/member/removeMember.do", method=RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
		//find data by id
		memberService.removeMember(id);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;
	}

	//update a member
	@Override
	@RequestMapping(value="/member/modMember.do", method=RequestMethod.POST)
	public ModelAndView modMember(MemberVO memberVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		memberService.modMember(memberVO);
		
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;
	}
	
	//login
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.login(member);
		HttpSession session = request.getSession();
		
		if(memberVO != null) {	
			session.setAttribute("isLogOn", true);		//status of login
			session.setAttribute("logMember", memberVO);	//information of user logging in
			
			String action = (String)session.getAttribute("action");
			System.out.println("action: " + action);
			mav.setViewName("redirect:/main.do");
			
		} else {					//if id and password don't match to data in DB
			System.out.println("wrong id or pwd");
			mav.addObject("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}
	
	@RequestMapping(value="/member/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		session.setAttribute("isLogOn", false);
		session.removeAttribute("logMember");
		
		mav.setViewName("redirect:/main.do");
		return mav;
	}
	
	//call forms(memberForm, modForm, etc)
	@RequestMapping(value="/member/*Form.do")
	public ModelAndView modForm(@RequestParam(value="result", required = false) String result, @RequestParam(value="id", required = false) String id, HttpServletRequest request) throws Exception {	
		//if there is an id, put data and there is no id, put null into 'id'
		ModelAndView mav = new ModelAndView();
		String viewName =  getViewName(request);
		
		//if id value exists
		if(id != null && id.length() != 0) {
			memberVO = memberService.select(id);
			mav.addObject("member", memberVO);
		}
		mav.addObject("result", result);
		mav.setViewName(viewName);	
		System.out.println("viewName: " +viewName);
		return mav;
	}

	
	//return view name from requested url
	private String getViewName(HttpServletRequest request) throws Exception {
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if (uri == null || uri.trim().equals("")) {
			uri = request.getRequestURI();				//if uri is null, force to bring
		}

		int begin = 0;
		if (!((contextPath == null) || ("".equals(contextPath)))) {
			begin = contextPath.length();
		}

		int end;
		if (uri.indexOf(";") != -1) {
			end = uri.indexOf(";");
		} else if (uri.indexOf("?") != -1) {
			end = uri.indexOf("?");
		} else {
			end = uri.length();
		}

		String viewName = uri.substring(begin, end);
		if (viewName.indexOf(".") != -1) {
			viewName = viewName.substring(0, viewName.lastIndexOf("."));
		}
		if (viewName.lastIndexOf("/") != -1) {
			viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
		}
		return viewName;
	}
}
