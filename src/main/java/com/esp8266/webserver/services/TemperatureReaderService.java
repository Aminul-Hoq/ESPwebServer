package com.esp8266.webserver.services;

import com.esp8266.webserver.entities.TemperatureEntity;
import com.esp8266.webserver.repos.TemperatureReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TemperatureReaderRepo repo;

    public TemperatureEntity saveTemperature(TemperatureEntity entity){
        return repo.save(entity);
    }

    public List<TemperatureEntity> getTempByDayBetween(LocalDate startDate, LocalDate endDate){
        return repo.findByEntryDateBetween(startDate, endDate);
    }

    public List<TemperatureEntity> getTempByDay(LocalDate date){
        return repo.findByEntryDate(date);
    }

}
