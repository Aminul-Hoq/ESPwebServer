package com.esp8266.webserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Aminul Hoque
 * @since 2021-05-30
 */

@Entity
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureEntity extends AbstractEntityStringId<TemperatureEntity>{

    @Column(nullable = false, columnDefinition = "date default now()")
    private LocalDate entryDate;

    @Column(nullable = false, columnDefinition = "time default now()")
    private LocalTime entryTime;

    @Column(nullable = false, columnDefinition = "bigint default 0.0")
    private double temperature;

    @Column(nullable = false, columnDefinition = "bigint default 0.0")
    private double humidity;

    public TemperatureEntity(double temperature, double humidity){
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @PrePersist
    private void setDate(){
        this.entryDate = LocalDate.now();
        this.entryTime = LocalTime.now();
    }
}
