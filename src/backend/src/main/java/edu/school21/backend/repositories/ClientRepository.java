package edu.school21.backend.repositories;

import edu.school21.backend.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    List<ClientEntity> findAllByClientNameAndClientSurname(String clientName, String clientSurname);
}
