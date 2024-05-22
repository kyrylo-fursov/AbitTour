package nure.abittour.controller;

import nure.abittour.dto.CompetitiveOfferRequest;
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
    public List<CompetitiveOfferRequest> getAll() {
        return competitiveOfferService.getAll();
    }

    @GetMapping("/{id}")
    public CompetitiveOfferRequest getById(@PathVariable Long id) {
        return competitiveOfferService.getById(id);
    }

    @PostMapping
    public CompetitiveOfferRequest create(@RequestBody CompetitiveOfferRequest competitiveOfferDTO) {
        return competitiveOfferService.create(competitiveOfferDTO);
    }

    @PutMapping
    public CompetitiveOfferRequest update(@RequestBody CompetitiveOfferRequest competitiveOfferDTO) {
        return competitiveOfferService.update(competitiveOfferDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        competitiveOfferService.delete(id);
    }
}
