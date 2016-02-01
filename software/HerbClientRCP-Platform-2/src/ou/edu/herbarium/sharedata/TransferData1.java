package ou.edu.herbarium.sharedata;

import java.net.Socket;
import java.util.ArrayList;

import ou.edu.herbarium.wsclient.Specimen;

public class TransferData1 {
	private ArrayList<Specimen> specs;
	private Socket socket;
	private ShareData2 s2;
	private ShareData3 s3;
	public TransferData1(ArrayList<Specimen> spec, Socket socket ,ShareData2 s2,ShareData3 s3) {
		super();
		this.socket = socket;
		this.specs = spec;
		this.s2 = s2;
		this.setS3(s3);
	}
	public ArrayList<Specimen> getSpec() {
		return specs;
	}
	public void setSpec(ArrayList<Specimen> spec) {
		this.specs = spec;
	}
	public ShareData2 getS2() {
		return s2;
	}
	public void setS2(ShareData2 s2) {
		this.s2 = s2;
	}
	public ShareData3 getS3() {
		return s3;
	}
	public void setS3(ShareData3 s3) {
		this.s3 = s3;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
