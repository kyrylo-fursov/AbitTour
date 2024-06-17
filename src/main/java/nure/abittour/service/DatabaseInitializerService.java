package nure.abittour.service;

import nure.abittour.dto.CompetitiveOfferRequest;
import nure.abittour.dto.RegistrationRequest;
import nure.abittour.dto.SpecialityDTO;
import nure.abittour.dto.SubjectCoefDTO;
import nure.abittour.dto.ZnoSubjectOptionDTO;
import nure.abittour.model.Region;
import nure.abittour.model.University;
import nure.abittour.model.enums.EducationalLevel;
import nure.abittour.model.enums.EnrolledCourse;
import nure.abittour.model.enums.EnrolmentBase;
import nure.abittour.model.enums.FormOfEducation;
import nure.abittour.model.enums.Subject;
import nure.abittour.model.enums.TypeOfOffer;
import nure.abittour.repository.CompetitiveOfferRepository;
import nure.abittour.repository.UniversityRepository;
import nure.abittour.repository.UserRepository;
import nure.abittour.repository.ZnoSubjectOptionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class DatabaseInitializerService {

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ZnoSubjectOptionRepository znoSubjectOptionRepository;

    @Autowired
    private CompetitiveOfferService competitiveOfferService;

    @Autowired
    private CompetitiveOfferRepository competitiveOfferRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initDb() {
        resetCompetitiveOffers();
        resetUniversityTable();

        initializeRegions();
        initializeUniversities();
        initializeSpecialities();
        initializeCompetitiveOffers();
        initializeUser();
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

    private void initializeUser() {
        String email = "testuser@gmail.com";
        String password = "password";
        if (userRepository.findByEmail(email).isEmpty()) {
            authenticationService.register(new RegistrationRequest(email, password));
        }
    }

    private void initializeUniversities() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src/main/resources/universities.json"));

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                University university = new University();
                university.setName((String) jsonObject.get("Назва закладу освіти"));
                university.setCode(String.valueOf(jsonObject.get("Код")));
                university.setWebsiteUrl((String) jsonObject.get("Веб-сайт"));

                String regionName = (String) jsonObject.get("Регіон (юридична)");
                Region region = regionRepository.findByName(normalizeRegionName(regionName));
                if (region != null) {
                    university.setRegion(region);
                }

                universityRepository.save(university);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void resetUniversityTable() {
        jdbcTemplate.execute("DELETE FROM university");
        jdbcTemplate.execute("ALTER TABLE university AUTO_INCREMENT = 1");
    }

    private String normalizeRegionName(String name) {
        switch (name) {
            case "м. Київ":
                return "Київ";
            case "м. Севастополь":
                return "Севастополь";
            case "Вінницька обл.":
            case "Вінницька область":
                return "Вінницька область";
            case "Волинська обл.":
            case "Волинська область":
                return "Волинська область";
            case "Дніпропетровська обл.":
            case "Дніпропетровська область":
                return "Дніпропетровська область";
            case "Донецька обл.":
            case "Донецька область":
                return "Донецька область";
            case "Житомирська обл.":
            case "Житомирська область":
                return "Житомирська область";
            case "Закарпатська обл.":
            case "Закарпатська область":
                return "Закарпатська область";
            case "Запорізька обл.":
            case "Запорізька область":
                return "Запорізька область";
            case "Івано-Франківська обл.":
            case "Івано-Франківська область":
                return "Івано-Франківська область";
            case "Київська обл.":
            case "Київська область":
                return "Київська область";
            case "Кіровоградська обл.":
            case "Кіровоградська область":
                return "Кіровоградська область";
            case "Автономна Республіка Крим":
            case "АР Крим":
                return "Автономна Республіка Крим";
            case "Львівська обл.":
            case "Львівська область":
                return "Львівська область";
            case "Миколаївська обл.":
            case "Миколаївська область":
                return "Миколаївська область";
            case "Одеська обл.":
            case "Одеська область":
                return "Одеська область";
            case "Полтавська обл.":
            case "Полтавська область":
                return "Полтавська область";
            case "Рівненська обл.":
            case "Рівненська область":
                return "Рівненська область";
            case "Сумська обл.":
            case "Сумська область":
                return "Сумська область";
            case "Тернопільська обл.":
            case "Тернопільська область":
                return "Тернопільська область";
            case "Харківська обл.":
            case "Харківська область":
                return "Харківська область";
            case "Херсонська обл.":
            case "Херсонська область":
                return "Херсонська область";
            case "Хмельницька обл.":
            case "Хмельницька область":
                return "Хмельницька область";
            case "Черкаська обл.":
            case "Черкаська область":
                return "Черкаська область";
            case "Чернігівська обл.":
            case "Чернігівська область":
                return "Чернігівська область";
            case "Чернівецька обл.":
            case "Чернівецька область":
                return "Чернівецька область";
            case "Луганська обл.":
            case "Луганська область":
                return "Луганська область";
            default:
                return name;
        }
    }

    public void initializeSpecialities() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray specialitiesArray = (JSONArray) parser.parse(new FileReader("src/main/resources/specialties.json"));

            JSONArray subjectsArray = (JSONArray) parser.parse(new FileReader("src/main/resources/subject_coefs.json"));

            for (Object obj : specialitiesArray) {
                JSONObject jsonObject = (JSONObject) obj;

                String code = (String) jsonObject.get("code");

                if (specialityService.existsByCode(code)) {
                    System.out.println("Speciality with code " + code + " already exists. Skipping.");
                    continue;
                }

                SpecialityDTO specialityDTO = new SpecialityDTO();
                specialityDTO.setId((Long) jsonObject.get("id"));
                specialityDTO.setCode(code);
                specialityDTO.setName((String) jsonObject.get("name"));
                specialityDTO.setSpecialization((String) jsonObject.get("specialization"));
                specialityDTO.setIsInDemand(false);

                List<SubjectCoefDTO> subjectCoefs = new ArrayList<>();

                for (Object subjObj : subjectsArray) {
                    JSONObject subjJson = (JSONObject) subjObj;

                    if (code.equals(subjJson.get("code"))) {
                        JSONObject subjects = (JSONObject) subjJson.get("subjects");
                        for (Object key : subjects.keySet()) {
                            String subjectName = (String) key;
                            Double coefficient = (Double) subjects.get(subjectName);

                            Subject subjectEnum = Subject.fromUkrainianName(subjectName);

                            SubjectCoefDTO subjectCoefDTO = new SubjectCoefDTO();
                            subjectCoefDTO.setSubject(subjectEnum.name());
                            subjectCoefDTO.setCoefficient(BigDecimal.valueOf(coefficient));
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

        JSONParser parser = new JSONParser();
        try {
            JSONArray offersArray = (JSONArray) parser.parse(new FileReader("src/main/resources/competitive_offers.json"));
            for (Object obj : offersArray) {
                JSONObject jsonObject = (JSONObject) obj;

                CompetitiveOfferRequest offerDto = new CompetitiveOfferRequest();
                offerDto.setProgramName((String) jsonObject.get("programName"));
                offerDto.setOfferCode((String) jsonObject.get("offerCode"));
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

                Set<ZnoSubjectOptionDTO> znoSubjectOptions = new HashSet<>();
                JSONArray subjectOptionsArray = (JSONArray) jsonObject.get("znoSubjectOptions");
                for (Object subjOptObj : subjectOptionsArray) {
                    JSONObject subjOptJson = (JSONObject) subjOptObj;

                    ZnoSubjectOptionDTO subjectOption = new ZnoSubjectOptionDTO();
                    subjectOption.setCoefficient(BigDecimal.valueOf((Double) subjOptJson.get("coefficient")));
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

    @Transactional
    public void resetCompetitiveOffers() {
        znoSubjectOptionRepository.deleteAll();
        competitiveOfferRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE zno_subject_option AUTO_INCREMENT = 1");
        jdbcTemplate.execute("ALTER TABLE competitive_offer AUTO_INCREMENT = 1");
    }
}