package com.spring.shareComm.comments.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.spring.shareComm.comments.vo.CommentVO;

@Component("commentDAO")
public class CommentDAOImpl implements CommentDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<CommentVO> selectAllComments(int articleNO) throws DataAccessException {
		List<CommentVO> comments = sqlSession.selectList("mapper.comment.selectAllComments", articleNO);
		updateCountComm(articleNO);
		return comments;
	}

	@Override
	public void createComment(CommentVO commentVO) throws DataAccessException {
		int commentNO = sqlSession.selectOne("mapper.comment.createCommentNO");
		commentVO.setCommentNO(commentNO);
		sqlSession.insert("mapper.comment.insertComment", commentVO);
		updateCountComm(commentVO.getArticleNO());
	}

	@Override
	public void updateComment(CommentVO commentVO) throws DataAccessException {
		sqlSession.update("mapper.comment.updateComment", commentVO);
	}

	@Override
	public void deleteComment(int commentNO) throws DataAccessException {
		sqlSession.delete("mapper.comment.deleteComment", commentNO);
	}

	@Override
	public void updateCountComm(int articleNO) throws DataAccessException {
		sqlSession.update("mapper.comment.updateCountComm", articleNO);
	}

}
