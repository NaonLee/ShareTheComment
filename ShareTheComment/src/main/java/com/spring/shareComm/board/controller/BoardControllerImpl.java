package com.spring.shareComm.board.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	@RequestMapping(value="/board/listArticles.do")		//list all article
	public ModelAndView allArticles(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		List articles = boardService.allArticles();
		
		/* to check level
		 * Iterator<ArticleVO> a = articles.iterator();
		 * 
		 * while(a.hasNext()) { int n = a.next().getLevel();
		 * System.out.println("level: " + n); }
		 */
		
		String viewName = getViewName(request);
		mav.addObject("articles", articles);			//pass all article information list
		mav.setViewName(viewName);
		return mav;
	}

	@Override
	@RequestMapping(value="/board/addArticle.do", method=RequestMethod.POST)	//add a new article
	public ModelAndView addArticle(@ModelAttribute("article") ArticleVO article) throws Exception {
		ModelAndView mav = new ModelAndView();
		System.out.println("call add");
		boardService.addArticle(article);		//add
		mav.setViewName("redirect:/board/listArticles.do");
		return mav;
	}

	@RequestMapping(value="/board/viewArticle.do")	//view article detail
	public ModelAndView viewArticle(@RequestParam("articleNO") int articleNO, HttpServletRequest request) throws Exception {
		System.out.println("call view-article");
		
		articleVO = boardService.article(articleNO);
		ModelAndView mav = new ModelAndView();
		String viewName = getViewName(request);
		
		mav.addObject("article", articleVO);		//pass article information to viewArticle.jsp
		mav.setViewName(viewName);
		return mav;
	}
	
	@RequestMapping(value="/board/modArticle.do")	//modify article
	public ModelAndView modArticle(@ModelAttribute("article") ArticleVO article, HttpServletRequest request) throws Exception {
		System.out.println("call mod-article");
		System.out.println(article.getContent());
		boardService.modArticle(article);			//modify
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("redirect:/board/listArticles.do");
		return mav;
	}
	
	@RequestMapping(value="/board/removeArticle.do")	//delete article
	public ModelAndView removeArticle(@RequestParam("articleNO") int no, HttpServletRequest request) throws Exception {
		System.out.println("call delete-article");
		ModelAndView mav = new ModelAndView();
		boardService.removeArticle(no);					//delete article by specific number
		
		mav.setViewName("redirect:/board/listArticles.do");
		return mav;
	}
	
	@RequestMapping(value="/board/*Form.do")	//replyForm, articleForm
	public ModelAndView form(@RequestParam(value="parentNO", required = false) String parentNO, HttpServletRequest request) throws Exception {
		System.out.println("call Form");
		System.out.println("form.do parent: " + parentNO);
		ModelAndView mav = new ModelAndView();
		if(parentNO != null)
			mav.addObject("parentNO", Integer.parseInt(parentNO));		//pass parent number for replyForm
		
		HttpSession session = request.getSession();
		System.out.println("session: " + session.getAttribute("isLogOn"));
		
		String viewName = getViewName(request);
		mav.setViewName(viewName);
		return mav;
	}
	@RequestMapping(value="/board/replyArticle.do")
	public ModelAndView reply(@ModelAttribute("reply") ArticleVO article, @RequestParam("parentNO") int parentNO, HttpServletRequest request) throws Exception{
		System.out.println("parentNO " + parentNO);
		ModelAndView mav = new ModelAndView();
		article.setParentNO(parentNO);
		boardService.replyArticle(article);
		
		mav.setViewName("redirect:/board/listArticles.do");
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
