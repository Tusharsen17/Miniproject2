package in.ashokit.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class CitizenPlan {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer citizenId;
	private String name;
	private String emailid;
	private Long phoneno;
	
	private Long ssn;
	private String gender;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
	
	
	public CitizenPlan() {
		
	}


	public CitizenPlan( String name, String emailid, Long phoneno, Long ssn, String gender,
			String planName, String planStatus, LocalDate planStartDate, LocalDate planEndDate) {
		
		this.name = name;
		this.emailid = emailid;
		this.phoneno = phoneno;
		this.ssn = ssn;
		this.gender = gender;
		this.planName = planName;
		this.planStatus = planStatus;
		this.planStartDate = planStartDate;
		this.planEndDate = planEndDate;
	}


	public Integer getCitizenId() {
		return citizenId;
	}


	public void setCitizenId(Integer citizenId) {
		this.citizenId = citizenId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmailid() {
		return emailid;
	}


	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}


	public Long getPhoneno() {
		return phoneno;
	}


	public void setPhoneno(Long phoneno) {
		this.phoneno = phoneno;
	}


	public Long getSsn() {
		return ssn;
	}


	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getPlanName() {
		return planName;
	}


	public void setPlanName(String planName) {
		this.planName = planName;
	}


	public String getPlanStatus() {
		return planStatus;
	}


	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}


	public LocalDate getPlanStartDate() {
		return planStartDate;
	}


	public void setPlanStartDate(LocalDate planStartDate) {
		this.planStartDate = planStartDate;
	}


	public LocalDate getPlanEndDate() {
		return planEndDate;
	}


	public void setPlanEndDate(LocalDate planEndDate) {
		this.planEndDate = planEndDate;
	}


	@Override
	public String toString() {
		return "CitizenPlan [citizenId=" + citizenId + ", name=" + name + ", emailid=" + emailid + ", phoneno="
				+ phoneno + ", ssn=" + ssn + ", gender=" + gender + ", planName=" + planName + ", planStatus="
				+ planStatus + ", planStartDate=" + planStartDate + ", planEndDate=" + planEndDate + "]";
	}

	
}
