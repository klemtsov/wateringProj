package ru.klemtsov.watering.restserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.klemtsov.watering.restserver.jsonUtils.JsonDateDeserializer;
import ru.klemtsov.watering.restserver.jsonUtils.JsonDateSerializer;

import java.time.LocalDateTime;
import java.util.Date;

public class StatisticByPeriod {
    private String valueTypeCode;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    //@JsonDeserialize(using = JsonDateDeserializer.class)
    //@JsonSerialize(using = JsonDateSerializer.class)
    private Date dateBegin;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    //@JsonDeserialize(using = JsonDateDeserializer.class)
    //@JsonSerialize(using = JsonDateSerializer.class)
    private Date dateEnd;

    public String getValueTypeCode() {
        return valueTypeCode;
    }

    public void setValueTypeCode(String valueTypeCode) {
        this.valueTypeCode = valueTypeCode;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

}
