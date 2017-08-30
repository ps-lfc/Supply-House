import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
//My standard file processor, I have been using for most of my projects.
public class FileProcessor {
		private FileReader fileReader = null;
		private String fileName = null;
		private ClassLoader classLoader = null;
		private URL urlToFilePath = null;
		private BufferedReader getSingleLine = null;
		Parser parser = new Parser ();
		public FileProcessor(String in_Filename){
			this.fileName = in_Filename;
			classLoader = Thread.currentThread().getContextClassLoader();
			urlToFilePath = classLoader.getResource(fileName);
			try {
				fileReader = new FileReader(new File(getFileName()));
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			getSingleLine = new BufferedReader(fileReader);
		}
		public FileProcessor(){
			try {
				fileReader = new FileReader(new File(fileName));
				getSingleLine = new BufferedReader(fileReader);
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		public void openFile(String nameofFile, String ext) throws IOException{
			String s = null;
			try {
				fileReader = new FileReader(new File(getFileName()));
				getSingleLine = new BufferedReader(fileReader);
				int i=1;
				while((s=getSingleLine.readLine())!=null){
					//After reading each line in the file, call ParseLine method in class Parser to process the data and store it in database.
					parser.ParseLine(s, nameofFile,i,ext);
					i++;
				}
			} 
			catch (FileNotFoundException fileNotFoundException) {
				System.err.println("File not found exception");
				System.exit(1);
			}
		}
		public static void listfilesInDirectoyr(String directoryName, ArrayList<File> files) {
		    File directory = new File(directoryName);
		    File[] fList = directory.listFiles();
		    for (File file : fList) {
		        if (file.isFile()) {
		            files.add(file);
		        } else if (file.isDirectory()) {
		        	listfilesInDirectoyr(file.getAbsolutePath(), files);
		        }
		    }
		}
		public String readLineFromFile() {
			String lineReadFromFile = null;
			try {
				lineReadFromFile = getSingleLine.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("File Read exception");
				System.exit(1);
			}
			return lineReadFromFile;
		}
		public String getFileName() {
			return fileName;
		}
		public URL getUrlToFilePath() {
			return urlToFilePath;
		}
}