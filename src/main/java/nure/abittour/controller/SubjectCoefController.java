package nure.abittour.controller;

import nure.abittour.dto.SubjectCoefDto;
import nure.abittour.mapper.SubjectCoefMapper;
import nure.abittour.service.SubjectCoefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subject-coefs")
public class SubjectCoefController {

    @Autowired
    private SubjectCoefService subjectCoefService;

    @Autowired
    private SubjectCoefMapper subjectCoefMapper;

    @GetMapping
    public List<SubjectCoefDto> findAll() {
        return subjectCoefService.findAll()
                .stream()
                .map(subjectCoefMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectCoefDto> findById(@PathVariable Long id) {
        Optional<SubjectCoefDto> subjectCoefDto = subjectCoefService.findById(id).map(subjectCoefMapper::toDto);
        return subjectCoefDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SubjectCoefDto create(@RequestBody SubjectCoefDto subjectCoefDto) {
        return subjectCoefMapper.toDto(subjectCoefService.save(subjectCoefMapper.toEntity(subjectCoefDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectCoefDto> update(@PathVariable Long id, @RequestBody SubjectCoefDto subjectCoefDto) {
        if (!subjectCoefService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        subjectCoefDto.setId(id);
        return ResponseEntity.ok(subjectCoefMapper.toDto(subjectCoefService.save(subjectCoefMapper.toEntity(subjectCoefDto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!subjectCoefService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        subjectCoefService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
