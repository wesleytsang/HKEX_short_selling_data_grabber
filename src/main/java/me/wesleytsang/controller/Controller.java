package me.wesleytsang.controller;

import me.wesleytsang.collector.DataGetter;
import me.wesleytsang.model.DBConnect;
import me.wesleytsang.sql.Jdbcmysql;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class Controller.
 */
public class Controller {

	private DataGetter dataGetter = null;
	private Jdbcmysql jdbc = null;
	private Logger logger = null;

	public Controller(String path, String userName, String pw, String table, Logger logger) {
		dataGetter = new DataGetter();
		jdbc = new Jdbcmysql(new DBConnect(path, userName, pw), table);
		this.logger = logger;
	}

	public void morningProcess(String[] links) throws Exception {
		for (String link: links) {
			String data = dataGetter.getData(link);
			String date = dataGetter.getDate(data);
			String market = dataGetter.getMarket(data);

			logger.log(Level.INFO, "Link loaded, Date: " + date + " Market: " + market);

			if (dataGetter.validCheck(data) && !jdbc.checkIfExist(date, market)) {
				logger.log(Level.INFO, "Data is valid");
				dataGetter.writeFile(data); //Store data to local
				logger.log(Level.INFO, "Data stored locally");
				logger.log(Level.INFO, "Uploading data to database");
				jdbc.uploadData(data, date, market); //Store data to database
			} else {
				logger.log(Level.WARNING, "Today AM " + market + " is not yet ready or already exist on the database.");
				System.out.println("Today AM " + market + " is not yet ready or already exist on the database.");
			}
		}
	}
	
	public void eveningProcess(String[] links) throws Exception {
		for (String link: links) {
			String data = dataGetter.getData(link);
			String date = dataGetter.getDate(data);
			String market = dataGetter.getMarket(data);

			if (dataGetter.validCheck(data)) {
				logger.log(Level.INFO, "Data is valid");
				dataGetter.writeFile(data); //Store data to local
				logger.log(Level.INFO, "Data stored locally");
				logger.log(Level.INFO, "Grabbing its morning data");
				String data2 = dataGetter.readFile(date, market); //remember to check if file not exits
				logger.log(Level.INFO, "Data comparing and upload");
				jdbc.compareData(data, data2, date, market);
			} else {
				logger.log(Level.WARNING, "Today AM " + market + " is not yet ready.");
				System.out.println("Today PM " + market + " is not yet ready.");
			}
		}
	}
}
