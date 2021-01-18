package com.spring.shareComm.board.vo;

public class LikeVO {
	int likeNO;
	int articleNO;
	String id;
	
	public LikeVO() {}
	
	public LikeVO(int likeNO, int articleNO, String id) {
		super();
		this.likeNO = likeNO;
		this.articleNO = articleNO;
		this.id = id;
	}
	public int getLikeNO() {
		return likeNO;
	}
	public void setLikeNO(int likeNO) {
		this.likeNO = likeNO;
	}
	public int getArticleNO() {
		return articleNO;
	}
	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
