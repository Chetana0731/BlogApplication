package com.chetana.Blog.Application.Exceptions;

public class UnAuthorisedException extends RuntimeException{

    public UnAuthorisedException(Integer id)
    {
        super(String.format("Ceo details cannot be updated with id %s",id));
    }
}
