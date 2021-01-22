package com.spring.shareComm.member.controller;


import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.github.scribejava.core.model.OAuth2AccessToken;
import com.spring.shareComm.member.service.MemberService;
import com.spring.shareComm.member.vo.MemberVO;
import com.spring.shareComm.socialLogin.NaverLoginBO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController{
	@Autowired
	MemberService memberService;
	@Autowired
	MemberVO memberVO;
	//NaverLoginBO for Naver login
		private NaverLoginBO naverLoginBO;
		private String apiResult = null;
		
		@Autowired
		private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
			this.naverLoginBO = naverLoginBO;
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
	@RequestMapping(value="/member/idCheck", method= {RequestMethod.POST, RequestMethod.GET})
	public void checkUser(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("id Check");
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
	
	@RequestMapping("/member/findPwd.do")
	public ModelAndView findPwd(@ModelAttribute("member") MemberVO member, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ModelAndView mav = new ModelAndView();
		
		memberVO = memberService.select(member);
		
		if(memberVO != null) {
			System.out.println("Yeah!");
			String email = memberVO.getEmail();
			String pwd = memberVO.getPwd();
			mav.addObject("email", email);
			mav.addObject("pwd", pwd);
			mav.setViewName("redirect:/member/sendPwd.do");
		} else {
			System.out.println("find pwd failed.");
			mav.addObject("fail", "failed");
			mav.setViewName("redirect:/member/findPWForm.do");
		}
		
		return mav;
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
	
	//login with social account(Google, Naver)
	@RequestMapping(value="/member/socialLogin.do", method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView socialLogin(@RequestParam(required = false) String code, @RequestParam(required = false)  
									String state, HttpSession session)  throws Exception {
		ModelAndView mav = new ModelAndView("main");
		OAuth2AccessToken oauthToken;
		
		//if user is login with Naver
		if(code != null && state != "") {
			oauthToken = naverLoginBO.getAccessToken(session, code, state);
			
			//<1> Bring user information
			apiResult = naverLoginBO.getUserProfile(oauthToken);		//JSON data with String structure
			
			/*The structure of apiResult
			 *{"resultcode":00,
			 *"message":"success",
			 *"response":{"id":"id","nickname":"nickname","age":"20","gender":"M", "email":"email@email","name":"name"}} 
			 */
			
			//<2> Convert apiResult String to JSON
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(apiResult);
			JSONObject jsonObj = (JSONObject) obj;
			
			//<3> Data parsing
			//response parsing
			JSONObject response_obj = (JSONObject) jsonObj.get("response");	//"response":{"id":"id","nickname":"nickname","age":"20","gender":"M", "email":"email@email","name":"name"}}
			
			//<4> Save data for sign up
			String id = "N" + (String) response_obj.get("id");
			String name = (String) response_obj.get("name");
			String email = (String) response_obj.get("email");
			String password = Double.toString((Math.random())*10000);		//Creat random number for password
			//<5> Check if user has already been registered

			if(memberService.select(id) != null) {
				session.setAttribute("isLogOn", true);		//status of login
				session.setAttribute("logMember", id);		//information of user logging in
			} else {										//user hasn't been registered
				memberVO.setId(id);
				memberVO.setName(name);
				memberVO.setEmail(email);
				memberVO.setPwd(password);
				memberService.addMember(memberVO);
				
				System.out.println("Add..Naver member.." + memberVO.getId());
				session.setAttribute("isLogOn", true);		//status of login
				session.setAttribute("logMember", memberVO);		//information of user logging in
			}
			
			mav.addObject("result", apiResult);
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
	public ModelAndView modForm(@RequestParam(value="result", required = false) String result, @RequestParam(value="id", required = false) String id, 
			HttpServletRequest request, HttpSession session) throws Exception {	
		//if there is an id, put data and there is no id, put null into 'id'
		ModelAndView mav = new ModelAndView();
		String viewName =  getViewName(request);
		
		//if id value exists
		if(id != null && id.length() != 0) {
			memberVO = memberService.select(id);
			mav.addObject("member", memberVO);
		}
		
		//create authentication URL for Naver
		String naverAuthUrl = naverLoginBO.getAuthenticationUrl(session);
		//Bind Naver URL
		mav.addObject("url", naverAuthUrl);
				
		
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
