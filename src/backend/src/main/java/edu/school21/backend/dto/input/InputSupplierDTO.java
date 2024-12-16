package edu.school21.backend.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Input supplier object")
public class InputSupplierDTO {

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
    private InputAddressDTO address;

    @Schema(
            description = "Supplier phone number",
            example = "+7-918-592-09-12"
    )
    @JsonProperty("phone_number")
    private String phoneNumber;
}
