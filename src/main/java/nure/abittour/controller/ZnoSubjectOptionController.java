package nure.abittour.controller;

import nure.abittour.model.ZnoSubjectOption;
import nure.abittour.service.ZnoSubjectOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/znoSubjectOptions")
public class ZnoSubjectOptionController {

    @Autowired
    private ZnoSubjectOptionService znoSubjectOptionService;

    @GetMapping
    public List<ZnoSubjectOption> getAll() {
        return znoSubjectOptionService.getAll();
    }

    @GetMapping("/{id}")
    public ZnoSubjectOption getById(@PathVariable Long id) {
        return znoSubjectOptionService.getById(id);
    }

    @PostMapping
    public ZnoSubjectOption create(@RequestBody ZnoSubjectOption znoSubjectOption) {
        return znoSubjectOptionService.create(znoSubjectOption);
    }

    @PutMapping
    public ZnoSubjectOption update(@RequestBody ZnoSubjectOption znoSubjectOption) {
        return znoSubjectOptionService.update(znoSubjectOption);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        znoSubjectOptionService.delete(id);
    }
}
