package metadata;

public class FileMetadata {

	/**
	 * @param filename          The name of the file
	 * @param delimiterOflabels The delimiter that separates the columns of the file
	 * @param numberOflabels    The number of columns of the file
	 * @param labels            The columns of the file
	 */

	public FileMetadata(String filename, String delimiterOflabels, String[] labels, String numberOflabels) {
		this.numberOflabels = Integer.parseInt(numberOflabels);
		this.labels = labels;
		this.filename = filename;
		this.delimiterOflabels = delimiterOflabels;
	}

	private int numberOflabels;
	private String labels[];
	private String filename;
	private String delimiterOflabels;

	/**
	 * @param numberOflabels the numberOflabels to set
	 */
	public void setnumberOflabels(int numberOflabels) {
		this.numberOflabels = numberOflabels;
	}

	/**
	 * @param labels the labels to set
	 */
	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @param delimiterOflabels the delimiterOflabels to set
	 */
	public void setdelimiterOflabels(String delimiterOflabels) {
		this.delimiterOflabels = delimiterOflabels;
	}

	/**
	 * @return the numberOflabels
	 */
	public int getnumberOflabels() {
		return numberOflabels;
	}

	/**
	 * @return the labels
	 */
	public String[] getLabels() {
		return labels;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @return the delimiterOflabels
	 */
	public String getdelimiterOflabels() {
		return delimiterOflabels;
	}

}
