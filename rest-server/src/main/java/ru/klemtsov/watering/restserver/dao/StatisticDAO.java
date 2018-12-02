package ru.klemtsov.watering.restserver.dao;

import com.sun.tools.corba.se.idl.constExpr.Times;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.klemtsov.watering.restserver.exception.WateringException;
import ru.klemtsov.watering.restserver.mapper.StatisticItemMapper;
import ru.klemtsov.watering.restserver.model.StatisticItem;
import ru.klemtsov.watering.restserver.model.ValueKind;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.klemtsov.watering.restserver.mapper.StatisticItemMapper.BASE_SQL;
import static ru.klemtsov.watering.restserver.mapper.StatisticItemMapper.INS_SQL;

@Repository
@Transactional
public class StatisticDAO extends JdbcDaoSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticDAO.class);

    @Autowired
    private ValueKindDAO valueKindDAO;

    @Autowired
    public StatisticDAO(@Qualifier("dataSource") DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<StatisticItem> getStatisticByPeriod(ValueKind valueKind, Date dateBegin,
                                                    Date dateEnd) {
        StatisticItemMapper mapper = new StatisticItemMapper(valueKindDAO);
        String query = BASE_SQL +
                " WHERE value_kind = ? AND value_date >= ? AND value_date <= ? ORDER BY value_date";
        Object[] params = new Object[]{valueKind.getId(), dateBegin, dateEnd};
        LOGGER.info("Запрос {}, параметры {}", query, params);
        List<StatisticItem> result = this.getJdbcTemplate().query(query, params, mapper);

        return result;
    }

    public List<String> setStatistic(List<StatisticItem> statisticItems) throws WateringException {

        if (CollectionUtils.isEmpty(statisticItems)) {
            return new ArrayList<>();
        }
        List<String> errors = new ArrayList<>(statisticItems.size());
        List<Object[]> params = new ArrayList<>(statisticItems.size());

        statisticItems.stream().forEach(i -> {
            Integer valueKindId = i.getValueKind().getId();
            if (valueKindId == null) {
                ValueKind valueKind = valueKindDAO.getValueKindByCode(i.getValueKind().getCode());
                if (valueKind == null) {
                    String mess = String.format("Не найден тип значения! (%s)", i);
                    LOGGER.warn(mess);
                    errors.add(mess);
                }
                valueKindId = valueKind.getId();

                Object[] param = new Object[]{valueKindId, i.getValueDate(), i.getValue()};
                params.add(param);
            }
        });
        getJdbcTemplate().batchUpdate(INS_SQL, params);
        return errors;
    }

}