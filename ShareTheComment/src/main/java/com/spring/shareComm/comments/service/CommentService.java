package com.spring.shareComm.comments.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.shareComm.comments.vo.CommentVO;

public interface CommentService {
	public List<CommentVO> allComments(int articleNO) throws DataAccessException;
	
	public void addComment(CommentVO commentVO) throws DataAccessException;
	
	public void modComment(CommentVO commentVO) throws DataAccessException;
	
	public void removeComment(int commentNO) throws DataAccessException;
}
