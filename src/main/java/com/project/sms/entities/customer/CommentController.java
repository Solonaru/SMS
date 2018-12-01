package com.project.sms.entities.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sms.utils.DisplayData;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private DisplayData dataDisplay;

	@RequestMapping(value = "/{commentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Comment> findCommentById(@PathVariable("commentId") int commentId) {
		dataDisplay.printCrudInfo(commentId);
		return commentService.findCommentById(commentId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Comment> getComments() {
		dataDisplay.printCrudInfo();
		return commentService.findAllComments();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertComment(@RequestBody Comment comment) {
		dataDisplay.printCrudInfo();
		commentService.insertComment(comment);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateComment(@RequestBody Comment comment) {
		dataDisplay.printCrudInfo();
		commentService.updateComment(comment);
	}

	@RequestMapping(value = "/delete/{commentId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteComment(@PathVariable("commentId") int commentId) {
		dataDisplay.printCrudInfo(commentId);
		commentService.deleteCommentById(commentId);
	}
}
