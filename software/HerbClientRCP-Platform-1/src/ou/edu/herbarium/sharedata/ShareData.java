package ou.edu.herbarium.sharedata;

import java.util.ArrayList;

public class ShareData {
	
	public static int TIME_SPAN = 3;
	private ArrayList<Object> sharedQueue = new ArrayList<Object>();
	
	public synchronized void setShareChar(Object c) {
		this.sharedQueue.add(c);
		System.out.println("add image information to message queue");
		if (sharedQueue.size()>TIME_SPAN||c==null) {
			notify();
		}
	}

	public synchronized Object getShareChar() {
		try {
			wait();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("getter notified");
		Object c = sharedQueue.get(0);
		sharedQueue.remove(0);
		return c;
	}
}
