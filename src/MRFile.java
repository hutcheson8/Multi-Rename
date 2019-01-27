public class MRFile {
	private String extension;
	private String originalName;

	public String commandLine(int index) {
		return "rename " + originalName + ' ' + index + '.' + extension;
	}

	public MRFile(String fileName) {
		originalName = fileName;
		String[] fileNameParts = fileName.split("\\.");
		extension = fileNameParts[fileNameParts.length - 1];
	}
}