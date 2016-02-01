package ou.edu.herbarium.sharedata;

import java.util.ArrayList;



public class ShareData5 {
	
	public static int TIME_SPAN = 0;
	private ArrayList<TransferData5> sharedQueue = new ArrayList<TransferData5>();
	
	public synchronized void setShareData(TransferData5 c) {
		this.sharedQueue.add(c);
		if (sharedQueue.size()>TIME_SPAN) {
			notify();
		}
	}

	public synchronized TransferData5 getShareData() {
		//
		if(sharedQueue.size()>0){
			TransferData5 c = sharedQueue.get(0);
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
			TransferData5 c = sharedQueue.get(0);
			sharedQueue.remove(0);
			return c;
		}
	}

	public synchronized void setShareFirstData(TransferData5 d5) {
		// TODO Auto-generated method stub
		this.sharedQueue.add(0,d5);
		if (sharedQueue.size()>TIME_SPAN) {
			notify();
		}
	}
	
}
