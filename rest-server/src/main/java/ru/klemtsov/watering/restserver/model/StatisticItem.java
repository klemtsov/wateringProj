package ru.klemtsov.watering.restserver.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StatisticItem {
    private Integer id;
    private ValueKind valueKind;
    private LocalDateTime valueDate;
    private BigDecimal value;

    public StatisticItem() {
    }

    public StatisticItem(Integer id, ValueKind valueKind, LocalDateTime valueDate, BigDecimal value) {
        this.id = id;
        this.valueKind = valueKind;
        this.valueDate = valueDate;
        this.value = value;
    }

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

    public LocalDateTime getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDateTime valueDate) {
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
                ", valueDate=" + valueDate +
                ", value=" + value +
                '}';
    }
}
