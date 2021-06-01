package com.esp8266.webserver.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author Aminul Hoque
 * @since 2021-06-01
 */

/**
 * https://stackoverflow.com/questions/3126769/uniqueconstraint-annotation-in-java
 * class level annotation
 *
 * @Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"deviceName"})})
 */
@Entity
@Table(name = "devices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties("id")
public class DeviceEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    @Column(unique = true)
    private String deviceName;

    private String deviceType;

    private DeviceStatus status;

    @Column(columnDefinition = "timestamp not null default now()")
    private LocalDateTime registerOn;

    /**
     * Device output switches
     */
    private String switch1;
    private String switch2;
    private String switch3;
    private String switch4;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TemperatureEntity> temData;

    /**
     * This is for registering any device for 1st time
     */
    public DeviceEntity(String deviceName, String deviceType) {
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.registerOn = LocalDateTime.now();
        this.status = DeviceStatus.ACTIVE;
        this.switch1 = "OFF";
        this.switch2 = "OFF";
        this.switch3 = "OFF";
        this.switch4 = "OFF";
    }

    public DeviceEntity(String switch1, String switch2, String switch3, String switch4) {
        this.switch1 = switch1;
        this.switch2 = switch2;
        this.switch3 = switch3;
        this.switch4 = switch4;
    }
}
