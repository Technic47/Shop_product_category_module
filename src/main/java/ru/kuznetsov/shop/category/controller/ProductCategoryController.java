package ru.kuznetsov.shop.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuznetsov.shop.category.api.ProductCategoryControllerApi;
import ru.kuznetsov.shop.data.service.ProductCategoryService;
import ru.kuznetsov.shop.represent.dto.ProductCategoryDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/product-category")
@RequiredArgsConstructor
public class ProductCategoryController implements ProductCategoryControllerApi {

    private final ProductCategoryService productCategoryService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> getCategoryById(@PathVariable Long id) {
        ProductCategoryDto byId = productCategoryService.findById(id);
        return byId == null ?
                ResponseEntity.status(NO_CONTENT).build()
                : ResponseEntity.ok(byId);
    }

    @GetMapping()
    public ResponseEntity<List<ProductCategoryDto>> getAllCategories(
            @RequestParam(required = false) String name
    ) {
        List<ProductCategoryDto> result = new ArrayList<>();

        if (name != null) {
            result.add(productCategoryService.findByName(name));
        } else result.addAll(productCategoryService.findAll());

        return result.isEmpty() ?
                ResponseEntity.status(NO_CONTENT).build()
                : ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ProductCategoryDto> create(@RequestBody ProductCategoryDto categoryDto) {
        return ResponseEntity.ok(productCategoryService.add(categoryDto));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<ProductCategoryDto>> createBatch(@RequestBody Collection<ProductCategoryDto> dtoCollection) {
        return ResponseEntity.ok(dtoCollection.stream()
                .map(productCategoryService::add)
                .toList());
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        productCategoryService.deleteById(id);
    }
}
