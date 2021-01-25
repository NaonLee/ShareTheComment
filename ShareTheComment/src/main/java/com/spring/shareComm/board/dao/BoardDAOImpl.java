package com.spring.shareComm.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.shareComm.board.vo.ArticleVO;
import com.spring.shareComm.common.paging.Criteria;

@Component("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override	//to show all articles
	public List selectAllArticles(Criteria criteria) throws DataAccessException {
		//pass page information by criteria
		List<ArticleVO> boardList = sqlSession.selectList("mapper.board.selectAllArticles", criteria);	
		return boardList;
	}

	public int countArticle() throws DataAccessException{
		return sqlSession.selectOne("mapper.board.countArticle");
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
		System.out.println("sqlSession will delete" + articleNO);
		sqlSession.delete("mapper.board.deleteArticle", articleNO);
	}

}
