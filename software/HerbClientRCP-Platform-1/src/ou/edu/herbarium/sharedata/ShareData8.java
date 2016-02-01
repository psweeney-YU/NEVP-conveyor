package ou.edu.herbarium.sharedata;

import java.util.ArrayList;

public class ShareData8 {
	public static int TIME_SPAN = 0;
	private ArrayList<TransferData8> sharedQueue = new ArrayList<TransferData8>();
	
	public synchronized void setShareData(TransferData8 c) {
		this.sharedQueue.add(c);
		if (sharedQueue.size()>TIME_SPAN) {
			notify();
		}
	}

	public synchronized TransferData8 getShareData() {
		//
		if(sharedQueue.size()>0){
			TransferData8 c = sharedQueue.get(0);
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
			TransferData8 c = sharedQueue.get(0);
			sharedQueue.remove(0);
			return c;
		}
	}
}
