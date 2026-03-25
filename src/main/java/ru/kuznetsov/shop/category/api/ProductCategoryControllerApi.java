package ru.kuznetsov.shop.category.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kuznetsov.shop.represent.dto.ProductCategoryDto;

import java.util.Collection;
import java.util.List;

public interface ProductCategoryControllerApi {

    @Operation(summary = "Поиск по id", description = "Получение сущности по id записи")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductCategoryDto.class)
                    ),
                    description = "Категория"
            ),
            @ApiResponse(responseCode = "204",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    ),
                    description = "Категория не найдена")
    })
    ResponseEntity<ProductCategoryDto> getCategoryById(
            @Parameter(description = "Уникальный идентификатор категории для поиска", required = true,
                    schema = @Schema(
                            description = "Id категории",
                            example = "123",
                            type = "integer",
                            format = "int64"
                    )
            )
            @PathVariable Long id);

    @Operation(summary = "Получение всех сущностей", description = "Получение всех сущностей")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductCategoryDto[].class)
                    ),
                    description = "Список категорий"
            ),
            @ApiResponse(
                    responseCode = "204",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    ),
                    description = "Категорий не найдено"
            )
    })
    ResponseEntity<List<ProductCategoryDto>> getAllCategories(
            @Parameter(description = "Наименование категории для поиска",
                    schema = @Schema(
                            description = "Наименование категории",
                            example = "Продукты"
                    )
            )
            @RequestParam(required = false) String name
    );

    @Operation(summary = "Создание категории", description = "Создание категории")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductCategoryDto.class)
                    ),
                    description = "Сущность создана"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    ),
                    description = "Не корректно указаны данные"
            )
    })
    ResponseEntity<ProductCategoryDto> create(
            @Parameter(description = "Модель категории для создания", required = true,
                    schema = @Schema(
                            implementation = ProductCategoryDto.class,
                            description = "Категория"
                    ))
            @RequestBody ProductCategoryDto categoryDto);

    @Operation(summary = "Создание нескольких категорий", description = "Единовременное создание нескольких сущностей")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ProductCategoryDto[].class)
                    ),
                    description = "Сущность создана"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            schema = @Schema(hidden = true)
                    ),
                    description = "Не корректно указаны данные"
            )
    })
    ResponseEntity<List<ProductCategoryDto>> createBatch(
            @Parameter(description = "Модель категории для создания", required = true,
                    schema = @Schema(
                            implementation = ProductCategoryDto[].class,
                            description = "Категория"
                    ))
            @RequestBody Collection<ProductCategoryDto> dtoCollection);

    @Operation(summary = "Удаление по id", description = "Удаление сущности по id записи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Категория удалён"),
            @ApiResponse(responseCode = "404", description = "Категория не найден")
    })
    void deleteCategory(
            @Parameter(description = "Уникальный идентификатор категории для поиска", required = true,
                    schema = @Schema(
                            description = "Id категории",
                            example = "123",
                            type = "integer",
                            format = "int64"
                    )
            )
            @PathVariable Long id);
}
