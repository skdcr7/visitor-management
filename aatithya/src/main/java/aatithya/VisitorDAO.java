package aatithya;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

public class VisitorDAO {
	private Configuration  configuration = new Configuration().configure("hibernate.cfg.xml");
	private SessionFactory sf=configuration.buildSessionFactory();
	private Session s = sf.openSession();
	private Transaction tx ;
	private VisitorDTO visitor=null;
	
	LocalTime currentTime = LocalTime.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss a");
	String time=currentTime.format(df);
	public void addAdmin(String username, String password) {
		try {
			tx = s.beginTransaction();
			VisitorDAO visitor = new VisitorDAO();
			String hashedPassword=visitor.getMd5(password);
			EmployeeDTO adminEmp=new EmployeeDTO(username,hashedPassword);
			s.save(adminEmp);
			tx.commit();
			s.close();
			
		} catch (Exception e) {
			tx.rollback();
		}
		
	}
	public void checkin(VisitorDTO visitor)
	{
		s=sf.openSession();
		tx = s.beginTransaction();
		s.save(visitor);
		tx.commit();
		s.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<VisitorDTO> getAllVisitors()
	{
		s=sf.openSession();
		tx=s.beginTransaction();
		List<VisitorDTO> visitorList = s.createQuery("from VisitorDTO").list();
		tx.commit();
		s.close();
		return visitorList;
	}
	
	public int checkout( VisitorDTO visitor)
	{
		s=sf.openSession();
		tx=s.beginTransaction();
		int flag=0;
		if(s.createQuery("select 1 from VisitorDTO where phone_number = "+visitor.getPhoneNumber()).uniqueResult()!=null)
		{
			s.createQuery("update VisitorDTO set status='Completed', checkout ='"+ time +"'  where phone_number ="+visitor.getPhoneNumber()+"").executeUpdate();
			visitor.setStatus("Completed");
			tx.commit();
			 flag=1;
			 s.close();
		}
		else {
			flag=0;
		}
		return flag;
	}
	
	public String getLogin(EmployeeDTO emp)
	{
		emp.setPassword(getMd5(emp.getPassword()));
		NativeQuery query = s.createNativeQuery("select 1 from EmployeeDTO where username= '"+emp.getUsername()
				+"' and password= '"+emp.getPassword()+"'");
		if(query.uniqueResult() !=null)
		{
			return "success";
		}
		else
		{
			return "failure";
		}
	}
	private String getMd5(String input) 
	{ 
		try {  
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			byte[] messageDigest = md.digest(input.getBytes()); 
			BigInteger no = new BigInteger(1, messageDigest);  
			String hashtext = no.toString(16); 
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext; 
			} 
			return hashtext; 
		}  
		catch (NoSuchAlgorithmException e) { 
			throw new RuntimeException(e); 
		} 
	}
	
	@SuppressWarnings("unchecked")
	public List<HostDTO> getAllHost()
	{
		s=sf.openSession();
		tx=s.beginTransaction();
		List<HostDTO> hostList = s.createQuery("from HostDTO").list();
		tx.commit();
		s.close();
		return hostList;
		
	}
	
	public void addHost(int id, String name)
	{
		try {
			s=sf.openSession();
			tx = s.beginTransaction();
			HostDTO host = new HostDTO(id,name);
			s.save(host);
			tx.commit();
			s.close();
			
		} catch (Exception e) {
			tx.rollback();
		}
	}
}
