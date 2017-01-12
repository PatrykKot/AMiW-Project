package pl.poznan.put.random.web;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("random")
public class RandomWeb {
	@GetMapping("values")
	public Map<String, List<Integer>> getValues(@RequestParam Integer length) {
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		List<Integer> list = new LinkedList<Integer>();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			list.add(random.nextInt());
		}
		
		map.put("random", list);

		return map;
	}
}
