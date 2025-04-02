package com.booking.TRAIN_SERVICE.services.IMPL;

import com.booking.TRAIN_SERVICE.model.Seat;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatCacheService {

    private static final String REDIS_PREFIX = "available_seats:";
    private static final long DEFAULT_EXPIRY = 15; // Expiry in minutes

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, String, Boolean> hashOps;

    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }

    // ✅ Cache seat availability using Hash Operations
    public void cacheAvailableSeats(Long trainId, List<Seat> seats) {
        String key = REDIS_PREFIX + trainId;

        // Store only seat IDs to reduce memory usage
        seats.forEach(seat -> hashOps.put(key, seat.getId(), !seat.isBooked()));

        // Set expiry dynamically based on load
        redisTemplate.expire(key, DEFAULT_EXPIRY, TimeUnit.MINUTES);
    }

    // ✅ Fetch available seat IDs (instead of full objects)
    public List<Long> getAvailableSeatIds(Long trainId) {
        String key = REDIS_PREFIX + trainId;

        return hashOps.entries(key).entrySet().parallelStream()  // 🔥 Parallel processing for large data
                .filter(entry -> Boolean.TRUE.equals(entry.getValue()))
                .map(entry -> Long.parseLong(entry.getKey()))
                .collect(Collectors.toList());
    }


    // ✅ Mark seat as booked in Redis
    public void markSeatAsBooked(Long trainId, Long seatId) {
        String key = REDIS_PREFIX + trainId;
        hashOps.put(key, seatId.toString(), false);
    }
}
