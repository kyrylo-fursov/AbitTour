package nure.abittour.controller;

import nure.abittour.model.ChosenSubject;
import nure.abittour.service.ChosenSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chosenSubjects")
public class ChosenSubjectController {

    @Autowired
    private ChosenSubjectService chosenSubjectService;

    @GetMapping
    public List<ChosenSubject> getAll() {
        return chosenSubjectService.getAll();
    }

    @GetMapping("/{id}")
    public ChosenSubject getById(@PathVariable Long id) {
        return chosenSubjectService.getById(id);
    }

    @PostMapping
    public ChosenSubject create(@RequestBody ChosenSubject chosenSubject) {
        return chosenSubjectService.create(chosenSubject);
    }

    @PutMapping
    public ChosenSubject update(@RequestBody ChosenSubject chosenSubject) {
        return chosenSubjectService.update(chosenSubject);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        chosenSubjectService.delete(id);
    }
}
