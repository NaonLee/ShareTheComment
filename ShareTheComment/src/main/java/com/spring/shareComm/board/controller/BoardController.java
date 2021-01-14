package com.spring.shareComm.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.shareComm.board.vo.ArticleVO;

public interface BoardController {
	public ModelAndView allArticles() throws Exception;
	public ModelAndView addArticle(@ModelAttribute("article") ArticleVO article) throws Exception;
	public ModelAndView removeArticle(@RequestParam("articleNO") int no, HttpServletRequest request) throws Exception;
}
