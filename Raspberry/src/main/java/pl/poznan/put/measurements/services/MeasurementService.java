package pl.poznan.put.measurements.services;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.poznan.put.measurements.dao.MeasurementDao;
import pl.poznan.put.measurements.domain.MeasurementDomain;

@Service
public class MeasurementService {
	@Autowired
	MeasurementDao mesDao;

	public List<MeasurementDomain> getAll() {
		return IterableUtils.toList(mesDao.findAll());
	}

	public void add(MeasurementDomain entry) {
		mesDao.save(entry);
	}

}
