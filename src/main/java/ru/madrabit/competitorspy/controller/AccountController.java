package ru.madrabit.competitorspy.controller;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.madrabit.competitorspy.dto.SubscribeNewProductsDTOResp;
import ru.madrabit.competitorspy.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/notifications")
    public ResponseEntity<SubscribeNewProductsDTOResp> subscribeNewProducts(@RequestParam @Positive long userId) {
        if(userId < 0 || !accountService.isUserExists(userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SubscribeNewProductsDTOResp("Пользователь не найден", false));
        }
        return ResponseEntity.ok(accountService.subscribeNewProducts(userId));

    }
}
