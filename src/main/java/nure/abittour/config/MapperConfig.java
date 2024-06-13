package nure.abittour.config;

import nure.abittour.mapper.CompetitiveOfferMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public CompetitiveOfferMapper competitiveOfferMapper() {
        return Mappers.getMapper(CompetitiveOfferMapper.class);
    }
}
