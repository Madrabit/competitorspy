package ru.madrabit.competitorspy.service;

import org.springframework.stereotype.Service;
import ru.madrabit.competitorspy.dto.SubscribeDTOResp;
import ru.madrabit.competitorspy.entity.Subscribes;

import java.util.List;

@Service
public class SubscribeService {
    public SubscribeDTOResp subscribe(long userId, List<Integer> products) {
        return null;
    }

    public Subscribes getSubscribes(long empId) {
        return null;
    }
}
