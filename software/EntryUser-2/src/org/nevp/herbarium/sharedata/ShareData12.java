package org.nevp.herbarium.sharedata;

import java.util.ArrayList;

public class ShareData12 extends Thread{
	

	public static int TIME_SPAN = 0;
	private ArrayList<String> sharedQueue = new ArrayList<String>();
	
	public synchronized void setShareData(String c) {
		this.sharedQueue.add(c);
		if (sharedQueue.size()>TIME_SPAN) {
			System.out.println("set c = "+c);
			notify();
		}
	}

	public synchronized String getShareData() {
		//
		if(sharedQueue.size()>0){
			String c = sharedQueue.get(0);
			sharedQueue.remove(0);
			System.out.println("get c = "+c);
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
			System.out.println("get c = "+c);
			return c;
		}
	}
}
