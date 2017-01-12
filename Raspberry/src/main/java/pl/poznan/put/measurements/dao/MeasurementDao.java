package pl.poznan.put.measurements.dao;

import org.springframework.data.repository.CrudRepository;

import pl.poznan.put.measurements.domain.MeasurementDomain;

public interface MeasurementDao extends CrudRepository<MeasurementDomain, Long> {

}
