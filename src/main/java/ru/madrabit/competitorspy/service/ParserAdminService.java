package ru.madrabit.competitorspy.service;

import org.springframework.stereotype.Service;
import ru.madrabit.competitorspy.dto.LogsDTOResp;
import ru.madrabit.competitorspy.dto.ParserStatusDTOResp;
import ru.madrabit.competitorspy.dto.SetParseFrequencyDTOResp;
import ru.madrabit.competitorspy.util.ParsingStatus;

@Service
public class ParserAdminService {

    public SetParseFrequencyDTOResp setFrequency(int i) {
        return null;
    }

    public ParserStatusDTOResp startParsing() {
        return null;
    }

    public ParserStatusDTOResp checkStatus() {
        return null;
    }

    public LogsDTOResp getLogs() {
        return null;
    }
}
