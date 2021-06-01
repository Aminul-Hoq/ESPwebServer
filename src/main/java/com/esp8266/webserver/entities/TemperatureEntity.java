package com.esp8266.webserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Aminul Hoque
 * @since 2021-05-30
 */

@Entity
@Data
@Table(name = "temeratures")
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("device")
public class TemperatureEntity extends AbstractEntityStringId<TemperatureEntity> {

    @Column(nullable = false, columnDefinition = "date default now()")
    private LocalDate entryDate;

    @Column(nullable = false, columnDefinition = "time default now()")
    private LocalTime entryTime;

    @Column(nullable = false, columnDefinition = "bigint default 0.0")
    private double temperature;

    @Column(nullable = false, columnDefinition = "bigint default 0.0")
    private double humidity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "devices_id", nullable = false)
    private DeviceEntity device;

//    public TemperatureEntity(double temperature, double humidity) {
//        this.temperature = temperature;
//        this.humidity = humidity;
//    }
//
//    public TemperatureEntity(double temperature, double humidity, DeviceEntity device) {
//        this.temperature = temperature;
//        this.humidity = humidity;
//        this.device = device;
//    }

    @PrePersist
    private void setDate() {
        this.entryDate = LocalDate.now();
        this.entryTime = LocalTime.now();
    }
}
