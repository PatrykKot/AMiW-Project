package pl.poznan.put.measurements.services;

import java.io.IOException;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pi4j.io.spi.SpiChannel;

import pl.poznan.put.device.service.RaspberrySpiService;
import pl.poznan.put.device.service.RaspberryUartService;
import pl.poznan.put.measurements.dao.MeasurementDao;
import pl.poznan.put.measurements.domain.MeasurementDomain;

@Service
public class MeasurementService {
	@Autowired
	MeasurementDao mesDao;

	@Autowired
	RaspberrySpiService raspberrySpiService;

	@Autowired
	RaspberryUartService raspberryUartService;

	public static final byte X_ADDRESS = (byte) 0xaa;
	public static final byte Y_ADDRESS = (byte) 0xbb;

	public List<MeasurementDomain> getAll() {
		return IterableUtils.toList(mesDao.findAll());
	}

	public void add(MeasurementDomain entry) {
		mesDao.save(entry);
	}

	@Scheduled(fixedRate = 1000)
	public void getTouchSpiValues() throws IOException {
		raspberrySpiService.writeByte(X_ADDRESS, SpiChannel.CS0);

		byte[] readBytes = raspberrySpiService.readBytes(2, SpiChannel.CS0);
		int xVal = ((readBytes[0] & 0xff) << 8) | (readBytes[1] & 0xff);

		raspberrySpiService.writeByte(Y_ADDRESS, SpiChannel.CS0);
		int yVal = ((readBytes[0] & 0xff) << 8) | (readBytes[1] & 0xff);

		MeasurementDomain domain = new MeasurementDomain();
		domain.setValueX((long) xVal);
		domain.setValueY((long) yVal);

		mesDao.save(domain);
	}

	public int getUartValue(byte valType) throws Exception {
		raspberryUartService.clearBuffer();

		raspberryUartService.writeByte(valType);

		List<Byte> receivedBuffer = null;

		for (int i = 0; i < 10; i++) {
			receivedBuffer = raspberryUartService.getReceivedBuffer();
			if (receivedBuffer.size() > 1) {
				break;
			}

			wait(10);
		}

		if (receivedBuffer.size() < 2) {
			throw new Exception("Brak danych UART");
		}
		
		return ((receivedBuffer.get(0) & 0xff) << 8) | (receivedBuffer.get(1) & 0xff);
	}

	@Scheduled(fixedRate = 1000)
	public void getTouchUartValues() throws Exception {
		MeasurementDomain domain = new MeasurementDomain();
		domain.setValueX((long) getUartValue(X_ADDRESS));
		domain.setValueY((long) getUartValue(Y_ADDRESS));

		mesDao.save(domain);
	}

}
