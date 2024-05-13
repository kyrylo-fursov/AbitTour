package nure.abittour.controller;

import nure.abittour.model.CompetitiveOffer;
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
    public List<CompetitiveOffer> getAll() {
        return competitiveOfferService.getAll();
    }

    @GetMapping("/{id}")
    public CompetitiveOffer getById(@PathVariable Long id) {
        return competitiveOfferService.getById(id);
    }

    @PostMapping
    public CompetitiveOffer create(@RequestBody CompetitiveOffer competitiveOffer) {
        return competitiveOfferService.create(competitiveOffer);
    }

    @PutMapping
    public CompetitiveOffer update(@RequestBody CompetitiveOffer competitiveOffer) {
        return competitiveOfferService.update(competitiveOffer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        competitiveOfferService.delete(id);
    }
}
