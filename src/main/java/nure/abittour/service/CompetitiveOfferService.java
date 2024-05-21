package nure.abittour.service;

import nure.abittour.dto.CompetitiveOfferDTO;
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

    public List<CompetitiveOfferDTO> getAll() {
        return competitiveOfferRepository.findAll().stream()
                .map(competitiveOfferMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CompetitiveOfferDTO getById(Long id) {
        return competitiveOfferRepository.findById(id)
                .map(competitiveOfferMapper::toDTO)
                .orElse(null);
    }

    public CompetitiveOfferDTO create(CompetitiveOfferDTO competitiveOfferDTO) {
        CompetitiveOffer competitiveOffer = competitiveOfferMapper.toEntity(competitiveOfferDTO);
        return competitiveOfferMapper.toDTO(competitiveOfferRepository.save(competitiveOffer));
    }

    public CompetitiveOfferDTO update(CompetitiveOfferDTO competitiveOfferDTO) {
        CompetitiveOffer competitiveOffer = competitiveOfferMapper.toEntity(competitiveOfferDTO);
        return competitiveOfferMapper.toDTO(competitiveOfferRepository.save(competitiveOffer));
    }

    public void delete(Long id) {
        competitiveOfferRepository.deleteById(id);
    }
}
