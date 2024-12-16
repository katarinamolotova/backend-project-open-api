package edu.school21.backend.repositories;

import edu.school21.backend.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    Optional<AddressEntity> findByCityAndStreetAndCountry(String city, String street, String country);
}
