package utilities.elementsReader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ElementResources {

    private static final Logger logger = LogManager.getLogger(ElementResources.class);


    private static ElementResources instance;
    private final List<Path> jsonFiles = new ArrayList<>();
    private Map<String, Map<String, String>> elementData;
    String directory = "src/test/resources/elementJson";

    private ElementResources() {
        try {
            findJsonFiles();
            loadJsonData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ElementResources getInstance() {
        if (instance == null) {
            instance = new ElementResources();
        }
        return instance;
    }

    private void findJsonFiles() throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(directory))) {
            paths.filter(path -> path.toString().endsWith(".json"))
                    .forEach(jsonFiles::add);
            logger.info("JSON files found: {}", jsonFiles.size());
        } catch (IOException e) {
            logger.error("JSON files not found: {}", e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("An unexpected error occurred: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadJsonData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        elementData = new java.util.HashMap<>();
        for (Path jsonFile : jsonFiles) {
            File file = jsonFile.toFile();
            Map<String, Map<String, String>> fileData = objectMapper.readValue(file, new TypeReference<>() {
            });
            elementData.putAll(fileData);
        }
        logger.info("Loaded locators: {}", elementData.size());
    }

    private String getElementType(String fullName) {
        Map<String, String> element = elementData.get(fullName);
        if (element != null) {
            return element.get("type");
        }
        return null;
    }

    private String getElementLocator(String fullName) {
        Map<String, String> element = elementData.get(fullName);
        if (element != null) {
            return element.get("locator");
        }
        return null;
    }

    public By getElementBy(String fullName) {
        String elementLocator = getElementLocator(fullName);
        String locatorType = getElementType(fullName);

        if (elementLocator != null) {
            assert locatorType != null;
            return switch (locatorType.toLowerCase()) {
                case "xpath" -> By.xpath(elementLocator);
                case "id" -> By.id(elementLocator);
                case "name" -> By.name(elementLocator);
                case "css" -> By.cssSelector(elementLocator);
                case "class" -> By.className(elementLocator);
                case "tag" -> By.tagName(elementLocator);
                default -> throw new IllegalArgumentException("Invalid locator type" + locatorType);
            };
        }
        return null;
    }
}
