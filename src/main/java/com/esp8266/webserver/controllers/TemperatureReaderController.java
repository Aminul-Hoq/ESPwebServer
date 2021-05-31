package com.esp8266.webserver.controllers;

import com.esp8266.webserver.entities.TemperatureEntity;
import com.esp8266.webserver.services.TemperatureReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aminul Hoque
 * @since 2021-05-30
 */

/**
 * TODO add device registration and device mapping
 */
@RestController
@RequestMapping("/temp-reader")
public class TemperatureReaderController {

    @Autowired
    private TemperatureReaderService service;

    @PostMapping("/save")
    public ResponseEntity<TemperatureEntity> saveTemp(@RequestBody TemperatureEntity entity) {
        return ResponseEntity.ok(service.saveTemperature(entity));
    }

    //    https://stackoverflow.com/questions/60371954/how-to-pass-local-date-in-path-variable-in-spring-boot
    @GetMapping("/get/{start-date}/{end-date}")
    public List<TemperatureEntity> tempListByDate(@PathVariable("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                  @PathVariable("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return service.getTempByDayBetween(startDate, endDate);
    }

    @GetMapping("/get/day/{date}")
    public List<TemperatureEntity> tempListByDateOne(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return service.getTempByDay(date);
    }

}
