package ru.klemtsov.watering.restserver.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.klemtsov.watering.restserver.exception.WateringException;
import ru.klemtsov.watering.restserver.mapper.SettingItemMapper;
import ru.klemtsov.watering.restserver.model.SettingItem;
import ru.klemtsov.watering.restserver.model.Settings;

import javax.sql.DataSource;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.klemtsov.watering.restserver.mapper.SettingItemMapper.INS_SQL;

@Repository
@Transactional
public class SettingsDAO extends JdbcDaoSupport {
    private static final String ID = "id";
    private static final String IP = "IP";
    private static final String SERVER_IP = "SERVER_IP";
    private static final String START_WATERING_BELOW_HUMIDITY = "START_WATERING_BELOW_HUMIDITY";
    private static final String TIME_WATERING_IN_SEC = "TIME_WATERING_IN_SEC";
    private static final String WATERING_PERIOD = "WATERING_PERIOD";
    private static final String WATERING_START_AT = "WATERING_START_AT";
    private static final String MAIN_TYPE = "MAIN";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    public SettingsDAO(@Qualifier("dataSource") DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Settings getSettings() {
        SettingItemMapper mapper = new SettingItemMapper();
        List<SettingItem> items = this.getJdbcTemplate().query(SettingItemMapper.BASE_SQL, (Object[]) null, mapper);
        Settings settings = new Settings();
        settings.setIp(getStringValue(items, IP));
        settings.setServerIp(getStringValue(items, SERVER_IP));
        settings.setStartWateringBelowHumidity(getIntValue(items, START_WATERING_BELOW_HUMIDITY));
        settings.setTimeWateringInSec(getIntValue(items, TIME_WATERING_IN_SEC));
        settings.setWateringPeriod(getIntValue(items, WATERING_PERIOD));
        settings.setWateringStartAt(getTimeValue(items, WATERING_START_AT));

        return settings;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = WateringException.class)
    public void setSettings(Settings settings) throws WateringException {
        updateOrInsert(IP, settings.getIp());
        updateOrInsert(SERVER_IP, settings.getServerIp());
        updateOrInsert(START_WATERING_BELOW_HUMIDITY, settings.getStartWateringBelowHumidity().toString());
        updateOrInsert(TIME_WATERING_IN_SEC, settings.getTimeWateringInSec().toString());
        updateOrInsert(WATERING_PERIOD, settings.getWateringPeriod().toString());

        updateOrInsert(WATERING_START_AT, settings.getWateringStartAt() != null
                ? settings.getWateringStartAt().format(formatter)
                : "");
    }

    @Transactional(propagation = Propagation.MANDATORY)
    protected void updateOrInsert(String code, String value) {
        Object[] params = new Object[]{MAIN_TYPE, code, value};
        int i = this.getJdbcTemplate().update(INS_SQL, params);
    }

    private String getStringValue(List<SettingItem> items, String key) {
        if (CollectionUtils.isEmpty(items) || StringUtils.isEmpty(key)) {
            return "";
        }
        return items.stream().filter(i -> key.equalsIgnoreCase(i.getKey()))
                .findFirst().orElse(new SettingItem()).getValue();
    }

    private Integer getIntValue(List<SettingItem> items, String key) {
        String value = getStringValue(items, key);
        return StringUtils.isEmpty(value) ? 0 : Integer.valueOf(value);
    }

    private LocalTime getTimeValue(List<SettingItem> items, String key) {
        String value = getStringValue(items, key);
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        try {
            return LocalTime.parse(value, formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
