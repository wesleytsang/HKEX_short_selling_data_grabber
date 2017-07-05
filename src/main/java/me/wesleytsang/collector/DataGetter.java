package me.wesleytsang.collector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;

/**
 * The Class DataGetter.
 */
public class DataGetter {

	/**
	 * Instantiates a new data getter.
	 */
	public DataGetter() {}
	
	/**
	 * Gets the data from the url and return a plain text content.
	 *
	 * @param url the url
	 * @return the data
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String getData(String url) throws IOException {
		URL website = new URL(url);
		URLConnection connection = website.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder response = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\r\n");	//collect data line-by-line
		}
        in.close();	//buffer close
        String nohtml = Jsoup.parse(response.toString()).text(); //remove all the html/xml tags
        return nohtml;
	}
	
	/**
	 * Recognize the date from the input content.
	 *
	 * @param data the data
	 * @return the date
	 */
	public String getDate(String data) {
		Pattern pattern = Pattern.compile("TRADING DATE : (\\d\\d\\s\\w\\w\\w\\s\\d\\d\\d\\d)");	//pattern of a date
		Matcher matcher = pattern.matcher(data.substring(0, 200));
		if (matcher.find()) {
			String[] splitedDate = matcher.group(1).split("\\s+");
			String returnDate = splitedDate[2] + this.convertMonth(splitedDate[1]) + splitedDate[0];
		    return returnDate;
		}
		else {
			return null;	//date can not be found
		}
	}
	
	/**
	 * Convert month from English representation to number representation.
	 *
	 * @param month the month
	 * @return the string
	 */
	private String convertMonth(String month) {
		String returnMonth = "00";
		switch (month) {
	        case "JAN": returnMonth = "01";
	                 break;
	        case "FEB": returnMonth = "02";
	                 break;
	        case "MAR": returnMonth = "03";
	                 break;
	        case "APR": returnMonth = "04";
	                 break;
	        case "MAY": returnMonth = "05";
	                 break;
	        case "JUN": returnMonth = "06";
	                 break;
	        case "JUL": returnMonth = "07";
	                 break;
	        case "AUG": returnMonth = "08";
	                 break;
	        case "SEP": returnMonth = "09";
	                 break;
	        case "OCT": returnMonth = "10";
	                 break;
	        case "NOV": returnMonth = "11";
	                 break;
	        case "DEC": returnMonth = "12";
	                 break;
	        default: returnMonth = "//";
	                 break;
	    }
		return returnMonth;
	}
	
	/**
	 * Recognize the market from the content.
	 *
	 * @param data the data
	 * @return the market
	 */
	public String getMarket(String data) {
		Pattern pattern = Pattern.compile("Short Selling Turnover \\((\\w*)(\\)|\\s)");	//pattern for the market
		Matcher matcher = pattern.matcher(data.substring(0, 200));
		if (matcher.find()) {
		    return matcher.group(1).toUpperCase();
		}
		else {
			System.out.println("Error: Source cannot be found.");
			return null;
		}
	}
	
	/**
	 * Recognize the data is for morning or evening.
	 *
	 * @param data the data
	 * @return the morning
	 */
	public String getMorning(String data) {
		Pattern pattern = Pattern.compile("up to morning close");	//look for a sentence like this
		Matcher matcher = pattern.matcher(data.substring(0, 200));
		if (matcher.find()) {
		    return "AM";	//if found then it is morning data
		}
		else {
			return "PM";	//otherwise it is evening data
		}
	}
	
	/**
	 * Valid check, if data have been uploaded even if it is empty.
	 *
	 * @param data the data
	 * @return true, if successful
	 */
	public boolean validCheck(String data) {
		if (this.getDate(data) == null) {	//if date cannot be found then it is not a valid data
			return false;
		}
		return true;
	}
	
	/**
	 * Write file to store a copy locally.
	 *
	 * @param data the data
	 */
	public void writeFile(String data) {
		String path = "./src/main/resources/data/";
		String directoryName = path.concat(this.getDate(data));
		String fileName = this.getMarket(data) + "-" + this.getMorning(data) + ".txt";
		
		File directory = new File(directoryName);
	    if (!directory.exists()){		//if the folder is not exist then create one
	        directory.mkdir();
	        System.out.println("New folder created.");
	    }
	    
	    File file = new File(directoryName + "/" + fileName);	//making a file to store new data
	    try {
	        FileWriter fw = new FileWriter(file.getAbsoluteFile());
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(data);
	        bw.close();
	        System.out.println("File " + fileName + " has been saved in " + directoryName);
	    }
	    catch (IOException e){
	        e.printStackTrace();
	        System.exit(-1);
	    }

	}
	
	/**
	 * Read file.
	 *
	 * @param date the date
	 * @param market the market
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("resource")
	public String readFile(String date, String market) throws IOException {
		String fileName = market + "-AM.txt";
		File f = new File("./src/main/resources/data/" + date + "/" + fileName);
		if (f.exists()) {
			String content = new Scanner(f).useDelimiter("\\Z").next();
			return content;
		}
		return "";
	}
	
}
