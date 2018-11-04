package ru.klemtsov.watering.restserver.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ru.klemtsov.watering.restserver.model.SettingItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SettingItemMapper implements RowMapper<SettingItem> {

    public static final String BASE_SQL = "select * from settings";
    public static final String INS_SQL = "INSERT into settings(setting_type, code, value)\n" +
            "values(?, ?, ?)\n" +
            "ON DUPLICATE KEY UPDATE\n" +
            "setting_type=VALUES(setting_type),code=VALUES(code), value=VALUES(value)";

    @Nullable
    @Override
    public SettingItem mapRow(ResultSet rs, int i) throws SQLException {
        return new SettingItem(rs.getInt("id"),
                rs.getString("setting_type"),
                rs.getString("code"),
                rs.getString("value"));
    }
}
