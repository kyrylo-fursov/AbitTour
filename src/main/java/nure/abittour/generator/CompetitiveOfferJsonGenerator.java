package nure.abittour.generator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import nure.abittour.model.enums.EducationalLevel;
import nure.abittour.model.enums.EnrolledCourse;
import nure.abittour.model.enums.EnrolmentBase;
import nure.abittour.model.enums.FormOfEducation;
import nure.abittour.model.enums.Subject;
import nure.abittour.model.enums.TypeOfOffer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDate;
import java.util.Random;

@Component
public class CompetitiveOfferJsonGenerator {

    private static final Random random = new Random();

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String[] PROGRAM_NAMES = {
            "Програмування", "Економіка", "Правознавство", "Філологія", "Інформаційні технології",
            "Міжнародні відносини", "Журналістика", "Медицина", "Біологія", "Інженерія",
            "Психологія", "Соціологія", "Фізика", "Математика", "Хімія"
    };

    private static final String[] FACULTY_NAMES = {
            "Факультет Комп'ютерних Наук", "Економічний Факультет", "Юридичний Факультет",
            "Філологічний Факультет", "Факультет Інформаційних Технологій", "Факультет Міжнародних Відносин",
            "Факультет Журналістики", "Медичний Факультет", "Біологічний Факультет",
            "Інженерний Факультет", "Факультет Психології", "Соціологічний Факультет",
            "Фізичний Факультет", "Математичний Факультет", "Хімічний Факультет"
    };

    public void generateJsonFile(String filePath, int numberOfOffers) {
        try {
            ArrayNode offersArray = mapper.createArrayNode();

            for (int i = 0; i < numberOfOffers; i++) {
                ObjectNode offerNode = mapper.createObjectNode();
                offerNode.put("programName", PROGRAM_NAMES[random.nextInt(PROGRAM_NAMES.length)]);
                offerNode.put("offerCode", String.format("%08d", Math.abs(random.nextLong() % 100000000)));
                offerNode.put("enrolmentBase", random.nextDouble() < 0.9 ? EnrolmentBase.COMPLETE_SECONDARY_EDUCATION.name() : EnrolmentBase.values()[random.nextInt(EnrolmentBase.values().length)].name());
                offerNode.put("educationalLevel", random.nextDouble() < 0.9 ? EducationalLevel.BACHELOR.name() : EducationalLevel.values()[random.nextInt(EducationalLevel.values().length)].name());
                offerNode.put("specialityId", random.nextDouble() < 0.5 ? 67 : random.nextInt(100) + 1);
                offerNode.put("universityId", random.nextDouble() < 0.5 ? 508 : random.nextInt(499) + 1); // 50% bias towards id 500
                offerNode.put("faculty", FACULTY_NAMES[random.nextInt(FACULTY_NAMES.length)]);
                offerNode.put("typeOfOffer", TypeOfOffer.OPEN.name());
                offerNode.put("formOfEducation", FormOfEducation.FULL_TIME.name());
                offerNode.put("enrolledCourse", EnrolledCourse.COURSE_1.name());
                offerNode.put("startOfStudies", LocalDate.of(2023, 9, 1).toString());
                offerNode.put("endOfStudies", LocalDate.of(2024, 6, 30).toString());
                offerNode.put("startOfApplication", LocalDate.of(2023, 5, 1).toString());
                offerNode.put("endOfApplication", LocalDate.of(2023, 6, 30).toString());
                offerNode.put("licenceAmount", random.nextInt(100) + 1);
                offerNode.put("maxVolumeOfTheStateOrder", random.nextInt(50) + 1);
                offerNode.put("priceForYear", random.nextInt(10000) + 1000);
                offerNode.put("totalPrice", (random.nextInt(10000) + 1000) * 4);
                offerNode.put("regionalCoefficient", 1.0);

                ArrayNode znoSubjectOptionsArray = mapper.createArrayNode();
                double totalCoefficient = 0.0;
                for (int j = 0; j < 4; j++) {
                    double remainingCoefficient = 1.4 - totalCoefficient;
                    double coefficient = roundToStep(random.nextDouble() * Math.min(remainingCoefficient, 0.4), 0.1);
                    if (j == 3 && totalCoefficient + coefficient < 1.0) {
                        coefficient = 1.0 - totalCoefficient;
                    }
                    totalCoefficient += coefficient;

                    ObjectNode subjectOptionNode = mapper.createObjectNode();
                    subjectOptionNode.put("coefficient", coefficient);
                    subjectOptionNode.put("subject", Subject.values()[random.nextInt(Subject.values().length)].name());
                    znoSubjectOptionsArray.add(subjectOptionNode);
                }
                offerNode.set("znoSubjectOptions", znoSubjectOptionsArray);

                offersArray.add(offerNode);
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), offersArray);
            System.out.println("JSON file generated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double roundToStep(double value, double step) {
        return Math.round(value / step) * step;
    }

    public static void main(String[] args) {
        CompetitiveOfferJsonGenerator generator = new CompetitiveOfferJsonGenerator();
        generator.generateJsonFile("src/main/resources/competitive_offers.json", 200);
    }
}

