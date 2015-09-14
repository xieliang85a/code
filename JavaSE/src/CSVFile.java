
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class CSVFile {
	public static void main(String [] args)throws Exception{
		URL url = CSVFile.class.getClassLoader().getResource("b.csv");
		File f = new File(url.getPath()/*"C:\\Users\\xieliang\\Desktop\\b.csv"*/);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String str = null;
		StringBuilder sb = null;
		while((str = br.readLine())!=null){
			System.out.println(str);
		}
		
		br.close();
		
	}
}
