package com.booking.TRAIN_SERVICE;

import com.booking.TRAIN_SERVICE.enums.*;
import com.booking.TRAIN_SERVICE.model.*;
import com.booking.TRAIN_SERVICE.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.time.LocalTime;
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
		// Step 1: Add 10 Stations
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
		Station station9 = Station.builder()
				.id(UUID.randomUUID().toString())
				.stationCode("BPL")
				.stationName("Bhopal Junction")
				.city("Bhopal")
				.state("Madhya Pradesh")
				.build();
		Station station10 = Station.builder()
				.id(UUID.randomUUID().toString())
				.stationCode("AGC")
				.stationName("Agra Cantt.")
				.city("Agra")
				.state("Uttar Pradesh")
				.build();

		stationRepository.saveAll(List.of(station1, station2, station3, station4, station5, station6, station7, station8, station9, station10));

		// Step 2: Add 10 Trains
		for (int i = 1; i <= 10; i++) {
			Train train = Train.builder()
					.id(UUID.randomUUID().toString())
					.trainNumber("2083" + i)
					.trainName("Train " + i)
					.totalCoaches(6)
					.trainType(TrainType.RAJDHANI) // Example, you can change this as needed
					.build();
			trainRepository.save(train);
		}

		// Step 3: Add Coaches for each Train
		List<Train> trains = trainRepository.findAll();
		for (Train train : trains) {
			for (int i = 1; i <= 6; i++) {
				Coach coach = Coach.builder()
						.id(UUID.randomUUID().toString())
						.coachNumber("Coach-" + i)
						.coachType(i % 2 == 0 ? CoachType.FIRST_AC : CoachType.SECOND_AC) // Alternating coach types
						.train(train)
						.totalAvailableTatkal(55)
						.totalAvailableNormal(200)
						.build();
				coachRepository.save(coach);
			}
		}

		// Step 4: Add Seats for Coaches
		List<Coach> coaches = coachRepository.findAll();
		for (Coach coach : coaches) {
			for (int i = 1; i <= 5; i++) {
				Seat seat = Seat.builder()
						.id(UUID.randomUUID().toString())
						.seatNumber(String.valueOf(i))
						.coach(coach)
						.seatType(i % 2 == 0 ? SeatType.UPPER : SeatType.LOWER) // Alternating seat types
						.seatCategory(SeatCategory.GENERAL)
						.isBooked(false)
						.generalWaitlist(0)
						.tatkalWaitlist(0)
						.normalFare(4000)
						.seatStatus(SeatStatus.AVAILABLE)
						.tatkalFare(4500)
						.premiumTatkalFare(4800)
						.isTatkalAvailable(true)
						.build();
				seatRepository.save(seat);
			}
		}

		// Step 5: Add Train Station Mappings (Stops)
		List<Train> allTrains = trainRepository.findAll();
		List<Station> allStations = stationRepository.findAll();
		List<String> list = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri");

		for (Train train : allTrains) {
			for (Station station : allStations) {
				TrainStationMapping stationMapping = TrainStationMapping.builder()
						.id(UUID.randomUUID().toString())
						.train(train)
						.station(station)
						.arrivalTime(LocalTime.of(18, 0))
						.departureTime(LocalTime.of(18, 30)) // Adding 30 minutes for departure
						.runningOn(list)
						.build();
				trainStationMappingRepository.save(stationMapping);
			}
		}

		System.out.println("🚆 Demo Data Added: 10 trains with stations, coaches, seats, and mappings ✅");
	}


}
