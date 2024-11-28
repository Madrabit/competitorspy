package ru.madrabit.competitorspy.controller;

import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.index.qual.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.madrabit.competitorspy.dto.LogsDTOResp;
import ru.madrabit.competitorspy.dto.ParserStatusDTOResp;
import ru.madrabit.competitorspy.dto.SetParseFrequencyDTOResp;
import ru.madrabit.competitorspy.service.ParserAdminService;
import ru.madrabit.competitorspy.util.ParsingStatus;

@RestController
@RequestMapping("/parser/admin")
@RequiredArgsConstructor
public class ParserAdminController {
    private final ParserAdminService adminService;

    @GetMapping("/frequency/{hours}")
    public ResponseEntity<SetParseFrequencyDTOResp> setFrequency(@PathVariable(name = "hours") @Positive int hours) {
        SetParseFrequencyDTOResp dtoResp = adminService.setFrequency(hours);
        return ResponseEntity.ok(dtoResp);
    }

    @PostMapping("/start")
    public ResponseEntity<ParserStatusDTOResp>  startParsing() {
        ParserStatusDTOResp dtoResp = adminService.startParsing();
        if(dtoResp.status() == ParsingStatus.NOT_STARTED) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(dtoResp);
        }
        return ResponseEntity.ok(dtoResp);
    }

    @GetMapping("/status")
    public ResponseEntity<ParserStatusDTOResp> checkStatus(){
        ParserStatusDTOResp status = adminService.checkStatus();
        return ResponseEntity.ok(status);
    }

    @GetMapping("logs")
    public ResponseEntity<LogsDTOResp> getLogs() {
        LogsDTOResp logs = adminService.getLogs();
        return ResponseEntity.ok(logs);
    }
}
