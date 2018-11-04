package ru.klemtsov.watering.restserver.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ru.klemtsov.watering.restserver.model.ValueKind;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ValueKindMapper implements RowMapper<ValueKind> {
    public static final String SELECT_SQL = "select * from value_kinds";

    @Nullable
    @Override
    public ValueKind mapRow(ResultSet rs, int i) throws SQLException {
        return new ValueKind(rs.getInt("id"), rs.getString("code"),
                rs.getString("name"), rs.getString("description"));
    }
}
