package me.wesleytsang;

import me.wesleytsang.model.ReadPropertyFile;
import me.wesleytsang.controller.Controller;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.File;

/**
 * The Class Main.
 */
public class Main {
	
	/**
	 * The main method.
	 *
	 * @param arg the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {

		try {
			LogManager lm = LogManager.getLogManager();
			Logger logger = Logger.getLogger("Main");
			File directory = new File("./log");
		    if (!directory.exists()){		//if the folder is not exist then create one
		        directory.mkdir();
		        System.out.println("New log folder created.");
		    }
			FileHandler fh = new FileHandler("./log/log.txt");

			lm.addLogger(logger);
			logger.setLevel(Level.INFO);
			fh.setFormatter(new SimpleFormatter());

			logger.addHandler(fh);

			logger.log(Level.INFO, "Program executing...");

			ReadPropertyFile pf = new ReadPropertyFile();
			
			Controller controller = new Controller(pf.getPath(), pf.getUserName(), pf.getPassword(), pf.getTable(), logger);
			String[] links = new String[3];
			
			if (args.length == 0) {
				logger.log(Level.SEVERE, "Proper usage: java main [AM|PM]");
				System.out.println("Proper usage: java main [AM|PM]");
				System.exit(1);
			} else if (args[0].equals("AM")) {
				logger.log(Level.INFO, "Running for Morning Close");
				links[0] = pf.getMainAM();
				links[1] = pf.getGemAM();
				links[2] = pf.getEnAM();
				controller.morningProcess(links);
			} else if (args[0].equals("PM")) {
				logger.log(Level.INFO, "Running for Evening Close");
				links[0] = pf.getMainPM();
				links[1] = pf.getGemPM();
				links[2] = pf.getEnPM();
				controller.eveningProcess(links);
			}

			logger.log(Level.INFO, "Program finishing...");
			fh.close();
		} catch (Exception e) {
			System.out.println("Exception thrown: e");
			e.printStackTrace();
		}
		
		

	}
	
}
