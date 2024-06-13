package nure.abittour.service;

import nure.abittour.dto.CompetitiveOfferDto;
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
public class CompetitiveOfferService {

    @Autowired
    private CompetitiveOfferRepository competitiveOfferRepository;

    @Autowired
    private CompetitiveOfferMapper competitiveOfferMapper;

    @Autowired
    private ZnoSubjectOptionMapper znoSubjectOptionMapper;

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

    @Transactional
    public CompetitiveOfferDto createOffer(CompetitiveOfferDto offerDto) {
        CompetitiveOffer competitiveOffer = competitiveOfferMapper.toEntity(offerDto);

        Set<ZnoSubjectOption> znoSubjectOptions = new LinkedHashSet<>();
        for (ZnoSubjectOptionDTO znoSubjectOptionDTO : offerDto.getZnoSubjectOptions()) {
            ZnoSubjectOption znoSubjectOption = znoSubjectOptionMapper.toEntity(znoSubjectOptionDTO);
            znoSubjectOption.setCompetitiveOffer(competitiveOffer);
            znoSubjectOptions.add(znoSubjectOption);
        }

        competitiveOffer.setZnoSubjectOptions(znoSubjectOptions);

        CompetitiveOffer savedOffer = competitiveOfferRepository.save(competitiveOffer);
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
