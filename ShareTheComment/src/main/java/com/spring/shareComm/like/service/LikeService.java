package com.spring.shareComm.like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.spring.shareComm.like.dao.LikeDAO;
import com.spring.shareComm.like.vo.LikeVO;

public interface LikeService {
	
	public int addLike(LikeVO likeVO) throws DataAccessException;
	public int removeLike(LikeVO likeVO) throws DataAccessException;
	public int countLike(LikeVO likeVO) throws DataAccessException;
}
