package com.spring.shareComm.like.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.shareComm.like.service.LikeService;
import com.spring.shareComm.like.vo.LikeVO;
import com.spring.shareComm.member.vo.MemberVO;

@Controller("likeController")
public class LikeControllerImpl implements LikeController {
	@Autowired
	LikeService likeService;
	@Autowired
	LikeVO likeVO;
	
	@ResponseBody
	@RequestMapping(value="/like", method=RequestMethod.POST, produces="application/json")
	public void like(@RequestParam Map<String, String> article, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("heart call, articleNO: " + article.get("articleNO"));
		String id = ((MemberVO)(request.getSession().getAttribute("logMember"))).getId();
		int articleNO = Integer.parseInt(article.get("articleNO"));
		PrintWriter pt = response.getWriter();
		
		likeVO.setId(id);
		likeVO.setArticleNO(articleNO);
		int count = likeService.countLike(likeVO);
		

		//if user already liked the article
		if(count == 0) {							//SELECT COUNT(*) FROM table WHERE id=id and articleNO=articleNO
			likeService.addLike(likeVO);
			pt.write("Liked");
			
		} else {									//if user hasn't liked the article
			likeService.removeLike(likeVO);
			pt.write("Disliked");
		}

	}
	
	
}
