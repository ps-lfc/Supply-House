import java.io.File;
import java.io.IOException;

public class Driver {
	static Parser parser = new Parser();
	
	public static void main(String[] args) {
		//check for the arguments. Here I am passing the path of the folder as argument. 
		//Throws an error if none or more than 1 arguments passed.
		if(args.length != 1) {
			System.err.println("Usage Improper: Please provide the arguments as per the assignment requirement");
			System.exit(1);
		}
		File folder = new File(args[0]);
		File[] Files = folder.listFiles();
		//If there are no files in the folder, program will print the message and exit.
		if(Files.length==0){
			System.out.println("There are no files in the folder");
			System.exit(0);
		}
		else{
			for (int i = 0; i < Files.length; i++) {
				String fileName=Files[i].toString();
				String extension = "";
				String NameofFile = "";
				//Parse the path to get the name of the file
				int k=(fileName).lastIndexOf('\\');
				if(k>0){
					NameofFile = fileName.substring(k+1);			
				}
				//get file extension
				extension = NameofFile.substring(NameofFile.lastIndexOf('.')+1);
				//get file name without extension
				NameofFile = NameofFile.replaceFirst("[.][^.]+$", "");
				FileProcessor fileProcessor = new FileProcessor(fileName);
				try {
					//call file processor to read file
					fileProcessor.openFile(NameofFile,extension);
				} catch (IOException e) {
					e.printStackTrace();
				}			
			}
		}
	}
}