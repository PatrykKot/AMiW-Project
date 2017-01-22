package pl.poznan.put.measurements.web;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.poznan.put.measurements.domain.MeasurementDomain;
import pl.poznan.put.measurements.services.MeasurementService;

@RestController
@RequestMapping("measurements")
public class MeasurementsWeb {
	@Autowired
	MeasurementService mesService;

	@GetMapping("getLast")
	public Map<String, List<Integer>> getLast(@RequestParam Integer length) {
		List<MeasurementDomain> all = mesService.getAll();

		while (all.size() > length) {
			all.remove(0);
		}

		List<Integer> listX = new LinkedList<Integer>();
		List<Integer> listY = new LinkedList<Integer>();

		for (int i = 0; i < all.size(); i++) {
			listX.add(all.get(i).getValueX().intValue());
			listY.add(all.get(i).getValueY().intValue());
		}

		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		map.put("X", listX);
		map.put("Y", listY);

		return map;
	}
}
