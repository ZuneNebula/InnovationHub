package com.niit.proposal.model;

public class AttachedFile {
    private String fileName;
    private String extension;
    private String content;
    private String fileType; //logo,summary etc

    public AttachedFile() {
    }

    public AttachedFile(String fileName, String extension, String content, String fileType) {
        this.fileName = fileName;
        this.extension = extension;
        this.content = content;
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
