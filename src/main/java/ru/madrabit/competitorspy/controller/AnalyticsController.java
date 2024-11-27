package ru.madrabit.competitorspy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.madrabit.competitorspy.entity.ProductHistory;
import ru.madrabit.competitorspy.service.AnalyticsService;

import java.util.List;

@RestController
@RequestMapping("/analytics/")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("popularity")
    public ResponseEntity<List<ProductHistory>> retrieveTopPopular() {
        List<ProductHistory> productHistories = analyticsService.retrieveTopPopular();
        return ResponseEntity.ok(productHistories);
    }


    @GetMapping("products/{productId}/history")
    public ResponseEntity<List<ProductHistory>> retrieveHistoryOfProduct(@PathVariable(name = "productId") long productId) {
        List<ProductHistory> productHistory = analyticsService.retrieveHistoryOfProduct(productId);
        return ResponseEntity.ok(productHistory);
    }


}
