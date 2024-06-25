package nure.abittour.controller;

import nure.abittour.dto.CompetitiveOfferFilterDTO;
import nure.abittour.dto.CompetitiveOfferRequest;
import nure.abittour.dto.CompetitiveOfferResponse;
import nure.abittour.service.CompetitiveOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/competitive-offers")
public class CompetitiveOfferController {
    @Autowired
    private CompetitiveOfferService competitiveOfferService;

    @GetMapping
    public List<CompetitiveOfferResponse> getAllOffers() {
        return competitiveOfferService.getAllOffers();
    }

    @GetMapping("/{id}")
    public CompetitiveOfferResponse getOfferById(@PathVariable Long id) {
        return competitiveOfferService.getOfferById(id);
    }

    @PostMapping("/filter")
    public Page<CompetitiveOfferResponse> getFilteredOffers(@RequestBody CompetitiveOfferFilterDTO filter, Pageable pageable) {
        return competitiveOfferService.getFilteredOffers(filter, pageable);
    }

    @PostMapping
    public ResponseEntity<CompetitiveOfferResponse> createOffer(@RequestBody CompetitiveOfferRequest offerRequest) {
        CompetitiveOfferResponse createdOffer = competitiveOfferService.createOffer(offerRequest);
        return ResponseEntity.ok(createdOffer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitiveOfferResponse> updateOffer(@PathVariable Long id, @RequestBody CompetitiveOfferRequest offerRequest) {
        CompetitiveOfferResponse updatedOffer = competitiveOfferService.updateOffer(id, offerRequest);
        return ResponseEntity.ok(updatedOffer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        competitiveOfferService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
}
