package edu.school21.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Product")
public class ProductDTO {

    @Schema(
            description = "Product ID",
            example = "1"
    )
    @JsonProperty("id")
    private Long id;

    @Schema(
            description = "Product name",
            example = "Dishwasher"
    )
    @JsonProperty("name")
    private String name;

    @Schema(
            description = "Product category",
            example = "Kitchen appliances"
    )
    @JsonProperty("category")
    private String category;

    @Schema(
            description = "Product price",
            example = "9999.99"
    )
    @JsonProperty("price")
    private Double price;

    @Schema(
            description = "Available stock",
            example = "10"
    )
    @JsonProperty("available_stock")
    private Long availableStock;

    @Schema(
            description = "Last update date",
            example = "2024-09-26"
    )
    @JsonProperty("last_update_date")
    private Date lastUpdateDate;

    @Schema(
            description = "Supplier ID",
            example = "1"
    )
    @JsonProperty("supplier_id")
    private Long supplierId;

    @Schema(
            description = "Image ID",
            example = "e1857a30-5ac3-4a78-af63-ac173275983c",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @JsonProperty("image_id")
    private UUID imageId;
}
