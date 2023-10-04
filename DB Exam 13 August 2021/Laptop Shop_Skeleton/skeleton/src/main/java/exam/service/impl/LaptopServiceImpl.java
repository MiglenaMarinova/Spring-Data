package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.ExportLaptopDto;
import exam.model.dto.ImportCustomersDto;
import exam.model.dto.ImportLaptopsDto;
import exam.model.entity.Laptop;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LaptopServiceImpl implements LaptopService {

    public static final String LAPTOP_FILE_PATH = "src/main/resources/files/json/laptops.json";

    private final LaptopRepository laptopRepository;
    private final ShopRepository shopRepository;
    private final ValidationUtil validationUtil;
    private  final ModelMapper modelMapper;
    private final Gson gson;


    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository, ValidationUtil validationUtil, ModelMapper modelMapper, Gson gson) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(LAPTOP_FILE_PATH));
    }

    @Override
    public String importLaptops() throws IOException {

        StringBuilder sb = new StringBuilder();

        ImportLaptopsDto[] importLaptopsDtos = gson.fromJson(readLaptopsFileContent(), ImportLaptopsDto[].class);

        Arrays.stream(importLaptopsDtos)
                .filter(importLaptopsDto -> {
                    boolean isValid = validationUtil.isValid(importLaptopsDto);
                    boolean isUnique = laptopRepository.findAllByMacAddress(importLaptopsDto.getMacAddress()).isEmpty();

                    if (isValid && isUnique){
                        sb.append(String.format("Successfully imported Laptop B5-42-0A-AC-F0-19 - 1.46 - 128 - 128",
                                importLaptopsDto.getMacAddress(),
                                importLaptopsDto.getCpuSpeed(),
                                importLaptopsDto.getRam(),
                                importLaptopsDto.getStorage())).append(System.lineSeparator());
                    }else{
                        sb.append("Invalid Laptop").append(System.lineSeparator());
                    }
                    return (isValid && isUnique);
                })
                .map(importLaptopsDto -> {
                    Laptop laptop = modelMapper.map(importLaptopsDto, Laptop.class);
                    laptop.setShop(shopRepository.findAllByName(importLaptopsDto.getShop().getName()).get());

                    return laptop;
                }).forEach(laptopRepository :: save);



        return sb.toString().trim();
    }

    @Override
    public String exportBestLaptops() {
        StringBuilder sb = new StringBuilder();


        List<ExportLaptopDto> laptops = laptopRepository.exportBestLaptops();

        laptops.stream().forEach(exportLaptopDto -> {
            sb.append(String.format("Laptop - %s%n" +
                            "*Cpu speed - %.2f%n" +
                            "**Ram - %d%n" +
                            "***Storage - %d%n" +
                            "****Price - %.2f%n" +
                            "#Shop name - %s%n" +
                            "##Town - %s",
                            exportLaptopDto.getMacAddress(),
                            exportLaptopDto.getCpuSpeed(),
                            exportLaptopDto.getRam(),
                            exportLaptopDto.getStorage(),
                            exportLaptopDto.getPrice(),
                            exportLaptopDto.getShopName(),
                            exportLaptopDto.getTownName()))
                    .append(System.lineSeparator());
        });
        return sb.toString().trim();
    }
}
