package edu.school21.backend.dto.input;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Input address object")
public class InputAddressDTO {

    @Schema(
            description = "Country",
            example = "Russia"
    )
    @JsonProperty
    private String country;

    @Schema(
            description = "City",
            example = "Kazan"
    )
    @JsonProperty
    private String city;

    @Schema(
            description = "Street",
            example = "Spartakovskay"
    )
    @JsonProperty
    private String street;
}
