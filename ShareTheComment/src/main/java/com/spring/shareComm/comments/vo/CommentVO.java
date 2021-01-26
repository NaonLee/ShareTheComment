package com.spring.shareComm.comments.vo;

import java.util.Date;

import org.springframework.stereotype.Repository;

@Repository("commentVO")
public class CommentVO {
	private int commentNO;
	private String comment_content;
	private String id;
	private int articleNO;
	private Date comment_date;	
	
	CommentVO(){}
	
	public CommentVO(int commentNO, String comment_content, String id, int articleNO, Date comment_date) {
		this.commentNO = commentNO;
		this.comment_content = comment_content;
		this.id = id;
		this.articleNO = articleNO;
		this.comment_date = comment_date;
	}
	
	public int getCommentNO() {
		return commentNO;
	}
	public void setCommentNO(int commentNO) {
		this.commentNO = commentNO;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getArticleNO() {
		return articleNO;
	}
	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	public Date getComment_date() {
		return comment_date;
	}

	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}

	@Override
	public String toString() {
		return "CommentVO [commentNO=" + commentNO + ", comment_content=" + comment_content + ", id=" + id
				+ ", articleNO=" + articleNO + ", comment_date=" + comment_date + "]";
	}
	
	
}
