package com.example.moonlighthotel.unit;

import com.example.moonlighthotel.model.RoomReservation;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.repositories.RoomReservationRepository;
import com.example.moonlighthotel.service.RoomService;
import com.example.moonlighthotel.service.UserService;
import com.example.moonlighthotel.service.impl.RoomReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RoomReservationServiceTest {

    @Mock
    private RoomReservationRepository roomReservationRepository;

    @Mock
    private UserService userService;

    private RoomService roomService;

    @Mock
    private RoomReservationServiceImpl roomReservationService;

    @BeforeEach
    public void setUp() {
        roomReservationService = new RoomReservationServiceImpl(roomReservationRepository, userService, roomService);
    }

    @Test
    public void verifySave() {

        RoomReservation roomReservation = RoomReservation.builder().build();
        roomReservationRepository.save(roomReservation);
        verify(roomReservationRepository, times(1)).save(roomReservation);
    }

    @Test
    public void verifyGetByUser() {
        RoomReservation roomReservation = RoomReservation.builder().build();
        User user = User.builder().build();
        roomReservation.setUser(user);
        roomReservationRepository.findAll();
        // verify(roomReservationRepository,times(1)).findAll(Set.of(roomReservation));
    }

    @Test
    public void verifyGetAll() {
        roomReservationRepository.findAll();
        verify(roomReservationRepository, times(1)).findAll();
    }

    @Test
    public void verifyFindRoomByPeriodAndPeople() {
        Instant startDate = Instant.now();
        Instant endDate = Instant.now();
        int adults = 2;
        int kids = 3;
        roomReservationRepository.findRoomByPeriodAndPeople(startDate, endDate, (adults + kids));
        verify(roomReservationRepository, times(1))
                .findRoomByPeriodAndPeople(startDate, endDate, (adults + kids));
    }

    @Test
    public void verifyFindReservationByIdAndUserIdSuccess() {

        Long uid = 2L;
        Long rid = 10L;

        com.example.moonlighthotel.model.User userById = userService.findUserById(uid);
        verify(userService, times(1)).findUserById(uid);

        Optional<RoomReservation> roomReservation = roomReservationRepository.findById(rid);
        verify(roomReservationRepository, times(1)).findById(rid);

        roomReservation = Optional.ofNullable(RoomReservation.builder().user(User.builder().id(uid).build()).build());

        Long id = roomReservation.orElseThrow().getUser().getId();

    }


}