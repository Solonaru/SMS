package com.project.msd.customer;

import java.util.List;
import java.util.Optional;

public interface ICommentService {

	public Optional<Comment> findCommentById(int commentId);

	public List<Comment> findAllComments();

	public void insertComment(Comment comment);

	public void updateComment(Comment comment);

	public void deleteCommentById(int commentId);
}
