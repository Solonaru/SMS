package com.project.msd.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService{
	
	@Autowired
	private ICommentRepository commentRepository;

	public Optional<Comment> findCommentById(int commentId) {
		return commentRepository.findById(commentId);
	}

	public List<Comment> findAllComments() {
		return (List<Comment>) commentRepository.findAll();
	}

	public void insertComment(Comment comment) {
		commentRepository.save(comment);
	}

	public void updateComment(Comment comment) {
		commentRepository.save(comment);
	}

	public void deleteCommentById(int commentId) {
		commentRepository.deleteById(commentId);
	}
}
