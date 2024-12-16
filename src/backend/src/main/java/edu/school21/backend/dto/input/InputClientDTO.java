package edu.school21.backend.dto.input;


import com.fasterxml.jackson.annotation.JsonProperty;
import edu.school21.backend.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Input client object")
public class InputClientDTO {

    @Schema(
            description = "Client name",
            example = "Dmitry"
    )
    @JsonProperty("client_name")
    private String clientName;

    @Schema(
            description = "Client surname",
            example = "Ivanov"
    )
    @JsonProperty("client_surname")
    private String clientSurname;

    @Schema(
            description = "Client birthday",
            example = "2024-09-26"
    )
    @JsonProperty("birthday")
    private Date birthday;

    @Schema(
            description = "Client gender",
            example = "MAN"
    )
    @JsonProperty("gender")
    private Gender gender;

    @Schema(
            description = "Client address",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    @JsonProperty("address")
    private InputAddressDTO address;
}
