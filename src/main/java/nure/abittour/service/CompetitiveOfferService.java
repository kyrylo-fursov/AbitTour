package nure.abittour.service;

import nure.abittour.dto.CompetitiveOfferDto;
import nure.abittour.mapper.CompetitiveOfferMapper;
import nure.abittour.repository.CompetitiveOfferRepository;
import nure.abittour.model.CompetitiveOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitiveOfferService {

    @Autowired
    private CompetitiveOfferRepository competitiveOfferRepository;

    @Autowired
    private CompetitiveOfferMapper competitiveOfferMapper;

    public List<CompetitiveOfferDto> getAllOffers() {
        List<CompetitiveOffer> offers = competitiveOfferRepository.findAll();
        return offers.stream()
                .map(competitiveOfferMapper::toDto)
                .collect(Collectors.toList());
    }

    public CompetitiveOfferDto getOfferById(Long id) {
        CompetitiveOffer offer = competitiveOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        return competitiveOfferMapper.toDto(offer);
    }

    public CompetitiveOfferDto createOffer(CompetitiveOfferDto offerDto) {
        System.out.println(offerDto.getZnoSubjectOptions());
        CompetitiveOffer offer = competitiveOfferMapper.toEntity(offerDto);
        System.out.println(offer.toString());
        CompetitiveOffer savedOffer = competitiveOfferRepository.save(offer);
        return competitiveOfferMapper.toDto(savedOffer);
    }

    public void deleteOffer(Long id) {
        competitiveOfferRepository.deleteById(id);
    }

    public CompetitiveOfferDto updateOffer(Long id, CompetitiveOfferDto offerDto) {
        CompetitiveOffer existingOffer = competitiveOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        return competitiveOfferMapper.toDto(competitiveOfferRepository.save(existingOffer));
    }
}
