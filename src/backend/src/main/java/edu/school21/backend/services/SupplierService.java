package edu.school21.backend.services;

import edu.school21.backend.dto.AddressDTO;
import edu.school21.backend.dto.SupplierDTO;
import edu.school21.backend.dto.input.InputAddressDTO;
import edu.school21.backend.dto.input.InputSupplierDTO;
import edu.school21.backend.entities.SupplierEntity;
import edu.school21.backend.repositories.SupplierRepository;
import edu.school21.backend.utils.ExceptionUtil;
import edu.school21.backend.utils.MappingUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SupplierService {

    private final AddressService addressService;
    private final SupplierRepository repository;
    private final MappingUtil mapper;

    public SupplierDTO add(final InputSupplierDTO inputSupplier) {
        final InputAddressDTO inputAddress = inputSupplier.getAddress();
        final AddressDTO address = addressService.createIfNotExists(inputAddress);

        final SupplierEntity entity = mapper.map(inputSupplier, SupplierEntity.class);
        entity.setAddressId(address.getId());
        final SupplierEntity savedSupplier = repository.save(entity);

        final SupplierDTO supplierDTO = mapper.map(savedSupplier, SupplierDTO.class);
        supplierDTO.setAddress(address);
        return supplierDTO;
    }

    public void deleteById(final Long id) {
        repository
                .findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> { throw ExceptionUtil.createNotFoundException("Not found supplier by id = " + id); }
                );
    }

    public List<SupplierDTO> getAll() {
        final List<SupplierEntity> entities = repository.findAll();
        return getSuppliersWithAddress(entities);
    }

    public SupplierDTO getById(final Long id) {
        final SupplierEntity entity = repository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.createNotFoundException("Not found supplier by id = " + id));

        final AddressDTO address = addressService.getById(entity.getAddressId());
        final SupplierDTO supplier = mapper.map(entity, SupplierDTO.class);
        supplier.setAddress(address);
        return supplier;
    }

    public SupplierDTO updateAddress(final Long id, final InputAddressDTO inputAddress) {
        final SupplierEntity entity = repository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.createNotFoundException("Not found supplier by id = " + id));
        final AddressDTO address = addressService.createIfNotExists(inputAddress);
        final long addressId = address.getId();

        if (entity.getAddressId() != addressId) {
            entity.setAddressId(addressId);
            repository.save(entity);
        }

        final SupplierDTO supplierDTO = mapper.map(entity, SupplierDTO.class);
        supplierDTO.setAddress(address);
        return supplierDTO;
    }

    private List<SupplierDTO> getSuppliersWithAddress(final List<SupplierEntity> suppliers) {
        final Map<Long, Long> supplierIdToAddressId = getSupplierIdToAddressId(suppliers);
        final Set<Long> addressIds = new HashSet<>(supplierIdToAddressId.values());
        final Map<Long, AddressDTO> addressIdToAddress = addressService.getAddressIdToAddressByIds(addressIds);

        return mapper
                .mapList(suppliers, SupplierDTO.class)
                .stream()
                .peek(client -> client.setAddress(addressIdToAddress.get(supplierIdToAddressId.get(client.getId()))))
                .collect(Collectors.toList());
    }

    private static Map<Long, Long> getSupplierIdToAddressId(final List<SupplierEntity> suppliers) {
        return suppliers
                .stream()
                .collect(Collectors.toMap(
                        SupplierEntity::getId,
                        SupplierEntity::getAddressId
                ));
    }
}
