package nure.abittour.service;

import nure.abittour.dto.CompetitiveOfferRequest;
import nure.abittour.dto.CompetitiveOfferResponse;
import nure.abittour.dto.ZnoSubjectOptionDTO;
import nure.abittour.mapper.CompetitiveOfferMapper;
import nure.abittour.mapper.ZnoSubjectOptionMapper;
import nure.abittour.model.ZnoSubjectOption;
import nure.abittour.repository.CompetitiveOfferRepository;
import nure.abittour.model.CompetitiveOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompetitiveOfferService {

    @Autowired
    private CompetitiveOfferRepository competitiveOfferRepository;

    @Autowired
    private CompetitiveOfferMapper competitiveOfferMapper;

    @Autowired
    private ZnoSubjectOptionMapper znoSubjectOptionMapper;

    public List<CompetitiveOfferResponse> getAllOffers() {
        return competitiveOfferRepository.findAll().stream()
                .map(competitiveOfferMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public CompetitiveOfferResponse getOfferById(Long id) {
        CompetitiveOffer offer = competitiveOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        return competitiveOfferMapper.toResponseDto(offer);
    }

    @Transactional
    public CompetitiveOfferResponse createOffer(CompetitiveOfferRequest offerRequest) {
        CompetitiveOffer competitiveOffer = competitiveOfferMapper.toEntity(offerRequest);

        Set<ZnoSubjectOption> znoSubjectOptions = new LinkedHashSet<>();
        for (ZnoSubjectOptionDTO znoSubjectOptionDTO : offerRequest.getZnoSubjectOptions()) {
            ZnoSubjectOption znoSubjectOption = znoSubjectOptionMapper.toEntity(znoSubjectOptionDTO);
            znoSubjectOption.setCompetitiveOffer(competitiveOffer);
            znoSubjectOptions.add(znoSubjectOption);
        }

        competitiveOffer.setZnoSubjectOptions(znoSubjectOptions);

        CompetitiveOffer savedOffer = competitiveOfferRepository.save(competitiveOffer);
        return competitiveOfferMapper.toResponseDto(savedOffer);
    }

    public void deleteOffer(Long id) {
        competitiveOfferRepository.deleteById(id);
    }

    @Transactional
    public CompetitiveOfferResponse updateOffer(Long id, CompetitiveOfferRequest offerRequest) {
        CompetitiveOffer existingOffer = competitiveOfferRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        competitiveOfferMapper.updateEntityFromRequest(offerRequest, existingOffer);
        existingOffer.getZnoSubjectOptions().clear();
        existingOffer.getZnoSubjectOptions().addAll(
                offerRequest.getZnoSubjectOptions().stream()
                        .map(znoSubjectOptionMapper::toEntity)
                        .peek(option -> option.setCompetitiveOffer(existingOffer))
                        .collect(Collectors.toSet())
        );

        CompetitiveOffer updatedOffer = competitiveOfferRepository.save(existingOffer);
        return competitiveOfferMapper.toResponseDto(updatedOffer);
    }
}