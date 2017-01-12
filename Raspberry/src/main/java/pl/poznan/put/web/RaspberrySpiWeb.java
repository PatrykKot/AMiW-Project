package pl.poznan.put.web;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.spi.SpiChannel;
import pl.poznan.put.device.SpiResponse;
import pl.poznan.put.device.service.RaspberrySpiService;

@RestController
@RequestMapping(value = "spi")
public class RaspberrySpiWeb {
	@Autowired
	RaspberrySpiService spiService;

	/**
	 * Czytanie określonej ilości danych z SPI
	 * 
	 * @param channel
	 * @param length
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	@GetMapping("read/channel_{channel}")
	public Map<String, Object> spiRead(@PathVariable Integer channel, @RequestParam Integer length,
			@RequestParam(defaultValue = "UTF-8", required = false) String charset) throws IOException {
		byte[] buffer = spiService.readBytes(length, SpiChannel.getByNumber(channel));

		SpiResponse response = new SpiResponse(buffer, Charset.forName(charset));
		return response.toMap();
	}

	/**
	 * Wysyłanie tekstu po SPI
	 * 
	 * @param channel
	 * @param text
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	@GetMapping("write/text/channel_{channel}")
	public Map<String, Object> spiWriteText(@PathVariable Integer channel, @RequestParam String text,
			@RequestParam(defaultValue = "UTF-8", required = false) String charset) throws IOException {
		String responseText = spiService.writeText(text, Charset.forName(charset), SpiChannel.getByNumber(channel));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("StringResponse", responseText);
		map.put("Length", responseText.length());

		return map;
	}

	/**
	 * Wysyłanie hexowych wartości po SPI
	 * 
	 * @param channel
	 * @param hex
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	@GetMapping("write/hex/channel_{channel}")
	public Map<String, Object> spiWriteHex(@PathVariable Integer channel, @RequestParam String hex,
			@RequestParam(defaultValue = "UTF-8", required = false) String charset) throws IOException {
		byte[] buffer = spiService.writeHex(hex, SpiChannel.getByNumber(channel));

		SpiResponse response = new SpiResponse(buffer, Charset.forName(charset));
		return response.toMap();
	}
}
