package com.spring.shareComm.like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.shareComm.like.dao.LikeDAO;
import com.spring.shareComm.like.vo.LikeVO;

@Service("likeService")
@Transactional
public class LikeServiceImpl implements LikeService {
	@Autowired
	LikeDAO likeDAO;
	
	@Override
	public int addLike(LikeVO likeVO) throws DataAccessException {
		return likeDAO.insertLike(likeVO);
	}

	@Override
	public int removeLike(LikeVO likeVO) throws DataAccessException {
		return likeDAO.deleteLike(likeVO);
	}
	@Override
	public int countLike(LikeVO likeVO) throws DataAccessException {
		return likeDAO.readLike(likeVO);
	}
}
