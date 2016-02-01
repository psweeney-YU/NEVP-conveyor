package ou.edu.herbarium.sharedata;

import ou.edu.herbarium.wsclient.Specimen;

public class TransferData9 {
	private Specimen spec;
	private String originalFileName;
	private String thumbnailFileName;
	private String checksum;
	public TransferData9(Specimen spec, String originalFileName,
			String thumbnailFileName, String checksum) {
		super();
		this.spec = spec;
		this.originalFileName = originalFileName;
		this.thumbnailFileName = thumbnailFileName;
		this.setChecksum(checksum);
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
	public String getThumbnailFileName() {
		return thumbnailFileName;
	}
	public void setThumbnailFileName(String thumbnailFileName) {
		this.thumbnailFileName = thumbnailFileName;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	
}
