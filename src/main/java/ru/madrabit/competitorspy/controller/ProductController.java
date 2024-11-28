package ru.madrabit.competitorspy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.madrabit.competitorspy.dto.ProductProgramDTOResp;
import ru.madrabit.competitorspy.entity.Product;
import ru.madrabit.competitorspy.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    @GetMapping("")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductProgramDTOResp> getAll(@PathVariable("productId") long productId) {
        return ResponseEntity.ok(service.getProgamById(productId));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Product>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getProductPaginated(page, size));
    }


}
