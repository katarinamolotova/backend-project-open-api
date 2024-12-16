package edu.school21.backend.controllers;

import edu.school21.backend.services.ImageService;
import edu.school21.backend.utils.ExampleResponseError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Tag(
        name = "Image",
        description = "Контроллер для работы с изображениями"
)
@RequestMapping(value = "api/v1/image")
public class ImageController {

    private final ImageService service;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление изображения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.BAD_REQUEST))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public String addImage(
            @RequestParam("image") final MultipartFile image,
            @RequestParam("productId") final Long productId
    ) {
        return service.add(image, productId).toString();
    }

    @PutMapping(value = "/{id}/update-image-bytea", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Изменение изображения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.BAD_REQUEST))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public void updateImage(
            @PathVariable final UUID id,
            @RequestParam("image") final MultipartFile image
    ) {
        service.update(id, image);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление изображения по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public void deleteImageById(@PathVariable("id") final UUID id) {
        service.deleteById(id);
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Получение изображения конкретного товара")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public ResponseEntity<InputStreamResource> getImageByProductId(@PathVariable("productId") final Long id) {
        return getResponseEntity(service.getImageBytesByProductId(id));
    }

    @GetMapping("/{imageId}")
    @Operation(summary = "Получение изображения по id изображения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public ResponseEntity<InputStreamResource> getImageByImageId(@PathVariable("imageId") final UUID id) {
        return getResponseEntity(service.getImageBytesById(id));
    }

    private static ResponseEntity<InputStreamResource> getResponseEntity(final byte[] image) {
        return ResponseEntity.ok()
                             .contentType(MediaType.APPLICATION_OCTET_STREAM)
                             .contentLength(image.length)
                             .body(new InputStreamResource(new ByteArrayInputStream(image)));
    }
}
