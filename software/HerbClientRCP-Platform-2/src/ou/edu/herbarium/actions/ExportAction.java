package ou.edu.herbarium.actions;

import java.io.IOException;

import org.eclipse.jface.action.Action;


public class ExportAction  extends Action{
	public ExportAction() {
		super("Export Metadata",null);
		this.setId("ExportAction");
	}

	
	
	public void run(){
		String localDir = System.getProperty("user.dir").replaceAll("\\\\", "/")+"/";
		try {
			String command = "perl "+localDir+"perl/rdfXmlGen.pl \n";
			System.out.println(command);
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			System.out.println("done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
