package com.makersacademy.acebook.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    private Post() {}

    public Post(String content) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }


}
 //add a getter method for content
// create an instance of post
// render it onto the template
//create new object java and render it
