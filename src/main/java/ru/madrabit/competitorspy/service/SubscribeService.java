package ru.madrabit.competitorspy.service;

import org.springframework.stereotype.Service;
import ru.madrabit.competitorspy.dto.SubscribeDTOReq;
import ru.madrabit.competitorspy.dto.SubscribeDTOResp;
import ru.madrabit.competitorspy.entity.Employee;
import ru.madrabit.competitorspy.entity.Subcribes;

import java.util.List;

@Service
public class SubscribeService {
    public SubscribeDTOResp subscribe(List<SubscribeDTOReq> subscribeDTOReqs) {
        return null;
    }

    public Subcribes getSubscribes(long empId) {
        return null;
    }
}
