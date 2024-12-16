package edu.school21.backend.services;

import edu.school21.backend.dto.AddressDTO;
import edu.school21.backend.dto.ClientDTO;
import edu.school21.backend.dto.input.InputAddressDTO;
import edu.school21.backend.dto.input.InputClientDTO;
import edu.school21.backend.entities.ClientEntity;
import edu.school21.backend.repositories.ClientRepository;
import edu.school21.backend.utils.ExceptionUtil;
import edu.school21.backend.utils.MappingUtil;
import edu.school21.backend.utils.ValidateHelper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    private final AddressService addressService;
    private final ValidateHelper validateHelper;
    private final ClientRepository repository;
    private final MappingUtil mapper;

    public ClientDTO add(final InputClientDTO inputClient) {
        final InputAddressDTO inputAddress = inputClient.getAddress();
        final AddressDTO address = addressService.createIfNotExists(inputAddress);

        final ClientEntity entity = mapper.map(inputClient, ClientEntity.class);
        entity.setAddressId(address.getId());
        entity.setRegistrationDate(Date.valueOf(LocalDateTime.now().toLocalDate()));
        final ClientEntity savedClient = repository.save(entity);

        final ClientDTO clientDTO = mapper.map(savedClient, ClientDTO.class);
        clientDTO.setAddress(address);
        return clientDTO;
    }

    public void deleteById(final Long id) {
        repository
                .findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> { throw ExceptionUtil.createNotFoundException("Not found client by id = " + id); }
                );
    }

    public List<ClientDTO> getByClientNameAndClientSurname(final String clientName, final String clientSurname) {
        final List<ClientEntity> clients = repository.findAllByClientNameAndClientSurname(clientName, clientSurname);
        return getClientsWithAddress(clients);
    }

    public List<ClientDTO> findAll(final Integer limit, final Integer offset) {
        validateHelper.validatePaging(limit, offset);

        final Page<ClientEntity> clients = repository.findAll(PageRequest.of(offset, limit));
        return getClientsWithAddress(clients.toList());
    }

    public ClientDTO updateAddress(final Long id, final InputAddressDTO inputAddress) {
        final ClientEntity clientEntity = repository
                .findById(id)
                .orElseThrow(() -> ExceptionUtil.createNotFoundException("Not found client by id = " + id));
        final AddressDTO address = addressService.createIfNotExists(inputAddress);
        final long addressId = address.getId();

        if (clientEntity.getAddressId() != addressId) {
            clientEntity.setAddressId(addressId);
            repository.save(clientEntity);
        }

        final ClientDTO client = mapper.map(clientEntity, ClientDTO.class);
        client.setAddress(address);
        return client;
    }

    private List<ClientDTO> getClientsWithAddress(final List<ClientEntity> clients) {
        final Map<Long, Long> clientIdToAddressId = getClientIdToAddressId(clients);
        final Set<Long> addressIds = new HashSet<>(clientIdToAddressId.values());
        final Map<Long, AddressDTO> addressIdToAddress = addressService.getAddressIdToAddressByIds(addressIds);

        return mapper
                .mapList(clients, ClientDTO.class)
                .stream()
                .peek(client -> client.setAddress(addressIdToAddress.get(clientIdToAddressId.get(client.getId()))))
                .collect(Collectors.toList());
    }

    private static Map<Long, Long> getClientIdToAddressId(final List<ClientEntity> clients) {
        return clients
                .stream()
                .collect(Collectors.toMap(
                        ClientEntity::getId,
                        ClientEntity::getAddressId
                ));
    }
}
