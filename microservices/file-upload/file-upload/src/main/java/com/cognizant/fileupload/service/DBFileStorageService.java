package com.cognizant.fileupload.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cognizant.fileupload.exception.FileStorageException;
import com.cognizant.fileupload.model.StockPrice;
import com.cognizant.fileupload.property.FileStorageProperties;
import com.cognizant.fileupload.repository.StockRepository;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@Service
public class DBFileStorageService {

	Date minDate=null;
	Date maxDate=null;
	@Autowired
	private StockRepository stockRepository;

	private final Path fileStorageLocation;

	@Autowired
	public DBFileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public void storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}

		long start = System.currentTimeMillis();
		FileInputStream inputStream = null;
		Workbook workbook = null;

		try {
			inputStream = new FileInputStream("D:\\Users\\allfiles\\uploads\\" + fileName);
			workbook = new XSSFWorkbook(inputStream);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = firstSheet.iterator();

		rowIterator.next(); // skip the header row

		while (rowIterator.hasNext()) {

			StockPrice stockPrice = new StockPrice();

			Row nextRow = rowIterator.next();
			
			System.out.println(nextRow+"dsgfdsg");
			
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			  while (cellIterator.hasNext()) {
                  Cell nextCell = cellIterator.next();
                  int columnIndex = nextCell.getColumnIndex();
                  switch (columnIndex) {
                  case 0:
                      long companyCode = (long) nextCell.getNumericCellValue();
                      stockPrice.setCompanyCode(companyCode);
                      System.out.println("=================>" + stockPrice.getCompanyCode());
                    
                      break;
                  case 1:
                      String stockExchange = nextCell.getStringCellValue();
                      stockPrice.setStockExchangeName(stockExchange);
                      System.out.println("=================>" + stockExchange);
                      break;
                  case 2:
                	  
                      long currentPrice = (long) nextCell.getNumericCellValue();
                      stockPrice.setCurrentPrice(currentPrice);
                      System.out.println("=================>" + currentPrice);
                      break;
                  case 3:
                  	Date date = nextCell.getDateCellValue();
//                  	if(minDate==null) {
//                  	minDate = date;}
//                  	if(maxDate==null) {
//	                    	maxDate = date;}
//                  	if(date.compareTo(minDate)<0) {
//                  		minDate=date;
//                  		
//                  	}
//                  	if(date.compareTo(maxDate)>0) {
//                  		maxDate=date;
//                  		
//                  	}
                  	stockPrice.setDate(date);
                  	System.out.println("=================>" + date);
                  	break;
                  case 4:
                  	Date time = nextCell.getDateCellValue();               	
                  	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                  	stockPrice.setTime(Time.valueOf(sdf.format(time)));
                  	System.out.println("=================>1212" + Time.valueOf(sdf.format(time)));
            
                  	break;
                  	default:
                  		break;
                  }
                  stockRepository.save(stockPrice);
              }
			
		}

		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.printf("Import done in %d ms\n", (end - start));

	}

}