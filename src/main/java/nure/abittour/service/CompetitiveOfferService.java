package nure.abittour.service;

import nure.abittour.model.CompetitiveOffer;
import nure.abittour.repository.CompetitiveOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitiveOfferService {

    @Autowired
    private CompetitiveOfferRepository competitiveOfferRepository;

    public List<CompetitiveOffer> getAll() {
        return competitiveOfferRepository.findAll();
    }

    public CompetitiveOffer getById(Long id) {
        return competitiveOfferRepository.findById(id).orElse(null);
    }

    public CompetitiveOffer create(CompetitiveOffer competitiveOffer) {
        return competitiveOfferRepository.save(competitiveOffer);
    }

    public CompetitiveOffer update(CompetitiveOffer competitiveOffer) {
        return competitiveOfferRepository.save(competitiveOffer);
    }

    public void delete(Long id) {
        competitiveOfferRepository.deleteById(id);
    }
}
