package nure.abittour.service;

import nure.abittour.model.Region;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import nure.abittour.model.Speciality;
import nure.abittour.repository.SpecialityRepository;
import nure.abittour.repository.RegionRepository;

@Service
public class DatabaseInitializerService {

    @Autowired
    private SpecialityRepository specialityRepository;
    @Autowired
    private RegionRepository regionRepository;

    @PostConstruct
    public void initDb() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src/main/resources/specialties.json"));

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                Speciality speciality = new Speciality();
                speciality.setId((Long) jsonObject.get("id") + 1);
                speciality.setCode((String) jsonObject.get("code"));
                speciality.setName((String) jsonObject.get("name"));
                speciality.setSpecialization((String) jsonObject.get("specialization"));

                specialityRepository.save(speciality);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        parser = new JSONParser();
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
}
