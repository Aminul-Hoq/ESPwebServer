package com.esp8266.webserver.services;

import com.esp8266.webserver.entities.DeviceEntity;
import com.esp8266.webserver.entities.TemperatureEntity;
import com.esp8266.webserver.repos.DeviceRepo;
import com.esp8266.webserver.repos.TemperatureReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Aminul Hoque
 * @since 2021-05-30
 */
@Service
public class TemperatureReaderService {

    @Autowired
    private DeviceRepo divRepo;
    @Autowired
    private TemperatureReaderRepo tempRepo;

    public List<TemperatureEntity> getTempByDayBetween(LocalDate startDate, LocalDate endDate) {
        return tempRepo.findByEntryDateBetween(startDate, endDate);
    }

    public List<TemperatureEntity> getTempByDay(LocalDate date) {
        return tempRepo.findByEntryDate(date);
    }


}
