package ru.klemtsov.watering.restserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.klemtsov.watering.restserver.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
