package com.mossonthetree.model;

import java.time.LocalDateTime;

public class FileInfo {
    public String name;

    public long size;

    public LocalDateTime lastModified;

    public FileInfo() {
    }

    public FileInfo(String name, long size, LocalDateTime lastModified) {
        this.name = name;
        this.size = size;
        this.lastModified = lastModified;
    }
}
