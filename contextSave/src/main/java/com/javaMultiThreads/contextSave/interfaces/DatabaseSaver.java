package com.javaMultiThreads.contextSave.interfaces;

import java.util.concurrent.CompletableFuture;

public interface DatabaseSaver {
    CompletableFuture<Void> save(String data);
}