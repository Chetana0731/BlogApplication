package com.chetana.Blog.Application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private int comment_id;
    private String comment;
    //private PostDto postDto;
    //private UserDto userDto;
}
