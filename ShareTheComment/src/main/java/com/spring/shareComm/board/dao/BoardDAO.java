package com.spring.shareComm.board.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.shareComm.board.vo.ArticleVO;
import com.spring.shareComm.common.paging.Criteria;

public interface BoardDAO {
	public List selectAllArticles(Criteria criteria) throws DataAccessException;
	public int countArticle() throws DataAccessException;
	public void insertArticle(ArticleVO articleVO) throws DataAccessException;
	public ArticleVO selectArticle(int articleNO) throws DataAccessException;
	public void updateArticle(ArticleVO articleVO) throws DataAccessException;
	public void deleteArticle(int articleNO) throws DataAccessException;
	public void createReply(ArticleVO articleVO) throws DataAccessException;
}
