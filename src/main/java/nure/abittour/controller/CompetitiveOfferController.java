package nure.abittour.controller;

import nure.abittour.dto.CompetitiveOfferDTO;
import nure.abittour.service.CompetitiveOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitiveOffers")
public class CompetitiveOfferController {

    @Autowired
    private CompetitiveOfferService competitiveOfferService;

    @GetMapping
    public List<CompetitiveOfferDTO> getAll() {
        return competitiveOfferService.getAll();
    }

    @GetMapping("/{id}")
    public CompetitiveOfferDTO getById(@PathVariable Long id) {
        return competitiveOfferService.getById(id);
    }

    @PostMapping
    public CompetitiveOfferDTO create(@RequestBody CompetitiveOfferDTO competitiveOfferDTO) {
        return competitiveOfferService.create(competitiveOfferDTO);
    }

    @PutMapping
    public CompetitiveOfferDTO update(@RequestBody CompetitiveOfferDTO competitiveOfferDTO) {
        return competitiveOfferService.update(competitiveOfferDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        competitiveOfferService.delete(id);
    }
}
