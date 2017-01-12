package pl.poznan.put.measurements.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.poznan.put.measurements.domain.MeasurementDomain;
import pl.poznan.put.measurements.services.MeasurementService;

@RestController
@RequestMapping("measurement")
public class MeasurementsWeb {
	@Autowired
	MeasurementService mesService;

	@GetMapping("add")
	public void addNew(@RequestParam Long value) {
		MeasurementDomain mes = new MeasurementDomain();
		mes.setValue(value);
		mesService.add(mes);
	}

	@GetMapping("getAll")
	public List<MeasurementDomain> getAll() {
		return mesService.getAll();
	}

	@GetMapping("getLast")
	public List<MeasurementDomain> getLast(@RequestParam Integer length) {
		List<MeasurementDomain> all = mesService.getAll();
		int size = all.size();
		if (size - length <= 0)
			return all;
		else
			return all.subList(size - length, size);
	}
}
