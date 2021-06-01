package com.esp8266.webserver.services;

import com.esp8266.webserver.entities.DeviceEntity;
import com.esp8266.webserver.entities.TemperatureEntity;
import com.esp8266.webserver.repos.DeviceRepo;
import com.esp8266.webserver.repos.TemperatureReaderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Aminul Hoque
 * @since 2021-06-01
 */

@Service
public class DeviceRegisterService {

    @Autowired
    private DeviceRepo devRepo;
    @Autowired
    private TemperatureReaderRepo tempRepo;

    public String registerDevice(String deviceName, String deviceType) {
        DeviceEntity entity = devRepo.save(new DeviceEntity(deviceName, deviceType));
        return entity.getId();
    }

    public DeviceEntity searchDeviceByName(String name) {
        return devRepo.findDeviceEntityByDeviceName(name);
    }

    public DeviceEntity searchDeviceById(String id) {
        return devRepo.findById(id).get();
    }

    public ResponseEntity addTempData(String id, TemperatureEntity entity) {
        DeviceEntity device = devRepo.findById(id).get();
        if (device.getDeviceName() == null) {
            return ResponseEntity.ok("No device found by id");
        }
        return ResponseEntity.ok(tempRepo.save(entity.setDevice(device)));
    }

    public List<TemperatureEntity> getTempByIdAndDayBetween(String id, LocalDate startDate, LocalDate endDate) {
        return devRepo.findByIdAndDayBetween(id, startDate, endDate);
    }

    public List<TemperatureEntity> getTempByIdAndDay(String id, LocalDate date) {
        return devRepo.findByIdAndDate(id, date);
    }

}
