package edu.school21.backend.controllers;

import edu.school21.backend.dto.ProductDTO;
import edu.school21.backend.dto.input.InputProductDTO;
import edu.school21.backend.services.ProductService;
import edu.school21.backend.utils.ExampleResponseError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(
        name = "Product",
        description = "Контроллер для работы с товарами"
)
@RequestMapping(value = "api/v1/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService service;

    @PostMapping("/add")
    @Operation(summary = "Добавление товара")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.BAD_REQUEST))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public ProductDTO addProduct(@RequestBody final InputProductDTO client) {
        return service.add(client);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление товара по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public void deleteProduct(@PathVariable("id") final Long id) {
        service.deleteById(id);
    }

    @GetMapping()
    @Operation(summary = "Получение всех доступных товаров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public List<ProductDTO> getAllProduct() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение товара по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.BAD_REQUEST))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public ProductDTO getById(@PathVariable("id") final Long id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}/update-decrease-count")
    @Operation(summary = "Уменьшение количества товара")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.BAD_REQUEST))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public ProductDTO updateAvailableStockById(
            @PathVariable("id") final Long id,
            @RequestParam(value = "decrease_count", defaultValue = "0") final Integer decreaseCount
    ) {
        return service.decreaseAvailableStock(id, decreaseCount);
    }
}
