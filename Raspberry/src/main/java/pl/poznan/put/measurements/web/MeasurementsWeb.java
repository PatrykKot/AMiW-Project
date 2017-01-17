package pl.poznan.put.measurements.web;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.poznan.put.measurements.services.MeasurementService;

@RestController
@RequestMapping("measurements")
public class MeasurementsWeb {
	@Autowired
	MeasurementService mesService;

	@GetMapping("getLast")
	public Map<String, List<Integer>> getLast(@RequestParam Integer length) {
		/*List<MeasurementDomain> all = mesService.getAll();
		int size = all.size();
		if (size - length <= 0)
			return all;
		else
			return all.subList(size - length, size);*/
		List<Integer> list = new LinkedList<Integer>();
		Random random = new Random();
		
		for(int i=0; i<length; i++)
		{
			list.add(random.nextInt()%100);
		}
		
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		map.put("measurements", list);
		
		return map;		
	}
}
