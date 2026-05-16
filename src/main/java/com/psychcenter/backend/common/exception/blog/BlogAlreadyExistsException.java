package com.psychcenter.backend.common.exception.blog;

public class BlogAlreadyExistsException extends RuntimeException {

    public BlogAlreadyExistsException(String message) {
        super(message);
    }
}