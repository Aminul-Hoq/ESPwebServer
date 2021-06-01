package com.esp8266.webserver.repos;

import com.esp8266.webserver.entities.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Aminul Hoque
 * @since 2021-05-31
 */

public interface TemperatureReaderRepo extends JpaRepository<TemperatureEntity, Long> {

    List<TemperatureEntity> findByEntryDateBetween(LocalDate entryDate, LocalDate entryDate2);

    List<TemperatureEntity> findByEntryDate(LocalDate entryDate);

}
