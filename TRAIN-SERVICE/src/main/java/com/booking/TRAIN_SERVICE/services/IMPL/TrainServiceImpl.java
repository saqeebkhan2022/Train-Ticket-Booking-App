package com.booking.TRAIN_SERVICE.services.IMPL;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.enums.SeatStatus;
import com.booking.TRAIN_SERVICE.model.Coach;
import com.booking.TRAIN_SERVICE.model.Station;
import com.booking.TRAIN_SERVICE.model.Train;
import com.booking.TRAIN_SERVICE.repository.StationRepository;
import com.booking.TRAIN_SERVICE.repository.TrainRepository;
import com.booking.TRAIN_SERVICE.request.TrainSearchRequest;
import com.booking.TRAIN_SERVICE.services.TrainService;
import com.booking.TRAIN_SERVICE.uitls.DateUtility;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;
    private final StationRepository stationRepository;

    @Override
    @Cacheable(value = "trains", key = "#trainNumber")
    public Train getTrainByNumber(String trainNumber) {
        return trainRepository.findByTrainNumber(trainNumber)
                .orElseThrow(() -> new RuntimeException("Train not found!"));
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train saveTrain(Train train) {
        String trainId = UUID.randomUUID().toString();
        train.setId(trainId);
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getTrainsByName(String trainName) {
         return trainRepository.findByTrainNameContainingIgnoreCase(trainName);
    }

    @Override
    public void deleteTrain(String trainId) {
        trainRepository.deleteById(trainId);
    }


    @Override
    public Page<Train> searchTrain(TrainSearchRequest trainSearchRequest, Pageable pageable) {
        String dayOfWeek = trainSearchRequest.getDateOfJourney().getDayOfWeek()
                .getDisplayName(TextStyle.SHORT, Locale.ENGLISH);

        // Delegate the paged search to the repository
        Page<Train> trainsPage = trainRepository.findTrains(
                trainSearchRequest.getFromStationCode(),
                trainSearchRequest.getToStationCode(),
                dayOfWeek,
                trainSearchRequest.getCoachType(),
                trainSearchRequest.getSeatCategory(),
                pageable
        );

        // Process the results to include seat status (available and waiting list seats)
        for (Train train : trainsPage) {
            for (Coach coach : train.getCoaches()) {
                // Get available seats and waiting list seats
                long availableSeats = coach.getSeats().stream()
                        .filter(seat -> seat.getSeatStatus() == SeatStatus.AVAILABLE && !seat.isBooked())
                        .count();

                long waitingListSeats = coach.getSeats().stream()
                        .filter(seat -> seat.getSeatStatus() == SeatStatus.WAITING)
                        .count();

                // Calculate general and tatkal waitlist counts
                int generalWaitlistCount = coach.getGeneralWaitlistCount();
                int tatkalWaitlistCount = coach.getTatkalWaitlistCount();

                // Prepare the seat status message
                String seatStatus = "Available " + availableSeats + " WL " + waitingListSeats;
                String waitlistStatus = "General WL: " + generalWaitlistCount + " Tatkal WL: " + tatkalWaitlistCount;

                // Print or log the seat status and waitlist information
                System.out.println("Train: " + train.getTrainNumber() + " - Coach: " + coach.getCoachNumber());
                System.out.println("Seat Status: " + seatStatus);
                System.out.println("Waitlist Status: " + waitlistStatus);
            }
        }

        return trainsPage;
    }







}
