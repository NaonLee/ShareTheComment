package com.spring.shareComm.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shareComm.board.dao.BoardDAO;
import com.spring.shareComm.board.vo.ArticleVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO boardDAO;
	@Override
	public List allArticles() {
		List articlesList = boardDAO.selectAllArticles();
		return articlesList;
	}
	@Override
	public void addArticle(ArticleVO boardVO) {
		boardDAO.insertArticle(boardVO);
	}

}
