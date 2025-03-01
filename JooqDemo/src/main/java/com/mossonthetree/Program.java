package com.mossonthetree;

/*
config
    new jdbc
        driver
        username
        password
        url
    new Generator
        target
            directory
            package
        database
            includes
            name
            schema
        generate
            daos
            pojos
        strat
            name
 */

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generate;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Strategy;
import org.jooq.meta.jaxb.Target;

public class Program {
    public static void main(String[] args) {

        Jdbc jdbc = new Jdbc();
        jdbc
                .withDriver("org.postgresql.Driver")
                .withUser("postgres")
                .withPassword("secret123!")
                .withUrl("jdbc:postgresql://localhost:5432/trade_db");

        String directory = String.format("%s/src/main/java/", System.getProperty("user.dir"));
        Generator generator = new Generator();
        generator
                .withTarget(new Target()
                        .withPackageName("com.mossonthetree.database")
                        .withDirectory(directory))
                .withDatabase(new Database()
                        .withIncludes(".*")
                        .withName("org.jooq.meta.postgres.PostgresDatabase")
                        .withInputSchema("public"))
                .withGenerate(new Generate()
                        .withDaos(false)
                        .withPojos(false))
                .withStrategy(new Strategy()
                        .withName("com.mossonthetree.NamingStrategy"));


        Configuration config = new Configuration();
        config
                .withJdbc(jdbc)
                .withGenerator(generator);
        try {
            GenerationTool.generate(config);
        } catch (Exception ignored) {}

    }
}
