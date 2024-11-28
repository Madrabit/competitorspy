package ru.madrabit.competitorspy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.checkerframework.checker.index.qual.Positive;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.madrabit.competitorspy.dto.SubscribeDTOReq;
import ru.madrabit.competitorspy.dto.SubscribeDTOResp;
import ru.madrabit.competitorspy.dto.SubscribesDTOResp;
import ru.madrabit.competitorspy.entity.Subscribes;
import ru.madrabit.competitorspy.service.SubscribeService;

@RestController
@RequestMapping("/subscribe")
@RequiredArgsConstructor
@Validated
public class SubscribeController {
    private final SubscribeService service;

    @PostMapping("")
    public ResponseEntity<SubscribeDTOResp> subscribe(@RequestBody @Valid SubscribeDTOReq resp) {
        SubscribeDTOResp dtoResp = service.subscribe(resp.id(), resp.products());
        return ResponseEntity.ok(dtoResp);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Subscribes> getSubscribes(@PathVariable("userId") @Positive long userId) {
        return ResponseEntity.ok(service.getSubscribes(userId));
    }

}
