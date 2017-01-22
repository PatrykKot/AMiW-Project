package pl.poznan.put.device.service;

import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;

@Service
public class RaspberrySpiService {

	private static SpiDevice device;

	@PostConstruct
	void init() {
		try
		{
		device = SpiFactory.getInstance(SpiChannel.CS0);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
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
