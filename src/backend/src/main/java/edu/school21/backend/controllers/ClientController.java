package edu.school21.backend.controllers;

import edu.school21.backend.dto.ClientDTO;
import edu.school21.backend.dto.input.InputAddressDTO;
import edu.school21.backend.dto.input.InputClientDTO;
import edu.school21.backend.services.ClientService;
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
        name = "Client",
        description = "Контроллер для работы с клиентами"
)
@RequestMapping(value = "api/v1/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    private final ClientService service;

    @PostMapping("/add")
    @Operation(summary = "Добавление клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.BAD_REQUEST))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public ClientDTO addClient(@RequestBody final InputClientDTO client) {
        return service.add(client);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление клиента (по его идентификатору)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public void deleteClient(@PathVariable("id") final Long id) {
        service.deleteById(id);
    }

    @GetMapping("/by-name-and-surname")
    @Operation(summary = "Получение клиентов по имени и фамилии")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public List<ClientDTO> getByNameAndSurname(
            @RequestParam("client_name") final String clientName,
            @RequestParam("client_surname") final String clientSurname
    ) {
        return service.getByClientNameAndClientSurname(
                clientName,
                clientSurname
        );
    }

    @GetMapping()
    @Operation(summary = "Получение всех клиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.BAD_REQUEST))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public List<ClientDTO> getAllClients(
            @RequestParam(value = "limit", defaultValue = "10", required = false) final Integer limit,
            @RequestParam(value = "offset", defaultValue = "0", required = false) final Integer offset
    ) {
        return service.findAll(limit, offset);
    }

    @PatchMapping("/{id}/update-address")
    @Operation(summary = "Изменение адреса клиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.BAD_REQUEST))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.NOT_FOUND))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(examples = @ExampleObject(value = ExampleResponseError.INTERNAL_SERVER_ERROR)))
    })
    public ClientDTO updateAddress(
            @PathVariable("id") final Long id,
            @RequestBody final InputAddressDTO address
    ) {
        return service.updateAddress(id, address);
    }
}
