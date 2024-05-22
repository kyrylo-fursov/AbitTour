package nure.abittour.service;

import nure.abittour.dto.CompetitiveOfferRequest;
import nure.abittour.mapper.CompetitiveOfferMapper;
import nure.abittour.model.CompetitiveOffer;
import nure.abittour.repository.CompetitiveOfferRepository;
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

    public List<CompetitiveOfferRequest> getAll() {
        List<CompetitiveOffer> competitiveOffers = competitiveOfferRepository.findAll();
        return competitiveOffers.stream()
                .map(competitiveOfferMapper::competitiveOfferToDTO)
                .collect(Collectors.toList());
    }

    public CompetitiveOfferRequest getById(Long id) {
        CompetitiveOffer competitiveOffer = competitiveOfferRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Competitive offer with id " + id + " not found"));
        return competitiveOfferMapper.competitiveOfferToDTO(competitiveOffer);
    }

    public CompetitiveOfferRequest create(CompetitiveOfferRequest competitiveOfferDTO) {
        CompetitiveOffer competitiveOffer = competitiveOfferMapper.dtoToCompetitiveOffer(competitiveOfferDTO);
        CompetitiveOffer savedCompetitiveOffer = competitiveOfferRepository.save(competitiveOffer);
        return competitiveOfferMapper.competitiveOfferToDTO(savedCompetitiveOffer);
    }

    public CompetitiveOfferRequest update(CompetitiveOfferRequest competitiveOfferDTO) {
        CompetitiveOffer competitiveOffer = competitiveOfferRepository.findById(competitiveOfferDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Competitive offer with id " + competitiveOfferDTO.getId() + " not found"));
        competitiveOfferMapper.updateCompetitiveOfferFromDTO(competitiveOfferDTO, competitiveOffer);
        CompetitiveOffer updatedCompetitiveOffer = competitiveOfferRepository.save(competitiveOffer);
        return competitiveOfferMapper.competitiveOfferToDTO(updatedCompetitiveOffer);
    }

    public void delete(Long id) {
        competitiveOfferRepository.deleteById(id);
    }
}
