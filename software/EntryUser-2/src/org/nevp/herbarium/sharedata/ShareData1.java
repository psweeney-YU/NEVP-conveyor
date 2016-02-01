package org.nevp.herbarium.sharedata;

import java.util.ArrayList;

import org.nevp.herbarium.Activator;


public class ShareData1 {
	
//	public int TIME_SPAN = Activator.bufferSize;//retret
	private ArrayList<TransferData1> sharedQueue = new ArrayList<TransferData1>();
	
	public synchronized void setShareData(TransferData1 c) {
		this.sharedQueue.add(c);
		if (sharedQueue.size()>Activator.bufferSize||c==null) {
			notify();
		}
	}

	public synchronized TransferData1 getShareData() {
		try {
			wait();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		TransferData1 c = sharedQueue.get(0);
		sharedQueue.remove(0);
		return c;
	}
//	}
}
