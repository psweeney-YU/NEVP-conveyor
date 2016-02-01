package ou.edu.herbarium.thread2;

import ou.edu.herbarium.wsclient.Specimen;

public class TransferData {
	byte [] bytes2;
	String tooltip;
	String name;
	private Specimen spec;
	public TransferData(byte [] bytes2,String tooltip, String name,Specimen spec) {
		super();
		this.bytes2 = bytes2;
		this.tooltip = tooltip;
		this.name = name;
		this.setSpec(spec);
	}
	public byte[] getBytes2() {
		return bytes2;
	}
	public void setBytes2(byte[] bytes2) {
		this.bytes2 = bytes2;
	}
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the spec
	 */
	public Specimen getSpec() {
		return spec;
	}
	/**
	 * @param spec the spec to set
	 */
	public void setSpec(Specimen spec) {
		this.spec = spec;
	}
}
