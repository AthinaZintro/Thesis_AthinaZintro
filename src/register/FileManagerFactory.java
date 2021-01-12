package register;

public class FileManagerFactory {

	public IFileManager generateFileManager() {
		return new FileManager();
	}
}
