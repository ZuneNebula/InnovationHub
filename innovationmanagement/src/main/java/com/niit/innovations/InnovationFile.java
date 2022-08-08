package com.niit.innovations;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;

@Getter
@Setter
public class InnovationFile {
    private String fileName;
    private String type;
    private Binary image;
}
