package nure.abittour.controller;

import nure.abittour.dto.CompetitiveOfferDto;
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
    public List<CompetitiveOfferDto> getAllOffers() {
        return competitiveOfferService.getAllOffers();
    }

    @GetMapping("/{id}")
    public CompetitiveOfferDto getOfferById(@PathVariable Long id) {
        return competitiveOfferService.getOfferById(id);
    }

    @PostMapping
    public CompetitiveOfferDto createOffer(@RequestBody CompetitiveOfferDto offerDto) {
        System.out.println(offerDto.toString());
        return competitiveOfferService.createOffer(offerDto);
    }

//    @PutMapping("/{id}")
//    public CompetitiveOfferDto updateOffer(@PathVariable Long id, @RequestBody CompetitiveOfferDto offerDto) {
//        return competitiveOfferService.updateOffer(id, offerDto);
//    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {
        competitiveOfferService.deleteOffer(id);
    }
}
