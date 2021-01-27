package com.spring.shareComm.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.shareComm.board.dao.BoardDAO;
import com.spring.shareComm.board.vo.ArticleVO;
import com.spring.shareComm.common.paging.Criteria;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO boardDAO;
	
	@Override	//select all articles
	public List allArticles(Criteria criteria) throws DataAccessException {
		List articlesList = boardDAO.selectAllArticles(criteria);
		return articlesList;
	}
	@Override	//count articles
	public int count() throws DataAccessException{
		return boardDAO.countArticle();
	}
	
	@Override	//add a new article
	public int addArticle(Map articleMap) throws DataAccessException {
		return boardDAO.insertArticle(articleMap);
	}
	@Override	//reply
	public int replyArticle(Map articleMap) throws DataAccessException {
		return boardDAO.createReply(articleMap);
	}
	@Override	//select a article by articleNO
	public ArticleVO article(int articleNO) throws DataAccessException {
		return boardDAO.selectArticle(articleNO);
	}
	@Override	//update article
	public void modArticle(Map articleMap) throws DataAccessException {
		boardDAO.updateArticle(articleMap);
	}
	@Override	//delete article
	public void removeArticle(int articleNO) throws DataAccessException {
		boardDAO.deleteArticle(articleNO);
	}
	

}
