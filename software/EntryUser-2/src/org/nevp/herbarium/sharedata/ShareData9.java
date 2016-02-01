package org.nevp.herbarium.sharedata;

import java.util.ArrayList;

public class ShareData9 {
	public static int TIME_SPAN = 0;
	private ArrayList<TransferData9> sharedQueue = new ArrayList<TransferData9>();
	
	public synchronized void setShareData(TransferData9 c) {
		this.sharedQueue.add(c);
		if (sharedQueue.size()>TIME_SPAN) {
			notify();
		}
	}

	public synchronized TransferData9 getShareData() {
		//
		if(sharedQueue.size()>0){
			TransferData9 c = sharedQueue.get(0);
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
			TransferData9 c = sharedQueue.get(0);
			sharedQueue.remove(0);
			return c;
		}
	}
}
