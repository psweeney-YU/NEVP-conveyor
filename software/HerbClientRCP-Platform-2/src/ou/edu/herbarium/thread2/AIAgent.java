package ou.edu.herbarium.thread2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AIAgent extends Thread{
	public Process p;
	public void run(){
		try {
//			String localDir = System.getProperty("user.dir").replaceAll("\\\\", "/")+"/";
//			p = Runtime.getRuntime().exec(localDir+"aiao/GlobalContinuousAI.exe");
//			p.waitFor();
			boolean flag = false;
			p = Runtime.getRuntime().exec("tasklist");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String nextline = "";
			while((nextline=bufferedReader.readLine())!=null){
				if(nextline.startsWith("GlobalContinuousAI.exe")){
					flag = true;
				}
			}
			if(flag){
				p = Runtime.getRuntime().exec("taskkill /IM GlobalContinuousAI.exe /f");
				while((nextline=bufferedReader.readLine())!=null){
					System.out.println(nextline);
				}
				p.waitFor();
			}
			
			String localDir = System.getProperty("user.dir").replaceAll("\\\\", "/")+"/";
			p = Runtime.getRuntime().exec(localDir+"aiao/GlobalContinuousAI.exe");
			p.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
