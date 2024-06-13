package nure.abittour.controller;

import nure.abittour.dto.SpecialityDTO;
import nure.abittour.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/specialities")
public class SpecialityController {

    @Autowired
    private SpecialityService specialityService;

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityDTO> getSpecialityById(@PathVariable Long id) {
        SpecialityDTO specialityDTO = specialityService.getSpecialityWithCoefs(id);
        return ResponseEntity.ok(specialityDTO);
    }

    @GetMapping
    public ResponseEntity<List<SpecialityDTO>> getAllSpecialities() {
        List<SpecialityDTO> specialities = specialityService.getAllSpecialities();
        return ResponseEntity.ok(specialities);
    }

    @PostMapping
    public ResponseEntity<SpecialityDTO> createSpeciality(@RequestBody SpecialityDTO specialityDTO) {
        SpecialityDTO createdSpeciality = specialityService.createSpeciality(specialityDTO);
        return ResponseEntity.ok(createdSpeciality);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialityDTO> updateSpeciality(@PathVariable Long id, @RequestBody SpecialityDTO specialityDTO) {
        SpecialityDTO updatedSpeciality = specialityService.updateSpeciality(id, specialityDTO);
        return ResponseEntity.ok(updatedSpeciality);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeciality(@PathVariable Long id) {
        specialityService.deleteSpeciality(id);
        return ResponseEntity.noContent().build();
    }
}