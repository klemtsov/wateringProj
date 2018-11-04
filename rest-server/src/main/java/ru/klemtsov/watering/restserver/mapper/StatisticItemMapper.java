package ru.klemtsov.watering.restserver.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ru.klemtsov.watering.restserver.dao.ValueKindDAO;
import ru.klemtsov.watering.restserver.model.StatisticItem;
import ru.klemtsov.watering.restserver.model.ValueKind;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.*;
import static ru.klemtsov.watering.restserver.Constants.DATETIME_FORMAT;

public class StatisticItemMapper implements RowMapper<StatisticItem> {
    public static final String BASE_SQL = "select * from statistics ";
    public static final String INS_SQL = "insert into statistics (value_kind, value_date, value) values(?, ?, ?)";


    private ValueKindDAO valueKindDAO;
    private final ThreadLocal<DateTimeFormatter> dateTimeFormatter =
            ThreadLocal.withInitial(() -> ofPattern(DATETIME_FORMAT));


    public StatisticItemMapper(ValueKindDAO valueKindDAO) {
        this.valueKindDAO = valueKindDAO;
    }

    @Nullable
    @Override
    public StatisticItem mapRow(ResultSet rs, int i) throws SQLException {
        ValueKind valueKind = valueKindDAO.getValueKinds().get(rs.getInt("value_kind"));
        LocalDateTime dateTime = LocalDateTime.parse(rs.getString("value_date"), dateTimeFormatter.get());
        return new StatisticItem(rs.getInt("id"),
                valueKind, dateTime, rs.getBigDecimal("value"));
    }
}
