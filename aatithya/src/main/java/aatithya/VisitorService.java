package aatithya;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/uri", produces = "application/json")
public class VisitorService {
	/*SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss z");
	String timeString = dateFormat.format(new Date()).toString();*/
	
	LocalTime currentTime = LocalTime.now(ZoneId.of("Asia/Kolkata"));
	DateTimeFormatter df = DateTimeFormatter.ofPattern("hh:mm:ss a");
	String time=currentTime.format(df);
	
	
	DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
	String dateString = dateFormat2.format(new Date()).toString();
	VisitorDAO visitor=new VisitorDAO();
	@RequestMapping(value = "/ex" ,method = RequestMethod.GET)
	@ResponseBody
	public String showDetails(){
		visitor.addAdmin("abcde@gmail.com","Incture@123");
		return "Success";
	}
	
	private VisitorDAO visitorDAO = new VisitorDAO();
		
	@RequestMapping(value="/get", method=RequestMethod.GET)
	public ResponseEntity<List<VisitorDTO>> showVisitorDetails()
	{
		List<VisitorDTO> visitorList = visitorDAO.getAllVisitors();
		return new ResponseEntity<List<VisitorDTO>>(visitorList,HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST, consumes="application/json")
	public String addVisitors(@RequestBody VisitorDTO visitorq)
	{
		visitorq.setDate(dateString);
		visitorq.setCheckin(time);
		visitorq.setStatus("Pending");
		visitor.checkin(visitorq);
		return "Data Submitted";
	}
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public String checkout(@RequestBody VisitorDTO visitorq)
	{
		int flag=visitorDAO.checkout(visitorq);
		if(flag==1) {
			return "Checkout Successfull";
		}
		else
		{
			return "Wrong credentials";
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public String login(@RequestBody EmployeeDTO emp)
	{
		String result=visitorDAO.getLogin(emp);
		if(result=="success") {
			return "Login Successful";
		}
		else
		{
			return "Wrong Credentials";
		}
	}
	
	@RequestMapping(value="/dropDown", method=RequestMethod.GET)
	public ResponseEntity<List<HostDTO>> dropDownList()
	{
		List<HostDTO> hostList = visitorDAO.getAllHost();
		return new ResponseEntity<List<HostDTO>>(hostList,HttpStatus.OK);
	}
	
	@RequestMapping(value="/addHost", method=RequestMethod.GET)
	public String showHost()
	{
		visitorDAO.addHost(1105,"Sachin Verma");
		return "Host Added Successfully";
	}
}
