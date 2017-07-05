package me.wesleytsang.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.wesleytsang.model.DBConnect;
import me.wesleytsang.model.Record;

/**
 * The Class Jdbcmysql.
 */
public class Jdbcmysql {
	
	/** The db. */
	private DBConnect db = null;
	private String table = "";
	
	/**
	 * Instantiates a new jdbcmysql.
	 *
	 * @param db the db
	 */
	public Jdbcmysql(DBConnect db, String table) {
		this.db= db;
		this.table = table;
	}
	
	/**
	 * Upload data.
	 *
	 * @param data the data
	 * @param date the date
	 * @param market the market
	 * @throws Exception the exception
	 */
	public void uploadData(String data, String date, String market) throws Exception {	//for morning data, no need to compare with morning data
		ArrayList<Record> dataList = decodeData(data, date, market);
		uploadInsert(dataList);
	}
	
	/**
	 * Compare data.
	 *
	 * @param eveningData the evening data
	 * @param morningData the morning data
	 * @param date the date
	 * @param market the market
	 * @throws Exception the exception
	 */
	public void compareData(String eveningData, String morningData, String date, String market) throws Exception {
		ArrayList<Record> eveningDataList = decodeData(eveningData, date, market);
		ArrayList<Record> morningDataList = decodeData(morningData, date, market);
		ArrayList<Record> insertList = new ArrayList<Record>();		//a list for data that is going to do insert query
		ArrayList<Record> updateList = new ArrayList<Record>();		//a list for data that is going to do update query
		
		eveningDataList.forEach(r -> {
			Boolean duplicated = false;
			for (Record r2: morningDataList) {
				if (r.toString().equals(r2.toString())) {	//if contain in both data set
					updateList.add(r);	//then it needs update query rather than insert
					duplicated = true;
					break;
				}
			}
			if (!duplicated) {
				insertList.add(r);	//if only contain in evening data set then insert into database
			}
		});
		
		uploadUpdate(updateList);
		uploadInsert(insertList);
	}
	
	/**
	 * Upload insert.
	 *
	 * @param dataList the data list
	 * @throws Exception the exception
	 */
	public void uploadInsert(ArrayList<Record> dataList) throws Exception {
		if (!dataList.isEmpty()) {
			try {
				db.connect();
				PreparedStatement preparedStatement = db.getConnection().prepareStatement("INSERT INTO " + table + " VALUES (?, ?, ?, ?, ?, ?)");
				
				for (Record record: dataList) {
					preparedStatement.setString(1, record.getCode());
					preparedStatement.setString(2, record.getName());
					preparedStatement.setString(3, record.getDate());
					preparedStatement.setString(4, record.getMarket());
					preparedStatement.setString(5, record.getSh() + "");
					preparedStatement.setString(6, record.getDollar() + "");
					preparedStatement.executeUpdate();
				}
	
				preparedStatement.close();
				db.closeConnection();
			} catch(ClassNotFoundException e) {
				System.out.println("DriverClassNotFound :" + e.toString());
			} catch(SQLException x) {
				System.out.println("Exception :" + x.toString());
			}
		}
	}
	
	/**
	 * Upload update.
	 *
	 * @param dataList the data list
	 * @throws Exception the exception
	 */
	public void uploadUpdate(ArrayList<Record> dataList) throws Exception {
		if (!dataList.isEmpty()) {
			try {
				db.connect();
				PreparedStatement preparedStatement = db.getConnection().prepareStatement("UPDATE " + table + " SET sh = ?, dollar = ? where code = ? and date = ? and market = ?");
				
				for (Record record: dataList) {
					preparedStatement.setString(1, record.getSh() + "");
					preparedStatement.setString(2, record.getDollar() + "");
					preparedStatement.setString(3, record.getCode());
					preparedStatement.setString(4, record.getDate());
					preparedStatement.setString(5, record.getMarket());
					preparedStatement.executeUpdate();
				}
				
				preparedStatement.close();
				db.closeConnection();
			} catch(ClassNotFoundException e) {
				System.out.println("DriverClassNotFound :" + e.toString());
			} catch(SQLException x) {
				System.out.println("Exception :" + x.toString());
			}
		} else {
			System.out.println("Data not found");
		}
	}
	
	/**
	 * Decode data.
	 *
	 * @param data the data
	 * @param date the date
	 * @param market the market
	 * @return the array list
	 */
	public ArrayList<Record> decodeData(String data, String date, String market) {
		ArrayList<Record> records = new ArrayList<Record>();
		String[] splitedData = null;
		Pattern pattern = Pattern.compile("%*\\d+\\s+[0-9A-Z]+(\\s[0-9A-Z\\-]+)*\\s+\\d+(,\\d+)*\\s+(\\d+(,\\d+)*)"); //get data row-by-row
		Matcher matcher = pattern.matcher(data);
		while (matcher.find()) {
			String temp = matcher.group(0).replaceAll(",","");	//remove comma from the sh and dollar value
			splitedData = temp.split("(\\s\\s)+");	//split everything
			for (int i = 0; i < splitedData.length; i+=4) {
				records.add(new Record(splitedData[i].trim(), splitedData[i+1].trim(), date, market, Integer.parseInt(splitedData[i+2].trim()), Integer.parseInt(splitedData[i+3].trim())));	// store as Record object
			}
		}	
		return records;
	}
	
	/**
	 * Check if exist.
	 *
	 * @param date the date
	 * @param market the market
	 * @return the boolean
	 * @throws Exception the exception
	 */
	public Boolean checkIfExist(String date, String market) throws Exception {
		db.connect();
		String query = "SELECT * FROM " + table + " WHERE date = " + date + " and market = '" + market + "' limit 1";
		ResultSet rs = db.getStatement().executeQuery(query);
		if (rs.next()) {		//if something found, then database is contained data from this market that day
			db.closeConnection();
            return true;
        }
		db.closeConnection();
		return false;
	}

}
