package com.spring.shareComm.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.shareComm.board.service.BoardService;
import com.spring.shareComm.board.vo.ArticleVO;

@Controller("boardController")
public class BoardControllerImpl implements BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	ArticleVO articleVO;
	
	@Override
	@RequestMapping(value="/board/listArticles.do")
	public ModelAndView allArticles() throws Exception {
		ModelAndView mav = new ModelAndView();
		List articles = boardService.allArticles();
		mav.addObject("articles", articles);
		mav.setViewName("/board/listArticles");
		return mav;
	}

	@Override
	@RequestMapping(value="/board/addArticle.do", method=RequestMethod.POST)
	public ModelAndView addArticle(@ModelAttribute("article") ArticleVO article) throws Exception {
		ModelAndView mav = new ModelAndView();
		System.out.println("call add");
		boardService.addArticle(article);
		mav.setViewName("redirect:/board/listArticles.do");
		return mav;
	}

	@RequestMapping(value="/board/viewArticle.do")
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request) throws Exception {
		System.out.println("call view-article");
		
		articleVO = boardService.article(articleNO);
		ModelAndView mav = new ModelAndView();
		String viewName = getViewName(request);
		
		mav.addObject("article", articleVO);
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping(value="/board/modArticle.do")
	public ModelAndView modArticle(@ModelAttribute("article") ArticleVO article, HttpServletRequest request) throws Exception {
		System.out.println("call mod-article");
		System.out.println(article.getContent());
		boardService.modArticle(article);
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/board/listArticles.do");
		return mav;
	}
	
	@RequestMapping(value="/board/*Form.do")
	public ModelAndView form(HttpServletRequest request) throws Exception {
		System.out.println("call addForm");
		ModelAndView mav = new ModelAndView();
		String viewName = getViewName(request);
		
		mav.setViewName(viewName);
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
