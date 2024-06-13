package nure.abittour.generators;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Component
public class CompetitiveOfferJsonGenerator {

    private static final Random random = new Random();
    private static final ObjectMapper mapper = new ObjectMapper();

    public void generateJsonFile(String filePath, int numberOfOffers) {
        try {
            ArrayNode offersArray = mapper.createArrayNode();

            for (int i = 0; i < numberOfOffers; i++) {
                ObjectNode offerNode = mapper.createObjectNode();
                offerNode.put("programName", "Program Name " + random.nextInt(1000));
                offerNode.put("offerCode", random.nextLong());
                offerNode.put("enrolmentBase", EnrolmentBase.values()[random.nextInt(EnrolmentBase.values().length)].name());
                offerNode.put("educationalLevel", EducationalLevel.values()[random.nextInt(EducationalLevel.values().length)].name());
                offerNode.put("specialityId", random.nextInt(100) + 1);
                offerNode.put("universityId", 1);
                offerNode.put("faculty", "Faculty " + random.nextInt(10));
                offerNode.put("typeOfOffer", TypeOfOffer.values()[random.nextInt(TypeOfOffer.values().length)].name());
                offerNode.put("formOfEducation", FormOfEducation.values()[random.nextInt(FormOfEducation.values().length)].name());
                offerNode.put("enrolledCourse", EnrolledCourse.values()[random.nextInt(EnrolledCourse.values().length)].name());
                offerNode.put("startOfStudies", LocalDate.of(2023, 9, 1).toString());
                offerNode.put("endOfStudies", LocalDate.of(2024, 6, 30).toString());
                offerNode.put("startOfApplication", LocalDate.of(2023, 5, 1).toString());
                offerNode.put("endOfApplication", LocalDate.of(2023, 6, 30).toString());
                offerNode.put("licenceAmount", random.nextInt(100) + 1);
                offerNode.put("maxVolumeOfTheStateOrder", random.nextInt(50) + 1);
                offerNode.put("priceForYear", random.nextInt(10000) + 1000);
                offerNode.put("totalPrice", (random.nextInt(10000) + 1000) * 4);
                offerNode.put("regionalCoefficient", roundToStep(1.0 + random.nextDouble(), 0.1));
                offerNode.put("domainCoefficient", roundToStep(1.0 + random.nextDouble(), 0.1));


                ArrayNode znoSubjectOptionsArray = mapper.createArrayNode();
                for (int j = 0; j < 4; j++) {
                    ObjectNode subjectOptionNode = mapper.createObjectNode();
                    subjectOptionNode.put("coefficient", roundToStep(random.nextDouble(), 0.1));
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