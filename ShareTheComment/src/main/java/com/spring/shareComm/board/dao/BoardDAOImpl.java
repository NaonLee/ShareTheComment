package com.spring.shareComm.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.InitBinder;

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

	@Override	//count articles
	public int countArticle() throws DataAccessException{
		return sqlSession.selectOne("mapper.board.countArticle");
	}
	
	@Override	//add a new article
	public int insertArticle(Map articleMap) throws DataAccessException {
		int articleNO = sqlSession.selectOne("mapper.board.createArticleNO");		//create article number(maximum articeNO in DB + 1)
		articleMap.put("articleNO", articleNO);
		sqlSession.insert("mapper.board.insertArticle", articleMap);
		return articleNO;
	}
	
	@Override	//add reply(similar to writing article)
	public int createReply(Map articleMap) throws DataAccessException{
		int articleNO = sqlSession.selectOne("mapper.board.createArticleNO");		//create article number(maximum articeNO in DB + 1)
		articleMap.put("articleNO", articleNO);
		sqlSession.insert("mapper.board.insertReply", articleMap);
		return articleNO;
	}

	@Override	//select a specific article with articleNO
	public ArticleVO selectArticle(int articleNO) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
	}

	@Override	//update article
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.board.updateArticle", articleMap);
	}

	@Override	//delete article
	public void deleteArticle(int articleNO) throws DataAccessException {
		System.out.println("sqlSession will delete" + articleNO);
		sqlSession.delete("mapper.board.deleteArticle", articleNO);
	}

}
