package aatithya;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VisitorDTO")
public class VisitorDTO implements Serializable {
	@Id
	@Column(name="Phone_Number")
	private long phoneNumber;
	@Column(name="Visitor_Name")
	private String name;
	@Column(name="Email")
	private String email;
	@Column(name="Purpose")
	private String purpose;
	@Column(name="Host_Name")
	private String hostName;
	@Id
	@Column(name="CheckIn")
	private String checkin;
	@Column(name="CheckOut")
	private String checkout;
	@Column(name="Date")
	private String Date;
	@Column(name="status")
	private String status;
	public VisitorDTO() {
		super();
	}
	public VisitorDTO(long phoneNumber, String name, String email, String purpose, String hostName) {
		super();
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.email = email;
		this.purpose = purpose;
		this.hostName = hostName;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPurpose() {
		return purpose;
	}
	public String getHostName() {
		return hostName;
	}
	public String getCheckin() {
		return checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public String getDate() {
		return Date;
	}
	public String getStatus() {
		return status;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	
}
