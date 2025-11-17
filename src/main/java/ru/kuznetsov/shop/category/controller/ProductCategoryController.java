package ru.kuznetsov.shop.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuznetsov.shop.data.service.ProductCategoryService;
import ru.kuznetsov.shop.represent.dto.ProductCategoryDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/product-category")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(productCategoryService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<ProductCategoryDto>> getAllStores(
            @RequestParam(required = false) String name
    ) {
        List<ProductCategoryDto> result = new ArrayList<>();
        if (name != null) {
            result.add(productCategoryService.findByName(name));
        } else result.addAll(productCategoryService.findAll());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ProductCategoryDto> create(@RequestBody ProductCategoryDto storeDto) {
        return ResponseEntity.ok(productCategoryService.add(storeDto));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<ProductCategoryDto>> createBatch(@RequestBody Collection<ProductCategoryDto> dtoCollection) {
        return ResponseEntity.ok(dtoCollection.stream()
                .map(productCategoryService::add)
                .toList());
    }

    @DeleteMapping("/{id}")
    public void deleteeStore(@PathVariable Long id) {
        productCategoryService.deleteById(id);
    }
}
