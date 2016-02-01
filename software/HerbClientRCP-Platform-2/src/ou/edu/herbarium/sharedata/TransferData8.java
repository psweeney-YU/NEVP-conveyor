package ou.edu.herbarium.sharedata;

import java.net.Socket;

import ou.edu.herbarium.wsclient.Specimen;

public class TransferData8 {
	private Specimen spec;
	private String originalFilePath;
	private Socket socket;
	private String qrCode;
	private byte[] byte4;
	public TransferData8(Specimen spec, Socket socket,byte[] byte4
	,String originalFilePath,String qrCode) {
		super();
		this.socket = socket;
		this.spec = spec;
		this.byte4 = byte4;
		this.originalFilePath = originalFilePath;
		this.qrCode = qrCode;
	}
	public Specimen getSpec() {
		return spec;
	}
	public void setSpec(Specimen spec) {
		this.spec = spec;
	}
	public byte[] getByte4() {
		return byte4;
	}
	public void setByte4(byte[] byte4) {
		this.byte4 = byte4;
	}
	public String getOriginalFilePath() {
		return originalFilePath;
	}
	public void setOriginalFilePath(String originalFilePath) {
		this.originalFilePath = originalFilePath;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
