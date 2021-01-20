package com.spring.shareComm.like.dao;

import org.springframework.dao.DataAccessException;

import com.spring.shareComm.like.vo.LikeVO;

public interface LikeDAO {
	public int insertLike(LikeVO likeVO) throws DataAccessException;
	public int deleteLike(LikeVO likeVO) throws DataAccessException;
	public void updateLike(int articleNO) throws DataAccessException;
	public int readLike(LikeVO likeVO) throws DataAccessException;
}
