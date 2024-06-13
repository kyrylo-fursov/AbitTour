package nure.abittour.service;

import nure.abittour.dto.SpecialityDTO;
import nure.abittour.dto.SubjectCoefDTO;
import nure.abittour.model.Region;
import nure.abittour.model.enums.Subject;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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

    @PostConstruct
    public void initDb() {
        initializeRegions();
        initializeSpecialities();
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
            // Parse specialties
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
}