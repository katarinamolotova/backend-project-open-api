package edu.school21.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Image")
public class ImageDTO {

    @JsonProperty("id")
    @Schema(
            description = "Image ID",
            example = "e1857a30-5ac3-4a78-af63-ac173275983c"
    )
    private UUID id;

    @JsonProperty("image")
    @Schema(
            description = "Image byte array"
    )
    private byte[] image;

}
