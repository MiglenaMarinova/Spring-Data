package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ExportOffersDto;
import softuni.exam.models.dto.OffersRootSeedDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    public static final String OFFERS_FILE_PATH = "src/main/resources/files/xml/offers.xml";

    private final OfferRepository offerRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final AgentRepository agentRepository;
    private final ApartmentRepository apartmentRepository;

    public OfferServiceImpl(OfferRepository offerRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, AgentRepository agentRepository, ApartmentRepository apartmentRepository) {
        this.offerRepository = offerRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;
    }


    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(OFFERS_FILE_PATH));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        if (offerRepository.count() == 0){

            OffersRootSeedDto offersRootSeedDto = xmlParser
                    .fromFile(OFFERS_FILE_PATH, OffersRootSeedDto.class);

            offersRootSeedDto.getOffers().stream()
                    .filter(offerSeedDto -> {
                        boolean isValid = validationUtil.isValid(offerSeedDto);
                        Agent agent = agentRepository.findAllByFirstName(offerSeedDto.getAgent().getName()).orElse(null);

                        if (agent == null){
                            isValid = false;
                        }

                        if (isValid){
                            sb.append(String.format("Successfully imported offer %.2f",
                                    offerSeedDto.getPrice())).append(System.lineSeparator());
                        }else{
                            sb.append("Invalid offer").append(System.lineSeparator());                    }

                        return isValid;

                    }).map(offerSeedDto -> {
                        Offer offer = modelMapper.map(offerSeedDto, Offer.class);
                        Agent agent = agentRepository.findAllByFirstName(offerSeedDto.getAgent().getName()).orElse(null);
                        Apartment apartment = apartmentRepository.findById(offerSeedDto.getApartment().getId()).orElse(null);

                        if (agent != null){
                            offer.setAgent(agent);
                        }
                        if (apartment != null){
                            offer.setApartment(apartment);
                        }
                        return offer;
                    }).forEach(offerRepository::save);
        }

        return sb.toString().trim();
    }

    @Override
    public String exportOffers() {
        StringBuilder sb = new StringBuilder();

        List<ExportOffersDto> offers = offerRepository.findOfferByApartmentType();

        offers.stream().forEach(exportOffersDto -> {
            sb.append(String.format("Agent %s %s with offer №%d:%n" +
                    "   \t\t-Apartment area: %.2f%n" +
                    "   \t\t--Town: %s%n" +
                    "   \t\t---Price: %.2f$",
                            exportOffersDto.getAgentFirstName(),
                            exportOffersDto.getAgentLastName(),
                            exportOffersDto.getId(),
                            exportOffersDto.getArea(),
                            exportOffersDto.getTownName(),
                            exportOffersDto.getPrice()))
                    .append(System.lineSeparator());
        });


        return sb.toString().trim();
    }
}
