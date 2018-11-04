package ru.klemtsov.watering.restserver.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.klemtsov.watering.restserver.mapper.ValueKindMapper;
import ru.klemtsov.watering.restserver.model.ValueKind;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.klemtsov.watering.restserver.mapper.ValueKindMapper.SELECT_SQL;

@Repository
@Transactional
public class ValueKindDAO extends JdbcDaoSupport {

    @Autowired
    public ValueKindDAO(@Qualifier("dataSource") DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Map<Integer, ValueKind> getValueKinds() {
        ValueKindMapper mapper = new ValueKindMapper();
        List<ValueKind> valueKinds = this.getJdbcTemplate().query(SELECT_SQL, mapper);
        return valueKinds.stream().collect(Collectors.toMap(ValueKind::getId, i -> i));
    }

    public ValueKind getValueKindByCode(String code) {
        ValueKind result =
         getValueKinds().values().stream()
                .filter(i-> i.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
        return result;
    }
}
