/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.5.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.example.api;

import com.example.model.Participant;
import com.example.model.ServerResponse;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-09T09:24:21.029835377+03:00[Asia/Amman]", comments = "Generator version: 7.5.0")
@Validated
@Tag(name = "participants-controller", description = "the participants-controller API")
public interface ParticipantsControllerApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /api/v1/participants/create : Create a new participant
     *
     * @param participant  (required)
     * @return Participant was saved successfully (status code 201)
     */
    @Operation(
        operationId = "createParticipant",
        summary = "Create a new participant",
        tags = { "participants-controller" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Participant was saved successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ServerResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/v1/participants/create",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<ServerResponse> _createParticipant(
        @Parameter(name = "Participant", description = "", required = true) @Valid @RequestBody Participant participant
    ) {
        return createParticipant(participant);
    }

    // Override this method
    default  ResponseEntity<ServerResponse> createParticipant(Participant participant) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"message\", \"participant\" : { \"code\" : \"code\", \"name\" : \"name\", \"logo\" : \"logo\", \"shortName\" : \"shortName\", \"bic\" : \"bic\", \"settlementBank\" : \"settlementBank\" }, \"statusCode\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /api/v1/participants/view : Fetch all participants
     *
     * @return fetched all participants successfully (status code 200)
     */
    @Operation(
        operationId = "fetchAllParticipants",
        summary = "Fetch all participants",
        tags = { "participants-controller" },
        responses = {
            @ApiResponse(responseCode = "200", description = "fetched all participants successfully", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Participant.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/participants/view",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<Participant>> _fetchAllParticipants(
        
    ) {
        return fetchAllParticipants();
    }

    // Override this method
    default  ResponseEntity<List<Participant>> fetchAllParticipants() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"code\" : \"code\", \"name\" : \"name\", \"logo\" : \"logo\", \"shortName\" : \"shortName\", \"bic\" : \"bic\", \"settlementBank\" : \"settlementBank\" }, { \"code\" : \"code\", \"name\" : \"name\", \"logo\" : \"logo\", \"shortName\" : \"shortName\", \"bic\" : \"bic\", \"settlementBank\" : \"settlementBank\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /api/v1/participants/fetch-codes : Fetch direct codes
     *
     * @return fetched all direct codes successfully (status code 200)
     */
    @Operation(
        operationId = "fetchDirectCodes",
        summary = "Fetch direct codes",
        tags = { "participants-controller" },
        responses = {
            @ApiResponse(responseCode = "200", description = "fetched all direct codes successfully", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/participants/fetch-codes",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<String>> _fetchDirectCodes(
        
    ) {
        return fetchDirectCodes();
    }

    // Override this method
    default  ResponseEntity<List<String>> fetchDirectCodes() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ \"\", \"\" ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /api/v1/participants/view/{code} : Fetch participant by code
     *
     * @param code  (required)
     * @return fetched participant Successfully (status code 200)
     */
    @Operation(
        operationId = "fetchParticipant",
        summary = "Fetch participant by code",
        tags = { "participants-controller" },
        responses = {
            @ApiResponse(responseCode = "200", description = "fetched participant Successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ServerResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/participants/view/{code}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<ServerResponse> _fetchParticipant(
        @Pattern(regexp = "^\\d{6}$") @Parameter(name = "code", description = "", required = true, in = ParameterIn.PATH) @PathVariable("code") String code
    ) {
        return fetchParticipant(code);
    }

    // Override this method
    default  ResponseEntity<ServerResponse> fetchParticipant(String code) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"message\", \"participant\" : { \"code\" : \"code\", \"name\" : \"name\", \"logo\" : \"logo\", \"shortName\" : \"shortName\", \"bic\" : \"bic\", \"settlementBank\" : \"settlementBank\" }, \"statusCode\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /api/v1/participants/update/{code} : Updates participant information
     *
     * @param code  (required)
     * @param participant  (required)
     * @return Participant info updated successfully (status code 200)
     */
    @Operation(
        operationId = "updateParticipant",
        summary = "Updates participant information",
        tags = { "participants-controller" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Participant info updated successfully", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ServerResponse.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/api/v1/participants/update/{code}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<ServerResponse> _updateParticipant(
        @Pattern(regexp = "^\\d{6}$") @Parameter(name = "code", description = "", required = true, in = ParameterIn.PATH) @PathVariable("code") String code,
        @Parameter(name = "Participant", description = "", required = true) @Valid @RequestBody Participant participant
    ) {
        return updateParticipant(code, participant);
    }

    // Override this method
    default  ResponseEntity<ServerResponse> updateParticipant(String code, Participant participant) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"message\" : \"message\", \"participant\" : { \"code\" : \"code\", \"name\" : \"name\", \"logo\" : \"logo\", \"shortName\" : \"shortName\", \"bic\" : \"bic\", \"settlementBank\" : \"settlementBank\" }, \"statusCode\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
