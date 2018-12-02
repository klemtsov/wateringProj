package ru.klemtsov.watering.restserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.klemtsov.watering.restserver.jsonUtils.JsonDateDeserializer;
import ru.klemtsov.watering.restserver.jsonUtils.JsonDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class StatisticItem {
    private Integer id;
    private ValueKind valueKind;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    //@JsonDeserialize(using = JsonDateDeserializer.class)
    //@JsonSerialize(using = JsonDateSerializer.class)
    private Date valueDate;
    private BigDecimal value;


//    public StatisticItem(Integer id, ValueKind valueKind, LocalDateTime valueDate, BigDecimal value) {
//        this.id = id;
//        this.valueKind = valueKind;
//        this.valueDate = valueDate;
//        this.value = value;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ValueKind getValueKind() {
        return valueKind;
    }

    public void setValueKind(ValueKind valueKind) {
        this.valueKind = valueKind;
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
        return "StatisticItem{" +
                "id=" + id +
                ", valueKind=" + valueKind +
                ", valueDate=" + valueDate.toString() +
                ", value=" + value +
                '}';
    }
}
