package com.spring.shareComm.board.service;

import java.util.List;

import com.spring.shareComm.board.vo.ArticleVO;

public interface BoardService {
	public List allArticles();
	public void addArticle(ArticleVO boardVO);
}
