package pl.poznan.put.device.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pi4j.io.serial.Baud;
import com.pi4j.io.serial.DataBits;
import com.pi4j.io.serial.FlowControl;
import com.pi4j.io.serial.Parity;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialConfig;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataEventListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPort;
import com.pi4j.io.serial.StopBits;

@Service
public class RaspberryUartService {
	private static final Logger log = Logger.getLogger(RaspberryUartService.class);

	private static Serial device = SerialFactory.createInstance();

	private static SerialConfig config;

	private List<Byte> receivedBuffer = new LinkedList<Byte>();

	@PostConstruct
	void listenerInit() {
		try {
			config = new SerialConfig();

			config.baud(Baud._9600);
			config.dataBits(DataBits._8);
			config.flowControl(FlowControl.NONE);
			config.parity(Parity.NONE);
			config.stopBits(StopBits._1);
			config.device(SerialPort.getDefaultPort());

			device.addListener(new SerialDataEventListener() {

				@Override
				public void dataReceived(SerialDataEvent event) {
					try {
						byte[] bytes = event.getBytes();
						receivedBuffer.addAll(Arrays.asList(ArrayUtils.toObject(bytes)));
						log.debug("Uart data:" + new String(bytes));
					} catch (Exception ex) {
						log.error(ex.getMessage());
					}
				}
			});

			device.open(config);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
	}

	public void writeText(String text, Charset charset) throws IOException {
		log.debug("Serial write: " + text);
		device.write(charset, text);
	}

	public void writeHex(String text) throws IllegalStateException, IOException {
		byte[] buffer = DatatypeConverter.parseHexBinary(text);

		log.debug("Serial write: " + text);
		device.write(buffer);
	}

	public void writeByte(byte data) throws IOException {
		device.write(data);
	}

	public List<Byte> getReceivedBuffer() {
		return receivedBuffer;
	}

	public void clearBuffer() {
		receivedBuffer.clear();
	}

}
