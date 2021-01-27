package com.spring.shareComm.comments.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.shareComm.comments.service.CommentService;
import com.spring.shareComm.comments.vo.CommentVO;

@RestController
@RequestMapping("/comments")
public class CommentControllerImpl implements CommentController {
	@Autowired
	CommentService commentService;
	
	//Show all comments
	@RequestMapping(value="/all/{articleNO}", method = RequestMethod.GET)
	public ResponseEntity<List<CommentVO>> listComment(@PathVariable("articleNO") Integer articleNO){
		ResponseEntity<List<CommentVO>> entity = null;
		try {
			entity = new ResponseEntity<List<CommentVO>>(commentService.allComments(articleNO), HttpStatus.OK);		//send status ok when all comments successfully loaded
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<CommentVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	//Insert new comment
	@RequestMapping(value="", method = RequestMethod.POST)
	public ResponseEntity<String> addComment(@RequestBody CommentVO commentVO) {
		ResponseEntity<String> entity = null;
		try {
			commentService.addComment(commentVO);
			entity = new ResponseEntity<String>("Addsuccess", HttpStatus.OK);				//send status ok when new comment successfully added
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//update comment
	@RequestMapping(value="/{commentNO}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> modComment(@PathVariable("commentNO") Integer commentNO, @RequestBody CommentVO commentVO){
		ResponseEntity<String> entity = null;
		try {
			commentVO.setCommentNO(commentNO);
			commentService.modComment(commentVO);
			entity = new ResponseEntity<String>("ModSuccess", HttpStatus.OK);				//send status ok when new comment successfully updated
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//delete comment
	@RequestMapping(value = "/{commentNO}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeComment(@PathVariable("commentNO") Integer commentNO){
		ResponseEntity<String> entity = null;
		try {
			commentService.removeComment(commentNO);
			entity = new ResponseEntity<String>("DelSuccess", HttpStatus.OK);				//send status ok when new comment successfully deleted
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
