package com.niit.innovations;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Comment {
    private String commentator_name;
    private String comment_data;
    private Date dateOfComment;
}
