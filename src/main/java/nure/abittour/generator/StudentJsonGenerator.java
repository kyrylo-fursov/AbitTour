package nure.abittour.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Random;

@Component
public class StudentJsonGenerator {

    private static final Random random = new Random();
    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String[] NAMES = {
            "Олександр", "Марія", "Андрій", "Світлана", "Дмитро", "Олена", "Олексій", "Ольга",
            "Іван", "Наталія", "Максим", "Анна", "Сергій", "Катерина", "Володимир", "Ірина"
    };

    public void generateJsonFile(String filePath, int numberOfStudents) {
        try {
            ArrayNode studentsArray = mapper.createArrayNode();

            for (int i = 0; i < numberOfStudents; i++) {
                ObjectNode studentNode = mapper.createObjectNode();
                studentNode.put("id", i + 1); // Генерация ID
                studentNode.put("name", NAMES[random.nextInt(NAMES.length)] + " " + (char) ('А' + random.nextInt(26)) + ".");
                studentsArray.add(studentNode);
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), studentsArray);
            System.out.println("JSON file with students generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentJsonGenerator generator = new StudentJsonGenerator();
        generator.generateJsonFile("src/main/resources/students.json", 100);
    }
}
