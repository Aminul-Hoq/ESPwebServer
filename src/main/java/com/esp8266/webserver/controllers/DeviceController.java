package com.esp8266.webserver.controllers;

import com.esp8266.webserver.entities.DeviceEntity;
import com.esp8266.webserver.entities.TemperatureEntity;
import com.esp8266.webserver.services.DeviceRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aminul Hoque
 * @since 2021-06-01
 */

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    DeviceRegisterService service;

    @PostMapping("/add")
    public String registerDevice(@RequestParam("name") String deviceName,
                                 @RequestParam("type") String deviceType) {
        return service.registerDevice(deviceName, deviceType);
    }

    @GetMapping("/search/name/{name}")
    public DeviceEntity searchDeviceByName(@PathVariable String name) {
        return service.searchDeviceByName(name);
    }

    @GetMapping("/search/id/{id}")
    public DeviceEntity searchDeviceById(@PathVariable String id) {
        return service.searchDeviceById(id);
    }

    @GetMapping("/get/{id}/temp")
    public List<TemperatureEntity> getTempData(@PathVariable String id) {
        return service.searchDeviceById(id).getTemData();
    }

    @PostMapping("/add/{id}/temp")
    public ResponseEntity addTempData(@RequestBody TemperatureEntity entity, @PathVariable("id") String id) {
        return service.addTempData(id, entity);
    }

    //    https://stackoverflow.com/questions/60371954/how-to-pass-local-date-in-path-variable-in-spring-boot
    @GetMapping("/get/{id}/{start-date}/{end-date}")
    public List<TemperatureEntity> tempListByDate(@PathVariable("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                  @PathVariable("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                  @PathVariable String id) {
        return service.getTempByIdAndDayBetween(id, startDate, endDate);
    }

    @GetMapping("/get/{id}/{date}/day")
    public List<TemperatureEntity> tempListByDate(
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @PathVariable String id) {
        return service.getTempByIdAndDay(id, date);
    }

}
