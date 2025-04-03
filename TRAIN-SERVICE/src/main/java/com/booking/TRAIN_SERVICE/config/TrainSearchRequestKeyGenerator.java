package com.booking.TRAIN_SERVICE.config;

import com.booking.TRAIN_SERVICE.request.TrainSearchRequest;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class TrainSearchRequestKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        // Assuming the first parameter is of type TrainSearchRequest
        TrainSearchRequest trainSearchRequest = (TrainSearchRequest) params[0];

        // Generate a custom key based on the fields of TrainSearchRequest
        return trainSearchRequest.getFromStationCode() + "-" +
                trainSearchRequest.getToStationCode() + "-" +
                trainSearchRequest.getDateOfJourney() + "-" +
                trainSearchRequest.getCoachType();
    }
}
