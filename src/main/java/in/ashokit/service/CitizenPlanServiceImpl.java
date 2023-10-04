package in.ashokit.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.binding.SerachCriteria;
import in.ashokit.entity.CitizenPlan;
import in.ashokit.repo.CitizenPlanRepo;
import in.ashokit.utils.EmailUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {
	
	
	@Autowired
	private CitizenPlanRepo repo;
	
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public List<String> getPlanNames() {
		return repo.getPlanName();
		
	}

	@Override
	public List<String> getPlanStatus() {
		return repo.getPlanStatus();
	
	}

	@Override
	public List<CitizenPlan> searchCitizens(SerachCriteria criteria) {
		
		CitizenPlan entity = new CitizenPlan();
		
		 if(StringUtils.isNotBlank(criteria.getPlanName())) {
			 entity.setPlanName(criteria.getPlanName());
		 }
		 

		 if(StringUtils.isNotBlank(criteria.getPlanStatus())) {
			 entity.setPlanStatus(criteria.getPlanStatus());
		 }
		 

		 if(StringUtils.isNotBlank(criteria.getGender())) {
			 entity.setGender(criteria.getGender());
		 }
		 
		 if(null!=criteria.getPlanStartDate()) {
			 entity.setPlanStartDate(criteria.getPlanStartDate());
		 }
		 
		 if(null!=criteria.getPlanStartDate()) {
			 entity.setPlanEndDate(criteria.getPlanEndDate());
		 }
		 
		 Example<CitizenPlan> of = Example.of(entity);
	     
		return repo.findAll(of);
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws Exception {
          List<CitizenPlan> records = repo.findAll();
          HSSFWorkbook workbook = new HSSFWorkbook();
          HSSFSheet sheet = workbook.createSheet("Data");
          HSSFRow headerRow = sheet.createRow(0);
          headerRow.createCell(0).setCellValue("Name");
          headerRow.createCell(1).setCellValue("Email");
          headerRow.createCell(2).setCellValue("Gender");
          headerRow.createCell(3).setCellValue("SSN");
          headerRow.createCell(4).setCellValue("Plan Name");
          headerRow.createCell(5).setCellValue("Plan Status");
          
          int rowIndex =1;
          for (CitizenPlan record : records) {
        	  HSSFRow dataRow = sheet.createRow(rowIndex);
        	  dataRow.createCell(0).setCellValue(record.getName());
        	  dataRow.createCell(1).setCellValue(record.getEmailid());
        	  dataRow.createCell(2).setCellValue(record.getGender());
        	  dataRow.createCell(3).setCellValue(record.getSsn());
        	  dataRow.createCell(4).setCellValue(record.getPlanName());
        	  dataRow.createCell(5).setCellValue(record.getPlanStatus());
        	  
        	  rowIndex ++;
          }
          
          //to download files as email attachment
          File f = new File("data.xls");
          FileOutputStream fos = new FileOutputStream(f);
          workbook.write(fos);
          emailUtils.sendEmail(f);
          
          
          //To download files in browser
          ServletOutputStream outputStream = response.getOutputStream();
          workbook.write(outputStream);
          workbook.close();
          outputStream.close();
          
   
		
	}

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception{
		
		
		File f = new File("data.pdf");
		FileOutputStream fos = new FileOutputStream(f);
		
		
	//List<CitizenPlan> records = repo.findAll();
	
	Document pdfDoc = new Document(PageSize.A4);
	ServletOutputStream outputStream = response.getOutputStream();
	PdfWriter.getInstance(pdfDoc, outputStream);
	
	PdfWriter.getInstance(pdfDoc, fos);
	pdfDoc.open();
	
	Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	 Paragraph p = new Paragraph("Citizen Plan Information");
	 p.setAlignment(Paragraph.ALIGN_CENTER);
	 pdfDoc.add(p);

	 
    PdfPTable table = new PdfPTable(6);
   
    PdfPCell cell = new PdfPCell();
    cell.setPhrase(new Phrase("Name",fontTiltle));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Email",fontTiltle));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Gender",fontTiltle));
    table.addCell(cell);
    cell.setPhrase(new Phrase("SSN",fontTiltle));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Plan Name",fontTiltle));
    table.addCell(cell);
    cell.setPhrase(new Phrase("Plan Status",fontTiltle));
    table.addCell(cell);
   
	List<CitizenPlan> records = repo.findAll();
	
    for(CitizenPlan record : records) {
    	
    	table.addCell(record.getName());
    	table.addCell(record.getEmailid());
    	table.addCell(record.getGender());
    	table.addCell(String.valueOf(record.getSsn()));
    	table.addCell(record.getPlanName());
    	table.addCell(record.getPlanStatus());
    }
    
    
    
    
    
    
    pdfDoc.add(table);
    
    emailUtils.sendEmail(f);
	 
	 pdfDoc.close();
	 outputStream.close();
	 fos.close();
	 emailUtils.sendEmail(f);
		
	}

}
