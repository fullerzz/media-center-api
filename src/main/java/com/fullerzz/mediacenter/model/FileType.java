package com.fullerzz.mediacenter.model;

public class FileType implements Media {

    private String filename;
    private long fileSize;

    public FileType(String filename, long fileSize) {
        this.filename = filename;
        this.fileSize = fileSize;
    }

    public String getFilename() {
        return this.filename;
    }

    public long getFileSize() {
        return this.fileSize;
    }

}
