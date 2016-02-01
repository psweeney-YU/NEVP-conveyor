package org.nevp.herbarium.sharedata;

import java.net.Socket;

import org.nevp.herbarium.wsclient.Specimen;

public class TransferData1 {
	private Specimen spec;
	private ShareData2 s2;
	private ShareData3 s3;
	public TransferData1(Specimen spec,ShareData2 s2,ShareData3 s3) {
		super();
		this.spec = spec;
		this.s2 = s2;
		this.setS3(s3);
	}
	public Specimen getSpec() {
		return spec;
	}
	public void setSpec(Specimen spec) {
		this.spec = spec;
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
