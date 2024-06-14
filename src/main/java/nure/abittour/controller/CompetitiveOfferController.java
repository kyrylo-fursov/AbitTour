package nure.abittour.controller;

import nure.abittour.dto.CompetitiveOfferRequest;
import nure.abittour.service.CompetitiveOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitive-offers")
public class CompetitiveOfferController {
    @Autowired
    private CompetitiveOfferService competitiveOfferService;

    @GetMapping
    public List<CompetitiveOfferRequest> getAllOffers() {
        return competitiveOfferService.getAllOffers();
    }

    @GetMapping("/{id}")
    public CompetitiveOfferRequest getOfferById(@PathVariable Long id) {
        return competitiveOfferService.getOfferById(id);
    }

    @PostMapping
    public CompetitiveOfferRequest createOffer(@RequestBody CompetitiveOfferRequest offerDto) {
        return competitiveOfferService.createOffer(offerDto);
    }

    @PutMapping("/{id}")
    public CompetitiveOfferRequest updateOffer(@PathVariable Long id, @RequestBody CompetitiveOfferRequest offerDto) {
        return competitiveOfferService.updateOffer(id, offerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {
        competitiveOfferService.deleteOffer(id);
    }
}
