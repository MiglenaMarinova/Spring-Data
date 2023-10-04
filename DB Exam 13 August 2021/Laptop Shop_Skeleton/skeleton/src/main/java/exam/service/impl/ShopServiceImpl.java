package exam.service.impl;

import exam.model.dto.ShopsRootSeedDto;
import exam.model.entity.Shop;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ShopServiceImpl implements ShopService {

    public static final String SHOPS_FILE_PATH = "src/main/resources/files/xml/shops.xml";

    private final ShopRepository shopRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final TownRepository townRepository;

    public ShopServiceImpl(ShopRepository shopRepository, ValidationUtil validationUtil, ModelMapper modelMapper, XmlParser xmlParser, TownRepository townRepository) {
        this.shopRepository = shopRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(SHOPS_FILE_PATH));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        ShopsRootSeedDto shopsRootSeedDto = xmlParser.fromFile(SHOPS_FILE_PATH, ShopsRootSeedDto.class);

        shopsRootSeedDto.getShops().stream()
                .filter(shopSeedDto -> {
                    boolean isValid = validationUtil.isValid(shopSeedDto);
                    boolean isUnique = shopRepository.findAllByName(shopSeedDto.getName()).isEmpty();

                    if (isValid && isUnique){
                        sb.append(String.format("Successfully imported Shop %s - %.0f",
                                shopSeedDto.getName(), shopSeedDto.getIncome())).append(System.lineSeparator());
                    }else{
                        sb.append("Invalid Shop").append(System.lineSeparator());
                    }
                    return (isValid && isUnique);
                })
                .map(shopSeedDto -> {
                    Shop shop = modelMapper.map(shopSeedDto, Shop.class);
                    shop.setTown(townRepository.findAllByName(shopSeedDto.getTown().getName()).get());
                    return shop;
                }).forEach(shopRepository :: save);

        return sb.toString().trim();
    }
}
