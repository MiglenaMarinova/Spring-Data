package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.ImportCustomersDto;
import exam.model.entity.Customer;
import exam.repository.CustomerRepository;
import exam.repository.TownRepository;
import exam.service.CustomerService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final String CUSTOMERS_FILE_PATH = "src/main/resources/files/json/customers.json";

    private final CustomerRepository customerRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;


    public CustomerServiceImpl(CustomerRepository customerRepository,
                               TownRepository townRepository, Gson gson,
                               ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(Path.of(CUSTOMERS_FILE_PATH));
    }

    @Override
    public String importCustomers() throws IOException {
        StringBuilder sb = new StringBuilder();

        ImportCustomersDto[] importCustomersDtos = gson.fromJson(readCustomersFileContent(), ImportCustomersDto[].class);
        Arrays.stream(importCustomersDtos)
                .filter(importCustomersDto -> {
                    boolean isValid = validationUtil.isValid(importCustomersDto);
                    boolean isUnique = customerRepository.findAllByEmail(importCustomersDto.getEmail()).isEmpty();

                    if (isValid && isUnique){
                        sb.append(String.format("Successfully imported Customer %s %s - %s",
                                        importCustomersDto.getFirstName(),
                                        importCustomersDto.getLastName(),
                                        importCustomersDto.getEmail()))
                                .append(System.lineSeparator());
                    }else{
                        sb.append("Invalid Customer").append(System.lineSeparator());
                    }
                    return (isValid && isUnique);
                })
                .map(importCustomersDto -> {
                    Customer customer = modelMapper.map(importCustomersDto, Customer.class);
                    customer.setTown(townRepository.findAllByName(importCustomersDto.getTown().getName()).get());
                    return customer;
                }).forEach(customerRepository::save);


        return sb.toString().trim();
    }
}
