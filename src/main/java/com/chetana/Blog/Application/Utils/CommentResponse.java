package com.chetana.Blog.Application.Utils;

import com.chetana.Blog.Application.Entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    List<Comment>  comments;
    String message;
}
