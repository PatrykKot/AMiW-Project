package pl.poznan.put.measurements.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pl.poznan.put.device.service.RaspberrySpiService;
import pl.poznan.put.measurements.domain.MeasurementDomain;

@Service
public class MeasurementService {

	private static List<MeasurementDomain> positionList = new LinkedList<MeasurementDomain>();

	@Autowired
	RaspberrySpiService raspberrySpiService;

	public static final byte X_ADDRESS = (byte) 0xaa;
	public static final byte Y_ADDRESS = (byte) 0xbb;
	public static final byte FORCE_TOUCH = (byte) 0xcc;

	public List<MeasurementDomain> getAll() {
		synchronized (positionList) {
			return positionList;
		}
	}

	@Scheduled(fixedRate = 20)
	public void getTouchSpiValues() throws IOException {
		raspberrySpiService.writeByte(X_ADDRESS);
		byte[] readBytes = raspberrySpiService.readBytes(2);
		int xVal = (((readBytes[1] & 0xFF) << 8) | (readBytes[0] & 0xFF));

		raspberrySpiService.writeByte(Y_ADDRESS);
		readBytes = raspberrySpiService.readBytes(2);
		int yVal = (((readBytes[1] & 0xFF) << 8) | (readBytes[0] & 0xFF));

		raspberrySpiService.writeByte(FORCE_TOUCH);

		MeasurementDomain domain = new MeasurementDomain();
		domain.setValueX((long) xVal);
		domain.setValueY((long) yVal);

		synchronized (positionList) {
			positionList.add(domain);
			if (positionList.size() > 10000)
				positionList = positionList.subList(0, 1000);
		}
	}
}
