package ru.klemtsov.watering.restserver.jsonUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static ru.klemtsov.watering.restserver.Constants.DATETIME_FORMAT;
import static ru.klemtsov.watering.restserver.Constants.DATETIME_FORMAT_WTZ;

public class JsonDateSerializer extends JsonSerializer <Date>{
    private SimpleDateFormat format = new SimpleDateFormat(DATETIME_FORMAT_WTZ);


    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider arg2)
            throws IOException {
        final String dateString = format.format(date);
        generator.writeString(dateString);
    }
}
