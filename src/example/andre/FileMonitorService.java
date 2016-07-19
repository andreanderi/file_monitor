package example.andre;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileMonitorService  extends SimpleFileVisitor<Path> {
	
	private final static Logger LOG = LoggerFactory.getLogger(FileMonitorService.class);
	
	List<String> fileList = new ArrayList<>();	
	boolean noFileFound = true;

	public List<String> getFileList(){
		return fileList;
	}
	
	public boolean noFileFound(){		
		return noFileFound;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		LOG.debug("checking the directory ", dir.toString());
		return super.preVisitDirectory(dir, attrs);
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		
        String fileName = file.toString();
        LOG.debug("File found: "+fileName);
        fileList.add(fileName);
        
		return super.visitFile(file, attrs);
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		LOG.debug("visitFileFailed: ", file.toString());
		return super.visitFileFailed(file, exc);
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		return super.postVisitDirectory(dir, exc);
	}

}
