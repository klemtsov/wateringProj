package ru.klemtsov.watering.restserver.repository;

import org.springframework.data.repository.CrudRepository;
import ru.klemtsov.watering.restserver.model.DeviceStatus;

public interface DeviceStatusRepository extends CrudRepository<DeviceStatus, Integer> {

    DeviceStatus findByCode(String code);
}
