package edu.school21.backend.services;

import edu.school21.backend.dto.AddressDTO;
import edu.school21.backend.dto.input.InputAddressDTO;
import edu.school21.backend.entities.AddressEntity;
import edu.school21.backend.repositories.AddressRepository;
import edu.school21.backend.utils.ExceptionUtil;
import edu.school21.backend.utils.MappingUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AddressService {

    private final AddressRepository repository;
    private final MappingUtil mapper;

    public AddressDTO getById(final Long id) {
        return repository
                .findById(id)
                .map(address -> mapper.map(address, AddressDTO.class))
                .orElseThrow(() -> ExceptionUtil.createNotFoundException("Not found address by id = " + id));
    }

    public Map<Long, AddressDTO> getAddressIdToAddressByIds(final Set<Long> ids) {
        return getByIds(ids)
                .stream()
                .collect(Collectors.toMap(
                        AddressDTO::getId,
                        Function.identity()
                ));
    }

    public List<AddressDTO> getByIds(final Set<Long> ids) {
        final List<AddressEntity> addresses = repository.findAllById(ids);
        return mapper.mapList(addresses, AddressDTO.class);
    }

    public AddressDTO createIfNotExists(final InputAddressDTO address) {
        return findByCityAndStreetAndCountry(address.getCity(), address.getStreet(), address.getCountry())
                .orElseGet(() -> add(address));
    }

    public Optional<AddressDTO> findByCityAndStreetAndCountry(final String city, final String street, final String country) {
        return repository
                .findByCityAndStreetAndCountry(city, street, country)
                .map(address -> mapper.map(address, AddressDTO.class));
    }

    public AddressDTO add(final InputAddressDTO address) {
        final AddressEntity entity = mapper.map(address, AddressEntity.class);
        return mapper.map(repository.save(entity), AddressDTO.class);
    }
}
