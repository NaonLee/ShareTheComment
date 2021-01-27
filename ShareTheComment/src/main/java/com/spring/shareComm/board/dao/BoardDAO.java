package com.spring.shareComm.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.spring.shareComm.board.vo.ArticleVO;
import com.spring.shareComm.common.paging.Criteria;

public interface BoardDAO {
	public List selectAllArticles(Criteria criteria) throws DataAccessException;
	public int countArticle() throws DataAccessException;
	public int insertArticle(Map articleMap) throws DataAccessException;
	public int createReply(Map articleMap) throws DataAccessException;
	public ArticleVO selectArticle(int articleNO) throws DataAccessException;
	public void updateArticle(Map articleMap) throws DataAccessException;
	public void deleteArticle(int articleNO) throws DataAccessException;
}
