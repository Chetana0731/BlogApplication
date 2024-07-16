package com.chetana.Blog.Application.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    /*@ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;*/
}
