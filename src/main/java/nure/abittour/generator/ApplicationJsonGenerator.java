package nure.abittour.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import nure.abittour.model.CompetitiveOffer;
import nure.abittour.model.Student;
import nure.abittour.model.enums.Subject;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class ApplicationJsonGenerator {

    private static final Random random = new Random();
    private static final ObjectMapper mapper = new ObjectMapper();

    public void generateJsonFile(String filePath, List<Student> students, List<CompetitiveOffer> offers) {
        try {
            ArrayNode applicationsArray = mapper.createArrayNode();

            List<Student> shuffledStudents = new ArrayList<>(students);
            Collections.shuffle(shuffledStudents);

            for (CompetitiveOffer offer : offers) {
                Set<Integer> usedStudentIndices = new HashSet<>();

                for (int i = 0; i < 20; i++) {
                    ObjectNode applicationNode = mapper.createObjectNode();

                    int studentIndex = getRandomUnusedStudentIndex(shuffledStudents.size(), usedStudentIndices);
                    Student student = shuffledStudents.get(studentIndex);
                    usedStudentIndices.add(studentIndex);

                    applicationNode.put("studentId", student.getId());
                    applicationNode.put("competitiveOfferId", offer.getId());

                    ArrayNode chosenSubjectsArray = mapper.createArrayNode();
                    double totalScore = 0.0;
                    for (Subject subject : Subject.values()) {
                        if (random.nextDouble() < 0.5) {
                            ObjectNode chosenSubjectNode = mapper.createObjectNode();
                            chosenSubjectNode.put("subject", subject.name());

                            int score = random.nextInt(101) + 100;
                            chosenSubjectNode.put("score", score);
                            chosenSubjectsArray.add(chosenSubjectNode);

                            totalScore += score;
                        }
                    }
                    applicationNode.set("chosenSubjects", chosenSubjectsArray);

                    double totalScoreWithDecimals = 100.0 + random.nextDouble() * 100.0;
                    applicationNode.put("totalScore", roundToTwoDecimals(totalScoreWithDecimals));
                    applicationNode.put("priority", random.nextInt(5) + 1);

                    applicationsArray.add(applicationNode);
                }
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), applicationsArray);
            System.out.println("JSON file with applications generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private int getRandomUnusedStudentIndex(int maxIndex, Set<Integer> usedIndices) {
        int index = random.nextInt(maxIndex);
        while (usedIndices.contains(index)) {
            index = random.nextInt(maxIndex);
        }
        return index;
    }

    public static void main(String[] args) {
        List<Student> students = fetchAllStudents();
        List<CompetitiveOffer> offers = fetchAllCompetitiveOffers();

        ApplicationJsonGenerator generator = new ApplicationJsonGenerator();
        generator.generateJsonFile("src/main/resources/applications.json", students, offers);
    }

    private static List<Student> fetchAllStudents() {
        return readStudentsFromFile("src/main/resources/students.json");
    }

    private static List<CompetitiveOffer> fetchAllCompetitiveOffers() {
        return readOffersFromFile("src/main/resources/competitive_offers.json");
    }

    private static List<Student> readStudentsFromFile(String filePath) {
        List<Student> students = new ArrayList<>();
        try {
            ArrayNode studentsArray = (ArrayNode) mapper.readTree(new File(filePath));
            for (Object obj : studentsArray) {
                ObjectNode studentNode = (ObjectNode) obj;
                Student student = new Student();
                student.setId(studentNode.get("id").asLong());
                student.setName(studentNode.get("name").asText());
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    private static List<CompetitiveOffer> readOffersFromFile(String filePath) {
        List<CompetitiveOffer> offers = new ArrayList<>();
        try {
            ArrayNode offersArray = (ArrayNode) mapper.readTree(new File(filePath));
            for (Object obj : offersArray) {
                ObjectNode offerNode = (ObjectNode) obj;
                CompetitiveOffer offer = new CompetitiveOffer();
                offer.setId(offerNode.get("id").asLong());
                offer.setProgramName(offerNode.get("programName").asText());
                offers.add(offer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offers;
    }
}
