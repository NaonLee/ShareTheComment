package com.spring.shareComm.board.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.shareComm.board.vo.ArticleVO;
import com.spring.shareComm.common.paging.Criteria;

public interface BoardService {
	public List allArticles(Criteria criteria) throws DataAccessException;
	public int count() throws DataAccessException;
	public void addArticle(ArticleVO boardVO) throws DataAccessException;
	public ArticleVO article(int articleNO) throws DataAccessException;
	public void modArticle(ArticleVO articleVO) throws DataAccessException;
	public void removeArticle(int articleNO) throws DataAccessException;
	public void replyArticle(ArticleVO articleVO) throws DataAccessException;
}
