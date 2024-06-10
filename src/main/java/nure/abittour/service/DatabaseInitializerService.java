package nure.abittour.service;

import nure.abittour.model.Region;
import nure.abittour.model.SubjectCoef;
import nure.abittour.model.enums.Subject;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.io.FileReader;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import nure.abittour.model.Speciality;
import nure.abittour.repository.SpecialityRepository;
import nure.abittour.repository.RegionRepository;
import nure.abittour.repository.SubjectCoefRepository;

@Service
public class DatabaseInitializerService {

    @Autowired
    private SpecialityRepository specialityRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private SubjectCoefRepository subjectCoefRepository;

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

//        parser = new JSONParser();
//        try {
//            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src/main/resources/subject_coefs.json"));
//
//            for (Object obj : jsonArray) {
//                JSONObject jsonObject = (JSONObject) obj;
//
//                // Найти специальность по коду из JSON-файла
//                String code = (String) jsonObject.get("code");
//                Speciality speciality = specialityRepository.findByCode(code);
//
//                // Если специальность найдена, сохранить коэффициенты предметов
//                if (speciality != null) {
//                    JSONObject subjects = (JSONObject) jsonObject.get("subjects");
//                    for (Object subjectKey : subjects.keySet()) {
//                        String subjectName = (String) subjectKey;
//                        Double coefficient = (Double) subjects.get(subjectName);
//
//                        // Создать объект SubjectCoef и установить связь с найденной специальностью
//                        SubjectCoef subjectCoef = new SubjectCoef();
//                        subjectCoef.setSpeciality(speciality);
//                        subjectCoef.setSubject(Subject.valueOf(subjectName));
//                        subjectCoef.setCoefficient(coefficient);
//
//                        // Сохранить объект в базе данных
//                        subjectCoefRepository.save(subjectCoef);
//                    }
//                } else {
//                    System.out.println("Специальность с кодом " + code + " не найдена.");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }

}
