package com.esp8266.webserver.repos;

import com.esp8266.webserver.entities.DeviceEntity;
import com.esp8266.webserver.entities.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aminul Hoque
 * @since 2021-06-01
 */
public interface DeviceRepo extends JpaRepository<DeviceEntity, String> {

    DeviceEntity findDeviceEntityByDeviceName(String deviceName);

    @Query("select t from TemperatureEntity as t inner join #{#entityName} e on " +
            "t.device = e where e.id = ?1 and t.entryDate between ?2 and ?3")
    List<TemperatureEntity> findByIdAndDayBetween(String id, LocalDate startDate, LocalDate endDate);

    @Query("select t from TemperatureEntity as t inner join #{#entityName} e on " +
            "t.device = e where e.id = ?1 and t.entryDate = ?2")
    List<TemperatureEntity> findByIdAndDate(String id, LocalDate date);
}
