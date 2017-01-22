package pl.poznan.put.device.service;

import java.io.IOException;
import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;
import com.pi4j.system.SystemInfo;

@Service
public class RaspberrySpiService {

	private static SpiDevice device;

	private static final Logger log = Logger.getLogger(RaspberrySpiService.class);

	@PostConstruct
	void init() {
		try {
			SystemInfo.getBoardType();
		} catch (Exception ex) {
			log.error("Unsupported board type");
			return;
		}

		try {
			device = SpiFactory.getInstance(SpiChannel.CS0);
		} catch (Exception ex) {
			log.error("SPI not available");
		}
	}

	public byte writeByte(byte data) throws IOException {
		return device.write(data)[0];
	}

	public byte[] readBytes(int length) throws IOException {
		byte[] buffer = new byte[length];
		return device.write(buffer);
	}

}
