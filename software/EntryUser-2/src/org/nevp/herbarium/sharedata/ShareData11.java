package org.nevp.herbarium.sharedata;

import java.util.ArrayList;

public class ShareData11 extends Thread{
	

	public static int TIME_SPAN = 0;
	private ArrayList<String> sharedQueue = new ArrayList<String>();
	
	public synchronized void setShareData(String c) {
		this.sharedQueue.add(c);
		if (sharedQueue.size()>TIME_SPAN) {
			notify();
		}
	}

	public synchronized String getShareData() {
		//
		if(sharedQueue.size()>0){
			String c = sharedQueue.get(0);
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
			String c = sharedQueue.get(0);
			sharedQueue.remove(0);
			return c;
		}
	}
}
