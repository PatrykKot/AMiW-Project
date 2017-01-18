package pl.poznan.put.measurements.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MeasurementDomain {
	@Id
	@GeneratedValue
	private Long id;

	private Long valueX;

	private Long valueY;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValueX() {
		return valueX;
	}

	public void setValueX(Long valueX) {
		this.valueX = valueX;
	}

	public Long getValueY() {
		return valueY;
	}

	public void setValueY(Long valueY) {
		this.valueY = valueY;
	}

}
