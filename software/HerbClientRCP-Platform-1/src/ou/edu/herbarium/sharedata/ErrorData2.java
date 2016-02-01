package ou.edu.herbarium.sharedata;

import java.util.ArrayList;

public class ErrorData2 {
	public static int TIME_SPAN = 0;
	private ArrayList<String> sharedQueue = new ArrayList<String>();
	
	public synchronized void setShareData(String c) {
		this.sharedQueue.add(c);
		if (sharedQueue.size()>TIME_SPAN) {
			notify();
		}
	}

	public synchronized String getShareData() {
		try {
			wait(300);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		if(sharedQueue.size()>0){
			String c = sharedQueue.get(0);//60005
			for(int i=0;i<sharedQueue.size();i++){
				sharedQueue.remove(0);
			}
			return c;
		}
		else{
			return null;
		}
	}
}
