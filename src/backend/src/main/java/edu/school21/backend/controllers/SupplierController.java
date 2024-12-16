package edu.school21.backend.controllers;

import edu.school21.backend.dto.SupplierDTO;
import edu.school21.backend.dto.input.InputAddressDTO;
import edu.school21.backend.dto.input.InputSupplierDTO;
import edu.school21.backend.services.SupplierService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(
        name = "Supplier",
        description = "Контроллер для работы с поставщиками"
)
@RequestMapping(value = "api/v1/supplier", produces = MediaType.APPLICATION_JSON_VALUE)
public class SupplierController {

    private final SupplierService service;

    @PostMapping("/add")
    @Operation(summary = "Добавление поставщика")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.BAD_REQUEST))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public SupplierDTO addSupplier(@RequestBody final InputSupplierDTO client) {
        return service.add(client);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление поставщика по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public void deleteSupplierById(@PathVariable("id") final Long id) {
        service.deleteById(id);
    }

    @GetMapping()
    @Operation(summary = "Получение всех поставщиков")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public List<SupplierDTO> getAllSuppliers() {
        return service.getAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Получение поставщика по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.BAD_REQUEST))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public SupplierDTO getSupplierById(@PathVariable("id") final Long id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}/update-address")
    @Operation(summary = "Изменение адреса поставщика")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.BAD_REQUEST))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public SupplierDTO updateAddressById(
            @PathVariable("id") final Long id,
            @RequestBody final InputAddressDTO address
    ) {
        return service.updateAddress(id, address);
    }
}
