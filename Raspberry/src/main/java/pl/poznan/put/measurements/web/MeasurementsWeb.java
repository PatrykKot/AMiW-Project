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
		List<MeasurementDomain> newList;
		synchronized (all) {
			newList = new LinkedList<MeasurementDomain>(all);
		}

		while (newList.size() > length) {
			newList.remove(0);
		}

		List<Integer> listX = new LinkedList<Integer>();
		List<Integer> listY = new LinkedList<Integer>();

		for (int i = 0; i < newList.size(); i++) {
			int x = newList.get(i).getValueX().intValue();
			int y = newList.get(i).getValueY().intValue();
			if (x < 0 || y < 0 || x > 479 || y > 256)
				continue;
			listX.add(newList.get(i).getValueX().intValue());
			listY.add(newList.get(i).getValueY().intValue());
		}

		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		map.put("X", listX);
		map.put("Y", listY);

		return map;
	}

	@GetMapping("getLastTest")
	public Map<String, List<Integer>> getLastTest(@RequestParam Integer length) {
		/*
		 * List<Integer> listX = new LinkedList<Integer>(); List<Integer> listY
		 * = new LinkedList<Integer>();
		 * 
		 * Random random = new Random();
		 * 
		 * for (int i = 0; i < length; i++) { listX.add(random.nextInt((479)));
		 * listY.add(random.nextInt((256))); }
		 * 
		 * Map<String, List<Integer>> map = new HashMap<String,
		 * List<Integer>>(); map.put("X", listX); map.put("Y", listY);
		 * 
		 * return map;
		 */
		return getLast(length);
	}
}
