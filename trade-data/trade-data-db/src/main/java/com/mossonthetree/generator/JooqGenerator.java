package com.mossonthetree.generator;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generate;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Strategy;
import org.jooq.meta.jaxb.Target;

import java.util.function.Consumer;

public class JooqGenerator {
    private final String dbServer;

    private final String dbName;

    private final String destinationDir;

    private final Consumer<Exception> errorHandler;

    public JooqGenerator(String dbServer, String dbName, String destinationDir, Consumer<Exception> errorHandler) {
        this.dbServer = dbServer;
        this.dbName = dbName;
        this.destinationDir = destinationDir;
        this.errorHandler = errorHandler;
    }

    public void generate() {
        try {
            GenerationTool.generate(new Configuration()
                    .withJdbc(new Jdbc()
                            .withUrl(String.format("jdbc:postgresql://%s/%s", dbServer, dbName))
                            .withPassword("secret123!")
                            .withUsername("postgres")
                            .withDriver("org.postgresql.Driver"))
                    .withGenerator(new Generator()
                            .withTarget(new Target()
                                    .withDirectory(destinationDir)
                                    .withPackageName("com.mossonthetree.database"))
                            .withDatabase(new Database()
                                    .withIncludes(".*")
                                    .withName("org.jooq.meta.postgres.PostgresDatabase")
                                    .withInputSchema("public"))
                            .withGenerate(new Generate()
                                    .withDaos(false)
                                    .withPojos(false))
                            .withStrategy(new Strategy()
                                    .withName("com.mossonthetree.generator.ClassGeneratorStrategy"))));
        } catch (Exception ex) {
            errorHandler.accept(ex);
        }
    }
}
