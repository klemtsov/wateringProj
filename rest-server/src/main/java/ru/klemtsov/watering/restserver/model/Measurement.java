package ru.klemtsov.watering.restserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.klemtsov.watering.restserver.jsonUtils.JsonDateDeserializer;
import ru.klemtsov.watering.restserver.jsonUtils.JsonDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "Measurement")
public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @JoinColumn(name = "device_id")
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Device.class)
    private Integer deviceId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="value_kind")
    private MeasurementValueKind valueKind;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "value_date")
    private Date valueDate;
    private BigDecimal value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", valueDate=" + valueDate.toString() +
                ", value=" + value +
                '}';
    }
}
