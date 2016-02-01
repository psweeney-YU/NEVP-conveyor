package ou.edu.herbarium.sharedata;

import java.net.Socket;

import ou.edu.herbarium.wsclient.Specimen;

public class TransferData7 {
	private Specimen spec;
	private Socket socket;
	private String originalFilePath;
	private byte[] byte1;
	private byte[] byte2;
	private byte[] byte3;
	private byte[] byte4;
	public TransferData7(Specimen spec,Socket socket,byte[] byte1
	, byte[] byte2
	, byte[] byte3
	, byte[] byte4,String originalFilePath) {
		super();
		this.spec = spec;
		this.setSocket(socket);
		this.byte1 = byte1;
		this.byte2 = byte2;
		this.byte3 = byte3;
		this.byte4 = byte4;
		this.originalFilePath = originalFilePath;
	}
	public Specimen getSpec() {
		return spec;
	}
	public void setSpec(Specimen spec) {
		this.spec = spec;
	}
	public byte[] getByte1() {
		return byte1;
	}
	public void setByte1(byte[] byte1) {
		this.byte1 = byte1;
	}
	public byte[] getByte2() {
		return byte2;
	}
	public void setByte2(byte[] byte2) {
		this.byte2 = byte2;
	}
	public byte[] getByte3() {
		return byte3;
	}
	public void setByte3(byte[] byte3) {
		this.byte3 = byte3;
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
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
}
