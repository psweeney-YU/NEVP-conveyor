package ou.edu.herbarium.sharedata;

import java.util.ArrayList;

public class ShareData7 {
	public static int TIME_SPAN = 0;
	private ArrayList<TransferData7> sharedQueue = new ArrayList<TransferData7>();
	
	public synchronized void setShareData(TransferData7 c) {
		this.sharedQueue.add(c);
		if (sharedQueue.size()>TIME_SPAN) {
			notify();
		}
	}

	public synchronized TransferData7 getShareData() {
		//
		if(sharedQueue.size()>0){
			TransferData7 c = sharedQueue.get(0);
			sharedQueue.remove(0);
			return c;
		}
		else{
		//
			try {
				wait();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			TransferData7 c = sharedQueue.get(0);
			sharedQueue.remove(0);
			return c;
		}
	}
}
