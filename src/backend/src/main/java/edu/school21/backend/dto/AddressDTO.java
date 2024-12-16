package edu.school21.backend.dto;

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
@Schema(description = "Address")
public class AddressDTO {

    @Schema(
            description = "Address ID",
            example = "1"
    )
    @JsonProperty
    private long id;

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
