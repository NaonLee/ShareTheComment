package com.spring.shareComm.comments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.spring.shareComm.comments.dao.CommentDAO;
import com.spring.shareComm.comments.vo.CommentVO;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDAO commentDAO;
	
	@Override
	public List<CommentVO> allComments(int articleNO) throws DataAccessException {
		return commentDAO.selectAllComments(articleNO);
	}

	@Override
	public void addComment(CommentVO commentVO) throws DataAccessException {
		commentDAO.createComment(commentVO);
	}

	@Override
	public void modComment(CommentVO commentVO) throws DataAccessException {
		commentDAO.updateComment(commentVO);
	}

	@Override
	public void removeComment(int commentNO) throws DataAccessException {
		commentDAO.deleteComment(commentNO);
	}

	
}
