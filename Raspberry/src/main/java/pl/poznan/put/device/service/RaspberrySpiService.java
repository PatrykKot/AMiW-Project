package pl.poznan.put.device.service;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;

@Service
public class RaspberrySpiService {
	private static final Logger log = Logger.getLogger(RaspberrySpiService.class);

	public String writeText(String text, Charset charset, SpiChannel channel) throws IOException {
		SpiDevice device = SpiFactory.getInstance(channel);
		log.debug("SPI write: " + text);
		return device.write(text, charset);
	}

	public byte[] writeHex(String text, SpiChannel channel) throws IOException {
		byte[] buffer = DatatypeConverter.parseHexBinary(text);
		SpiDevice device = SpiFactory.getInstance(channel);

		log.debug("SPI write: " + text);
		return device.write(buffer);
	}

	public byte writeByte(byte data, SpiChannel channel) throws IOException {
		SpiDevice device = SpiFactory.getInstance(channel);
		return device.write(data)[0];
	}
	
	public byte[] readBytes(int length, SpiChannel channel) throws IOException
	{
		SpiDevice device = SpiFactory.getInstance(channel);
		byte[] buffer = new byte[length];
		return device.write(buffer);
	}

}
