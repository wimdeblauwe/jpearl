package io.github.wimdeblauwe.jpearl.mavenplugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.NONE)
public class GenerateEntityClassesMojo extends AbstractMojo {

    @Parameter(property = "basePackage", required = true)
    private String basePackage;

    @Parameter(property = "entity", required = true)
    private String entity;

    @Parameter(property = "entityPackage")
    private String entityPackage;

    @Parameter(property = "entityId")
    private String entityId;

    @Parameter(property = "entityRepository")
    private String entityRepository;

    @Parameter(property = "entityRepositoryCustom")
    private String entityRepositoryCustom;

    @Parameter(property = "entityRepositoryImpl")
    private String entityRepositoryImpl;

    @Parameter(property = "entityIdType", defaultValue = "UUID")
    private String entityIdType;

    @Parameter(property = "entityIdTypeImport", defaultValue = "java.util.UUID")
    private String entityIdTypeImport;

    @Parameter(property = "entityRepositoryTest")
    private String entityRepositoryTest;

    @Parameter(property = "dataJpaTestAnnotation")
    private String dataJpaTestAnnotation;

    @Parameter(property = "dataJpaTestAnnotationImport")
    private String dataJpaTestAnnotationImport;

    @Parameter(property = "entityTable")
    private String entityTable;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (entityPackage == null) {
            entityPackage = String.format("%s.%s", basePackage, entity.toLowerCase());
        }
        if (entityId == null) {
            entityId = String.format("%sId", entity);
        }
        if (entityRepository == null) {
            entityRepository = String.format("%sRepository", entity);
        }
        if (entityRepositoryCustom == null) {
            entityRepositoryCustom = String.format("%sRepositoryCustom", entity);
        }
        if (entityRepositoryImpl == null) {
            entityRepositoryImpl = String.format("%sRepositoryImpl", entity);
        }
        if( entityRepositoryTest == null) {
            entityRepositoryTest = String.format("%sRepositoryTest", entity);
        }
        if (entityTable == null) {
            entityTable = entity.toLowerCase();
        }
        if (dataJpaTestAnnotation == null) {
            dataJpaTestAnnotation = "@DataJpaTest";
        }
        if (dataJpaTestAnnotationImport == null) {
            dataJpaTestAnnotationImport = "org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest";
        }

        try {
            generateMainJavaCode();
            generateTestJavaCode();
        } catch (IOException e) {
            getLog().error(e);
        }
    }

    private void generateMainJavaCode() throws IOException {
        Path javaSourceRoot = Paths.get("src/main/java/");
        Path entityPackageDirectory = javaSourceRoot.resolve(entityPackage.replaceAll("\\.", "/"));
        Files.createDirectories(entityPackageDirectory);

        getLog().info(String.format("Generating entity: %s.%s", entityPackage, entity));
        generateFileFromTemplate(entityPackageDirectory, "entity-template.java", entity);

        getLog().info(String.format("Generating entity id: %s.%s (using type: %s)", entityPackage, entityId, entityIdType));
        generateFileFromTemplate(entityPackageDirectory, "entityid-template.java", entityId);

        getLog().info(String.format("Generating entity repository: %s.%s", entityPackage, entityRepository));
        generateFileFromTemplate(entityPackageDirectory, "entity-repository-template.java", entityRepository);

        getLog().info(String.format("Generating entity repository custom: %s.%s", entityPackage, entityRepositoryCustom));
        generateFileFromTemplate(entityPackageDirectory, "entity-repository-custom-template.java", entityRepositoryCustom);

        getLog().info(String.format("Generating entity repository impl: %s.%s", entityPackage, entityRepositoryImpl));
        generateFileFromTemplate(entityPackageDirectory, "entity-repository-impl-template.java", entityRepositoryImpl);
    }

    private void generateTestJavaCode() throws IOException {
        Path javaSourceRoot = Paths.get("src/test/java/");
        Path entityPackageDirectory = javaSourceRoot.resolve(entityPackage.replaceAll("\\.", "/"));
        Files.createDirectories(entityPackageDirectory);

        getLog().info(String.format("Generating entity repository test: %s.%s", entityPackage, entityRepositoryTest));
        generateFileFromTemplate(entityPackageDirectory, "entity-repository-test-template.java", entityRepositoryTest);
    }

    private void generateFileFromTemplate(Path entityPackageDirectory, String templateName, String javaFileName) throws IOException {
        String string = replacePlaceholdersInTemplate(templateName);
        Files.write(entityPackageDirectory.resolve(javaFileName + ".java"), string.getBytes(StandardCharsets.UTF_8));
    }

    private String replacePlaceholdersInTemplate(String templateName) throws IOException {
        String string = getResourceFileAsString(templateName);
        string = string.replaceAll("<PACKAGE>", entityPackage);
        string = string.replaceAll("<ENTITY>", entity);
        string = string.replaceAll("<ENTITYID>", entityId);
        string = string.replaceAll("<ENTITYIDTYPE>", entityIdType);
        string = string.replaceAll("<ENTITYIDTYPEIMPORT>", entityIdTypeImport);
        string = string.replaceAll("<ENTITYTABLE>", entityTable);
        string = string.replaceAll("<DATAJPATESTANNOTATION>", dataJpaTestAnnotation);
        string = string.replaceAll("<DATAJPATESTANNOTATIONIMPORT>", dataJpaTestAnnotationImport);
        return string;
    }

    private String getResourceFileAsString(String fileName) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new RuntimeException("Could not find file on classpath: " + fileName);
            }

            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }
}
