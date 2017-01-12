package pl.poznan.put.device;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

public class SpiResponse {
	private String hexResponse;
	private String stringResponse;
	private Integer length;

	public SpiResponse(byte[] data, Charset charset) {
		this.length = data.length;
		this.hexResponse = Hex.encodeHexString(data);
		this.stringResponse = new String(data, charset);
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("StringResponse", stringResponse);
		map.put("HexResponse", hexResponse);
		map.put("Length", length);

		return map;
	}
}
