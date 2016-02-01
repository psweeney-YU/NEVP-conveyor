package org.nevp.herbarium.sharedata;

import java.net.Socket;

import org.nevp.herbarium.wsclient.Specimen;

public class TransferData5 {
	private Specimen spec;
	private ShareData2 s2;
	private ShareData3 s3;
	private String originalFileName;
	public TransferData5(Specimen spec, ShareData2 s2, ShareData3 s3,String originalFileName) {
		super();
		this.spec = spec;
		this.setS2(s2);
		this.setS3(s3);
		this.originalFileName = originalFileName;
	}
	public Specimen getSpec() {
		return spec;
	}
	public void setSpec(Specimen spec) {
		this.spec = spec;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
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
}
