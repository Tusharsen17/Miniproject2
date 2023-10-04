package in.ashokit.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepo;

@Component
public class DataLoader implements ApplicationRunner{
	
	@Autowired
	private CitizenPlanRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		repo.deleteAll();
	
		CitizenPlan p1 = new CitizenPlan("Tushar","tushar11@gmail.com",1234578L,12345678L,"Male","Cash","Approved",
				LocalDate.now(),LocalDate.now().plusMonths(6));
		
		CitizenPlan p2 = new CitizenPlan("Yash","yash11@gmail.com",1234578L,12345678L,"Male","Cash","Denied",
				LocalDate.now(),LocalDate.now().plusMonths(6));
		
		CitizenPlan p3 = new CitizenPlan("Abhay","abhay11@gmail.com",1234578L,12345678L,"Male","Food","Approved",
				LocalDate.now(),LocalDate.now().plusMonths(6));
		
		CitizenPlan p4 = new CitizenPlan("Ashok","ashok11@gmail.com",1234578L,12345678L,"Male","Food","Denied",
				LocalDate.now(),LocalDate.now().plusMonths(6));
		
		CitizenPlan p5 = new CitizenPlan("Krishna","krishna11@gmail.com",1234578L,12345678L,"Fe-Male","Medical","Approved",
				LocalDate.now(),LocalDate.now().plusMonths(6));
		
		CitizenPlan p6 = new CitizenPlan("Rani","rani11@gmail.com",1234578L,12345678L,"Fe-Male","Medical","Approved",
				LocalDate.now(),LocalDate.now().plusMonths(6));
		
		List<CitizenPlan> records = Arrays.asList(p1,p2,p3,p4,p5,p6);
		repo.saveAll(records);
		
	}

}
