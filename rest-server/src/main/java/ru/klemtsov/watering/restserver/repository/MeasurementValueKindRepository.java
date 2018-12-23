package ru.klemtsov.watering.restserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.klemtsov.watering.restserver.model.MeasurementValueKind;

public interface MeasurementValueKindRepository extends JpaRepository<MeasurementValueKind, Integer> {
    MeasurementValueKind findByCode(String code);
}
