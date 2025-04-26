package com.mossonthetree;

import com.mossonthetree.model.FileInfo;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.nio.file.StandardWatchEventKinds.*;

public class Program {
    private static final List<FileInfo> FILE_INFO = new ArrayList<>();

    private static final Thread INPUT_WORKER;

    private static final long TIMEOUT = 5L;

    private static boolean isRunning;

    public static void main(String[] args) {
        var watchedDir = Paths.get(System.getProperty("user.dir"));

        try(var watchService = FileSystems.getDefault().newWatchService()) {
            watchedDir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

            isRunning = true;
            INPUT_WORKER.start();
            while (isRunning) {
                System.out.println("Checking for changes");
                WatchKey key = null;
                try {
                    key = watchService.poll(TIMEOUT, TimeUnit.SECONDS);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    continue;
                }
                if(key == null) {
                    continue;
                }
                System.out.println("Handling events");
                for(var event: key.pollEvents()) {
                    var kind = event.kind();
                    var fileName = event.context();
                    System.out.println(kind + " " + fileName);
                }
            }
        } catch (Exception ex) {
            System.out.println("HUH, What happened?");
        } finally {
            try {
                INPUT_WORKER.join();
            } catch (Exception ignored) {}
        }
    }

    private static String getInput() {
        try {
            var buffer = new byte[1024];
            var read = System.in.read(buffer);
            return new String(buffer, 0, read - 1);
        } catch (Exception ignored) {
            return "";
        }
    }

    static {
        INPUT_WORKER = new Thread(() -> {
            while(isRunning) {
                var input = getInput();
                switch (input) {
                    case "-q" -> isRunning = false;
                }
            }
        });
    }
}
