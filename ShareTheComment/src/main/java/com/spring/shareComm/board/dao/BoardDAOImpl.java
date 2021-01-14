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

	@Override	//add a new article
	public void insertArticle(ArticleVO articleVO) {
		int articleNO = sqlSession.selectOne("mapper.board.createArticleNO");		//create article number(maximum articeNO in DB + 1)
		articleVO.setArticleNO(articleNO);
		sqlSession.insert("mapper.board.insertArticle", articleVO);
	}

	@Override
	public ArticleVO selectArticle(int articleNO) {
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
	}

	@Override
	public void updateArticle(ArticleVO articleVO) {
		sqlSession.update("mapper.board.updateArticle", articleVO);
		System.out.println("DAO articleNO-" + articleVO.getArticleNO() + "content-" + articleVO.getContent());
	}
	
	

}
