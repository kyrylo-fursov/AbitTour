package nure.abittour.service;

import nure.abittour.dto.CompetitiveOfferRequest;
import nure.abittour.dto.SpecialityDTO;
import nure.abittour.dto.SubjectCoefDTO;
import nure.abittour.dto.ZnoSubjectOptionDTO;
import nure.abittour.model.Region;
import nure.abittour.model.enums.EducationalLevel;
import nure.abittour.model.enums.EnrolledCourse;
import nure.abittour.model.enums.EnrolmentBase;
import nure.abittour.model.enums.FormOfEducation;
import nure.abittour.model.enums.Subject;
import nure.abittour.model.enums.TypeOfOffer;
import nure.abittour.repository.CompetitiveOfferRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import nure.abittour.repository.RegionRepository;

@Service
public class DatabaseInitializerService {

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private CompetitiveOfferService competitiveOfferService;

    @Autowired
    private CompetitiveOfferRepository competitiveOfferRepository;

    @PostConstruct
    public void initDb() {
        initializeRegions();
        initializeSpecialities();
        initializeCompetitiveOffers();
    }

    private void initializeRegions() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src/main/resources/regions.json"));

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                Region region = new Region();
                region.setId((Long) jsonObject.get("id"));
                region.setName((String) jsonObject.get("name"));

                regionRepository.save(region);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initializeSpecialities() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray specialitiesArray = (JSONArray) parser.parse(new FileReader("src/main/resources/specialties.json"));
            for (Object obj : specialitiesArray) {
                JSONObject jsonObject = (JSONObject) obj;

                SpecialityDTO specialityDTO = new SpecialityDTO();
                specialityDTO.setId((Long) jsonObject.get("id"));
                specialityDTO.setCode((String) jsonObject.get("code"));
                specialityDTO.setName((String) jsonObject.get("name"));
                specialityDTO.setSpecialization((String) jsonObject.get("specialization"));

                List<SubjectCoefDTO> subjectCoefs = new ArrayList<>();
                JSONArray subjectsArray = (JSONArray) parser.parse(new FileReader("src/main/resources/subject_coefs.json"));
                for (Object subjObj : subjectsArray) {
                    JSONObject subjJson = (JSONObject) subjObj;

                    if (specialityDTO.getCode().equals(subjJson.get("code"))) {
                        JSONObject subjects = (JSONObject) subjJson.get("subjects");
                        for (Object key : subjects.keySet()) {
                            String subjectName = (String) key;
                            Double coefficient = (Double) subjects.get(subjectName);

                            Subject subjectEnum = Subject.fromUkrainianName(subjectName);

                            SubjectCoefDTO subjectCoefDTO = new SubjectCoefDTO();
                            subjectCoefDTO.setSubject(subjectEnum.name());
                            subjectCoefDTO.setCoefficient(coefficient);
                            subjectCoefs.add(subjectCoefDTO);
                        }
                    }
                }

                specialityDTO.setSubjectCoefs(subjectCoefs);
                specialityService.createSpeciality(specialityDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeCompetitiveOffers() {
        clearCompetitiveOffers();


        JSONParser parser = new JSONParser();
        try {
            JSONArray offersArray = (JSONArray) parser.parse(new FileReader("src/main/resources/competitive_offers.json"));
            for (Object obj : offersArray) {
                JSONObject jsonObject = (JSONObject) obj;

                CompetitiveOfferRequest offerDto = new CompetitiveOfferRequest();
                offerDto.setProgramName((String) jsonObject.get("programName"));
                offerDto.setOfferCode((Long) jsonObject.get("offerCode"));
                offerDto.setEnrolmentBase(EnrolmentBase.valueOf((String) jsonObject.get("enrolmentBase")));
                offerDto.setEducationalLevel(EducationalLevel.valueOf((String) jsonObject.get("educationalLevel")));
                offerDto.setSpecialityId((Long) jsonObject.get("specialityId"));
                offerDto.setUniversityId((Long) jsonObject.get("universityId"));
                offerDto.setFaculty((String) jsonObject.get("faculty"));
                offerDto.setTypeOfOffer(TypeOfOffer.valueOf((String) jsonObject.get("typeOfOffer")));
                offerDto.setFormOfEducation(FormOfEducation.valueOf((String) jsonObject.get("formOfEducation")));
                offerDto.setEnrolledCourse(EnrolledCourse.valueOf((String) jsonObject.get("enrolledCourse")));
                offerDto.setStartOfStudies(LocalDate.parse((String) jsonObject.get("startOfStudies")));
                offerDto.setEndOfStudies(LocalDate.parse((String) jsonObject.get("endOfStudies")));
                offerDto.setStartOfApplication(LocalDate.parse((String) jsonObject.get("startOfApplication")));
                offerDto.setEndOfApplication(LocalDate.parse((String) jsonObject.get("endOfApplication")));
                offerDto.setLicenceAmount(((Long) jsonObject.get("licenceAmount")).intValue());
                offerDto.setMaxVolumeOfTheStateOrder(((Long) jsonObject.get("maxVolumeOfTheStateOrder")).intValue());
                offerDto.setPriceForYear(((Long) jsonObject.get("priceForYear")).intValue());
                offerDto.setTotalPrice(((Long) jsonObject.get("totalPrice")).intValue());
                offerDto.setRegionalCoefficient(BigDecimal.valueOf((Double) jsonObject.get("regionalCoefficient")));
                offerDto.setDomainCoefficient(BigDecimal.valueOf((Double) jsonObject.get("domainCoefficient")));

                Set<ZnoSubjectOptionDTO> znoSubjectOptions = new HashSet<>();
                JSONArray subjectOptionsArray = (JSONArray) jsonObject.get("znoSubjectOptions");
                for (Object subjOptObj : subjectOptionsArray) {
                    JSONObject subjOptJson = (JSONObject) subjOptObj;

                    ZnoSubjectOptionDTO subjectOption = new ZnoSubjectOptionDTO();
                    subjectOption.setCoefficient((Double) subjOptJson.get("coefficient"));
                    subjectOption.setSubject(Subject.valueOf((String) subjOptJson.get("subject")));
                    znoSubjectOptions.add(subjectOption);
                }
                offerDto.setZnoSubjectOptions(znoSubjectOptions);

                competitiveOfferService.createOffer(offerDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearCompetitiveOffers() {
        competitiveOfferRepository.deleteAll();
    }
}