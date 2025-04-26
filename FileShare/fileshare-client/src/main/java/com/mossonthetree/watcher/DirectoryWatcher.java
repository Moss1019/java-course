package com.mossonthetree.watcher;

import com.mossonthetree.model.FileInfo;

import java.util.function.Consumer;

public class DirectoryWatcher {
    private final Consumer<FileInfo> fileInfoConsumer;

    public DirectoryWatcher(Consumer<FileInfo> fileInfoConsumer) {
        this.fileInfoConsumer = fileInfoConsumer;
    }

    
}
