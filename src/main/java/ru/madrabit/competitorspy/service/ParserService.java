package ru.madrabit.competitorspy.service;

import org.springframework.stereotype.Service;
import ru.madrabit.competitorspy.dto.LogsDTOResp;
import ru.madrabit.competitorspy.dto.SetParseFrequencyDTOResp;
import ru.madrabit.competitorspy.util.ParsingStatus;

@Service
public class ParserService {

    public SetParseFrequencyDTOResp setFrequency(int i) {
        return null;
    }

    public String startParsing(boolean isStarted) {
        return "";
    }

    public ParsingStatus checkStatus() {
        return null;
    }

    public LogsDTOResp getLogs() {
        return null;
    }
}
