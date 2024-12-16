package edu.school21.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Supplier")
public class SupplierDTO {

    @Schema(
            description = "Supplier ID",
            example = "1"
    )
    @JsonProperty("id")
    private Long id;

    @Schema(
            description = "Supplier name",
            example = "Redmond"
    )
    @JsonProperty("name")
    private String name;

    @Schema(
            description = "Supplier address"
    )
    @JsonProperty("address")
    private AddressDTO address;

    @Schema(
            description = "Supplier phone number",
            example = "+7-918-592-09-12"
    )
    @JsonProperty("phone_number")
    private String phoneNumber;
}
