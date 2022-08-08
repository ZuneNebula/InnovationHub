package com.niit.innovations;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Files {
    private String fileName;
    private String extension;
    private String content;
    private String filetype;

    public Files(String fileName, String extension, String content, String filetype) {
        this.fileName = fileName;
        this.extension = extension;
        this.content = content;
        this.filetype = filetype;
    }
}
