package com.spring.shareComm.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.shareComm.board.vo.ArticleVO;
import com.spring.shareComm.common.paging.Criteria;

public interface BoardService {
	public List allArticles(Criteria criteria) throws DataAccessException;
	public int count() throws DataAccessException;
	public int addArticle(Map articleMap) throws DataAccessException;
	public ArticleVO article(int articleNO) throws DataAccessException;
	public void modArticle(Map articleMap) throws DataAccessException;
	public void removeArticle(int articleNO) throws DataAccessException;
	public int replyArticle(Map articleMap) throws DataAccessException;
}
