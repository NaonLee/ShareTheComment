package com.spring.shareComm.like.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.spring.shareComm.like.vo.LikeVO;

@Component("likeDAO")
public class LikeDAOImpl implements LikeDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertLike(LikeVO likeVO) throws DataAccessException {
		int result = sqlSession.insert("mapper.article_like.createLike", likeVO);
		updateLike(likeVO.getArticleNO());
		System.out.println("LikeDAO-insert result: " + result);
		return result;
	}

	@Override
	public int deleteLike(LikeVO likeVO) throws DataAccessException {
		int result = sqlSession.delete("mapper.article_like.deleteLike", likeVO);
		updateLike(likeVO.getArticleNO());
		System.out.println("LikeDAO-delete result: " + result);
		return result;
	}

	@Override
	public void updateLike(int articleNO) throws DataAccessException {
		sqlSession.update("mapper.article_like.updateLike", articleNO);
	}

	@Override
	public int readLike(LikeVO likeVO) throws DataAccessException {
		int likeCount = 0;
		try {	//if the value exists in the 
			likeCount = sqlSession.selectOne("mapper.article_like.readLike", likeVO);
			System.out.println("Count right after sql: " + likeCount);
			return likeCount;
		} catch (Exception e) {	//if 
			System.out.println(e);
			return likeCount;
		}
		
	}
	
	
}
