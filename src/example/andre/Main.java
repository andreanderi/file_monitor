package example.andre;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Main {
	private final static Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
			
		List<String> filesFound = null;
		String targetPathToScan = "D:\\workspaces\\";
		try {
			filesFound = scanDirectory(targetPathToScan);
			
			for (String file : filesFound){
				System.out.println(file); //For a quick test
				LOG.debug(file);
			}
		
		} catch (Exception e) {
			LOG.error("Could not complete the directory scan \n" , e);
			e.printStackTrace();
		}

	}
	
	public static List<String> scanDirectory(String targetDir) throws Exception {
		LOG.info("Scanning the directory "+targetDir);

		Path path = Paths.get(targetDir);
		List<String> fileList = null;
		FileMonitorService fileScan = new FileMonitorService();
		try {
			Files.walkFileTree(path, fileScan);
			fileList = new ArrayList<>();
			fileList = fileScan.getFileList();
		} catch (IOException e) {
			LOG.error("Could not scan the directory \n",e);
			e.printStackTrace();
			throw e;
		}
		return fileList;
	}

}
