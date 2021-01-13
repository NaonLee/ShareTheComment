package com.spring.shareComm.board.dao;

import java.util.List;

import com.spring.shareComm.board.vo.ArticleVO;

public interface BoardDAO {
	public List selectAllArticles();
	public void insertArticle(ArticleVO boardVO);
}
