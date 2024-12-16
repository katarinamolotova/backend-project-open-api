package edu.school21.backend.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "product", schema = "public", catalog = "backendProject")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "category")
    private String category;
    @Basic
    @Column(name = "price")
    private double price;
    @Basic
    @Column(name = "available_stock")
    private long availableStock;
    @Basic
    @Column(name = "last_update_date")
    private Date lastUpdateDate;
    @Basic
    @Column(name = "supplier_id")
    private long supplierId;
    @Basic
    @Column(name = "image_id")
    private UUID imageId;
}
