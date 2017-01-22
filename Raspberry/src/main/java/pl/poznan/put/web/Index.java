package pl.poznan.put.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Index {
	@GetMapping("/")
	public String getIndex() {
		return "redirect:/index.html";
	}
}
