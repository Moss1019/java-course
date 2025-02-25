package com.mossonthetree;

import com.mossonthetree.generator.JooqGenerator;

public class Program {
    public static void main(String[] args) {
        String host = "localhost";
        String database = "trade_db";

        var destinationDir = String.format("%s/trade-data-db/src/main/java", System.getProperty("user.dir"));
        JooqGenerator generator = new JooqGenerator(host, database, destinationDir, (Exception ex) -> {
            System.out.println(ex.getMessage());
        });

        generator.generate();
    }
}
