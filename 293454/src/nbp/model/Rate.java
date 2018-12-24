package nbp.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Rate")
public class Rate {

	private String number;
	private String effectiveDate;
	private double bid;
	private double ask;
	private double mid;

	public Rate() {
		this.number = "";
		this.effectiveDate = "";
		this.bid = 0;
		this.ask = 0;
		this.mid = 0;
	}
	
	public Rate(String number, String effectiveDate, double bid, double ask, double mid) {
		this.number = number;
		this.effectiveDate = effectiveDate;
		this.bid = bid;
		this.ask = ask;
		this.mid = mid;
	}

	@XmlElement(name = "No")
	public String getNumber() {
		return number;
	}

	@XmlElement(name = "EffectiveDate")
	public String getEffectiveDate() {
		return effectiveDate;
	}

	@XmlElement(name = "Bid")
	public double getBid() {
		return bid;
	}

	@XmlElement(name = "Ask")
	public double getAsk() {
		return ask;
	}
	
	@XmlElement(name = "Mid")
	public double getMid() {
		return mid;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setBid(double bid) {
		this.bid = bid;
	}

	public void setAsk(double ask) {
		this.ask = ask;
	}


	public void setMid(double mid) {
		this.mid = mid;
	}
	
}
