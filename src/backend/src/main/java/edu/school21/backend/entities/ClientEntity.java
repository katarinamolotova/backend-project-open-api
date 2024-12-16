package edu.school21.backend.entities;

import edu.school21.backend.enums.Gender;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "client", schema = "public", catalog = "backendProject")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "client_name")
    private String clientName;
    @Basic
    @Column(name = "client_surname")
    private String clientSurname;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Basic
    @Column(name = "registration_date")
    private Date registrationDate;
    @Basic
    @Column(name = "address_id")
    private long addressId;
}
