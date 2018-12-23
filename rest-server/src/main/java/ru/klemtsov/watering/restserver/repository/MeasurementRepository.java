package ru.klemtsov.watering.restserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.klemtsov.watering.restserver.model.Measurement;
import ru.klemtsov.watering.restserver.model.MeasurementValueKind;

import java.util.Date;
import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findMeasurementByValueKindAndValueDateBetween(
            MeasurementValueKind valueKind, Date dateBeg, Date dateEnd);
}
