package ou.edu.herbarium.sharedata;

import java.util.ArrayList;

public class ShareData6 {
	public static int TIME_SPAN = 0;
	private ArrayList<TransferData6> sharedQueue = new ArrayList<TransferData6>();
	
	public synchronized void setShareData(TransferData6 c) {
		this.sharedQueue.add(c);
		if (sharedQueue.size()>TIME_SPAN) {
			notify();
		}
	}

	public synchronized TransferData6 getShareData() {
		//
		if(sharedQueue.size()>0){
			TransferData6 c = sharedQueue.get(0);
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
			TransferData6 c = sharedQueue.get(0);
			sharedQueue.remove(0);
			return c;
		}
	}
}
