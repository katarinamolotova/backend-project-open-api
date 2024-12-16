package edu.school21.backend.dto;

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
@Schema(description = "Client")
public class ClientDTO {

    @JsonProperty("id")
    @Schema(
            description = "Client ID",
            example = "1"
    )
    private Long id;

    @JsonProperty("client_name")
    @Schema(
            description = "Client name",
            example = "Dmitry"
    )
    private String clientName;

    @JsonProperty("client_surname")
    @Schema(
            description = "Client surname",
            example = "Ivanov"
    )
    private String clientSurname;

    @JsonProperty("birthday")
    @Schema(
            description = "Client birthday",
            example = "2024-09-26"
    )
    private Date birthday;

    @JsonProperty("gender")
    @Schema(
            description = "Client gender",
            example = "MAN"
    )
    private Gender gender;

    @JsonProperty("registration_date")
    @Schema(
            description = "Client registration date",
            example = "2024-09-26"
    )
    private Date registrationDate;

    @JsonProperty("address")
    @Schema(
            description = "Client address",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private AddressDTO address;
}
