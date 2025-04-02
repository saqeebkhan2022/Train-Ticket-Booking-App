package com.booking.TRAIN_SERVICE;

import com.booking.TRAIN_SERVICE.enums.CoachType;
import com.booking.TRAIN_SERVICE.enums.SeatCategory;
import com.booking.TRAIN_SERVICE.enums.SeatType;
import com.booking.TRAIN_SERVICE.model.*;
import com.booking.TRAIN_SERVICE.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class TrainServiceApplication implements CommandLineRunner {

	@Autowired
	private TrainRepository trainRepository;

	@Autowired
	private CoachRepository coachRepository;

	@Autowired
	private SeatRepository seatRepository;

	@Autowired
	private StationRepository stationRepository;

	@Autowired
	private TrainStationRepository trainStationMappingRepository;

	public static void main(String[] args) {
		SpringApplication.run(TrainServiceApplication.class, args);
	}


	@Override
	@Transactional
	public void run(String... args) {
		addDemoData();
	}

	private void addDemoData() {

		// Step 1: Add Stations
		Station station1 = Station.builder()
				.id(UUID.randomUUID().toString())
				.stationCode("RNC")
				.stationName("Ranchi Junction")
				.city("Ranchi")
				.state("Jharkhand")
				.build();
		Station station2 = Station.builder()
				.id(UUID.randomUUID().toString())
				.stationCode("BKSC")
				.stationName("Bokaro Junction")
				.city("Bokaro")
				.state("Jharkhand")
				.build();
		Station station3 = Station.builder()
				.id(UUID.randomUUID().toString())
				.stationCode("KQR")
				.stationName("Koderma Junction")
				.city("Koderma")
				.state("Jharkhand")
				.build();
		Station station4 = Station.builder()
				.id(UUID.randomUUID().toString())
				.stationCode("GAYA")
				.stationName("Gaya Junction")
				.city("Gaya")
				.state("Bihar")
				.build();

		Station station5 = Station.builder()
				.id(UUID.randomUUID().toString())
				.stationCode("DOS")
				.stationName("Dehri Onsone Junction")
				.city("Dehri")
				.state("Bihar")
				.build();

		Station station6 = Station.builder()
				.id(UUID.randomUUID().toString())
				.stationCode("DDU")
				.stationName("Pt. Deen Dayal Upadhyaya Junction")
				.city("Mughalsarai")
				.state("Uttar Pradesh")
				.build();

		Station station7 = Station.builder()
				.id(UUID.randomUUID().toString())
				.stationCode("CNB")
				.stationName("Kanpur Junction")
				.city("Kanpur")
				.state("Uttar Pradesh")
				.build();


		Station station8 = Station.builder()
				.id(UUID.randomUUID().toString())
				.stationCode("NDLS")
				.stationName("New Delhi Junction")
				.city("New Delhi")
				.state("Delhi")
				.build();

		stationRepository.saveAll(List.of(station1, station2,station3,station4,station5,station6,station7,station8));

		// Step 2: Add Train
		Train train = Train.builder()
				.id(UUID.randomUUID().toString())
				.trainNumber("20839")
				.trainName("New Delhi Rajdhani Express")
				.totalCoaches(6)
				.build();
		Train savedTrain = trainRepository.save(train);


		// Step 3: Add Coaches
		Coach coach = Coach.builder()
				.id(UUID.randomUUID().toString())
				.coachNumber("H1")
				.coachType(CoachType.FIRST_AC)
				.train(savedTrain)
				.build();

		Coach coach1 = Coach.builder()
				.id(UUID.randomUUID().toString())
				.coachNumber("A1")
				.coachType(CoachType.SECOND_AC)
				.train(savedTrain)
				.build();

		Coach coach2 = Coach.builder()
				.id(UUID.randomUUID().toString())
				.coachNumber("A2")
				.coachType(CoachType.SECOND_AC)
				.train(savedTrain)
				.build();

		Coach coach3 = Coach.builder()
				.id(UUID.randomUUID().toString())
				.coachNumber("B1")
				.coachType(CoachType.THIRD_AC)
				.train(savedTrain)
				.build();

		Coach coach4 = Coach.builder()
				.id(UUID.randomUUID().toString())
				.coachNumber("B2")
				.coachType(CoachType.THIRD_AC)
				.train(savedTrain)
				.build();

		Coach coach5 = Coach.builder()
				.id(UUID.randomUUID().toString())
				.coachNumber("B3")
				.coachType(CoachType.THIRD_AC)
				.train(savedTrain)
				.build();

		Coach coach6 = Coach.builder()
				.id(UUID.randomUUID().toString())
				.coachNumber("B4")
				.coachType(CoachType.THIRD_AC)
				.train(savedTrain)
				.build();
		List<Coach> coaches = coachRepository.saveAll(List.of(coach, coach1, coach2, coach3, coach4, coach5, coach6));
//
//		// Step 4: Add Seats
		Seat seat1 = Seat.builder()
				.id(UUID.randomUUID().toString())
				.seatNumber("1")
				.coach(coach)
				.seatType(SeatType.LOWER)
				.seatCategory(SeatCategory.NORMAL)
				.isBooked(false)
				.normalFare(4000)
				.tatkalFare(4500)
				.premiumTatkalFare(4800)
				.isTatkalAvailable(true)
				.build();
		Seat seat2 = Seat.builder()
				.id(UUID.randomUUID().toString())
				.seatNumber("2")
				.coach(coach)
				.seatType(SeatType.UPPER)
				.seatCategory(SeatCategory.NORMAL)
				.isBooked(false)
				.normalFare(4000)
				.tatkalFare(4500)
				.premiumTatkalFare(4800)
				.isTatkalAvailable(true)
				.build();

		Seat seat3 = Seat.builder()
				.id(UUID.randomUUID().toString())
				.seatNumber("3")
				.coach(coach)
				.seatType(SeatType.LOWER)
				.seatCategory(SeatCategory.TATKAL)
				.isBooked(false)
				.normalFare(4000)
				.tatkalFare(4500)
				.premiumTatkalFare(4800)
				.isTatkalAvailable(true)
				.build();

		Seat seat4 = Seat.builder()
				.id(UUID.randomUUID().toString())
				.seatNumber("4")
				.coach(coach)
				.seatType(SeatType.UPPER)
				.seatCategory(SeatCategory.PERMIUM_TATKAL)
				.isBooked(false)
				.normalFare(4000)
				.tatkalFare(4500)
				.premiumTatkalFare(4800)
				.isTatkalAvailable(true)
				.build();

		Seat seat5 = Seat.builder()
				.id(UUID.randomUUID().toString())
				.seatNumber("5")
				.coach(coach)
				.seatType(SeatType.LOWER)
				.seatCategory(SeatCategory.TATKAL)
				.isBooked(false)
				.normalFare(4000)
				.tatkalFare(4500)
				.premiumTatkalFare(4800)
				.isTatkalAvailable(true)
				.build();

		seatRepository.save(seat1);
		seatRepository.save(seat2);
		seatRepository.save(seat3);
		seatRepository.save(seat4);
		seatRepository.save(seat5);
//
//		// Step 5: Add Train Station Mapping (Stops)
//
		List<String> list = Arrays.asList("Mon","Tue","Wed","Thu","Fri","Sat");
		TrainStationMapping stationMapping1 = TrainStationMapping.builder()
				.id(UUID.randomUUID().toString())
				.train(train)
				.station(station1)
				.arrivalTime(LocalTime.of(18, 0))
				.departureTime(LocalTime.of(18, 0))
				.runningOn(list)
				.build();

		TrainStationMapping stationMapping2 = TrainStationMapping.builder()
				.id(UUID.randomUUID().toString())
				.train(train)
				.station(station2)
				.arrivalTime(LocalTime.of(18, 0))
				.departureTime(LocalTime.of(18, 0))
				.runningOn(list)
				.build();
		TrainStationMapping stationMapping3 = TrainStationMapping.builder()
				.id(UUID.randomUUID().toString())
				.train(train)
				.station(station3)
				.arrivalTime(LocalTime.of(18, 0))
				.departureTime(LocalTime.of(18, 0))
				.runningOn(list)
				.build();
		TrainStationMapping stationMapping4 = TrainStationMapping.builder()
				.id(UUID.randomUUID().toString())
				.train(train)
				.station(station4)
				.arrivalTime(LocalTime.of(18, 0))
				.departureTime(LocalTime.of(18, 0))
				.runningOn(list)
				.build();
		TrainStationMapping stationMapping5 = TrainStationMapping.builder()
				.id(UUID.randomUUID().toString())
				.train(train)
				.station(station5)
				.arrivalTime(LocalTime.of(18, 0))
				.departureTime(LocalTime.of(18, 0))
				.runningOn(list)
				.build();
		TrainStationMapping stationMapping6 = TrainStationMapping.builder()
				.id(UUID.randomUUID().toString())
				.train(train)
				.station(station6)
				.arrivalTime(LocalTime.of(18, 0))
				.departureTime(LocalTime.of(18, 0))
				.runningOn(list)
				.build();
		TrainStationMapping stationMapping7 = TrainStationMapping.builder()
				.id(UUID.randomUUID().toString())
				.train(train)
				.station(station7)
				.arrivalTime(LocalTime.of(18, 0))
				.departureTime(LocalTime.of(18, 0))
				.runningOn(list)
				.build();
		TrainStationMapping stationMapping8 = TrainStationMapping.builder()
				.id(UUID.randomUUID().toString())
				.train(train)
				.station(station8)
				.arrivalTime(LocalTime.of(18, 0))
				.departureTime(LocalTime.of(18, 0))
				.runningOn(list)
				.build();

		trainStationMappingRepository.saveAll(List.of(stationMapping1,stationMapping2,stationMapping3,stationMapping4,stationMapping5,stationMapping6,stationMapping7,stationMapping8));

		System.out.println("🚆 Demo Data Added: Rajdhani Express from Ranchi to New Delhi ✅");
	}

}
