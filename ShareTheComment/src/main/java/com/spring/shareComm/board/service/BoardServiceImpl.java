package com.spring.shareComm.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.shareComm.board.dao.BoardDAO;
import com.spring.shareComm.board.vo.ArticleVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO boardDAO;
	@Override
	public List allArticles() throws DataAccessException {
		List articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	@Override
	public void addArticle(ArticleVO boardVO) throws DataAccessException {
		boardDAO.insertArticle(boardVO);
	}
	@Override
	public void replyArticle(ArticleVO articleVO) throws DataAccessException {
		boardDAO.createReply(articleVO);
	}
	@Override
	public ArticleVO article(int articleNO) throws DataAccessException {
		return boardDAO.selectArticle(articleNO);
	}
	@Override
	public void modArticle(ArticleVO articleVO) throws DataAccessException {
		boardDAO.updateArticle(articleVO);
		System.out.println("Sertice articleNO-" + articleVO.getArticleNO() + "content-" + articleVO.getContent());
	}
	@Override
	public void removeArticle(int articleNO) throws DataAccessException {
		boardDAO.deleteArticle(articleNO);
	}
	

}
