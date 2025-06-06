package com.mossonthetree;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain()
public class Program implements QuarkusApplication {
    public static void main(String[] args) {
        Quarkus.run(Program.class, args);
    }

    @Override
    public int run(String... args) throws Exception {
        Quarkus.waitForExit();
        return 0;
    }
}
