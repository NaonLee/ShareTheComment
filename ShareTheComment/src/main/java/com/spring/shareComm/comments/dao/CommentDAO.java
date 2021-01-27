package com.spring.shareComm.comments.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.spring.shareComm.comments.vo.CommentVO;

public interface CommentDAO {
	public List<CommentVO> selectAllComments(int articleNO) throws DataAccessException;
	
	public void createComment(CommentVO commentVO) throws DataAccessException;
	
	public void updateComment(CommentVO commentVO) throws DataAccessException;
	
	public void deleteComment(int commentNO) throws DataAccessException;
	
	public void updateCountComm(int articleNO) throws DataAccessException;
}
