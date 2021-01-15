package com.spring.shareComm.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.shareComm.board.vo.ArticleVO;

@Component("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override	//to show all articles
	public List selectAllArticles() throws DataAccessException {
		List<ArticleVO> boardList = sqlSession.selectList("mapper.board.selectAllArticles");	
		return boardList;
	}

	@Override	//add a new article
	public void insertArticle(ArticleVO articleVO) throws DataAccessException {
		int articleNO = sqlSession.selectOne("mapper.board.createArticleNO");		//create article number(maximum articeNO in DB + 1)
		articleVO.setArticleNO(articleNO);
		sqlSession.insert("mapper.board.insertArticle", articleVO);
	}
	
	public void createReply(ArticleVO articleVO) throws DataAccessException{
		int articleNO = sqlSession.selectOne("mapper.board.createArticleNO");		//create article number(maximum articeNO in DB + 1)
		articleVO.setArticleNO(articleNO);
		sqlSession.insert("mapper.board.insertReply", articleVO);
	}

	@Override
	public ArticleVO selectArticle(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
	}

	@Override
	public void updateArticle(ArticleVO articleVO) throws DataAccessException {
		sqlSession.update("mapper.board.updateArticle", articleVO);
		System.out.println("DAO articleNO-" + articleVO.getArticleNO() + "content-" + articleVO.getContent());
	}

	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle", articleNO);
	}

}
