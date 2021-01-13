package com.spring.shareComm.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.shareComm.board.vo.ArticleVO;

@Component("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override	//to show all articles
	public List selectAllArticles() {
		List<ArticleVO> boardList = sqlSession.selectList("mapper.board.selectAllArticles");	
		return boardList;
	}

	@Override
	public void insertArticle(ArticleVO boardVO) {
		sqlSession.insert("mapper.board.insertArticle", boardVO);
	}

}
