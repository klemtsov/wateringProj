package ru.klemtsov.watering.restserver.repository;

import org.springframework.data.repository.CrudRepository;
import ru.klemtsov.watering.restserver.model.DeviceType;

public interface DeviceTypeRepository extends CrudRepository<DeviceType, Integer> {
}
