package aatithya;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HostDTO")
public class HostDTO {

	@Id
	@Column(name="hostId")
	private int hostId;
	
	@Column(name="hostName")
	private String hostName;
	
	public int getHostId() {
		return hostId;
	}
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public HostDTO(int hostId, String hostName) {
		super();
		this.hostId = hostId;
		this.hostName = hostName;
	}
	
	public HostDTO()
	{
		super();
	}
		
}
