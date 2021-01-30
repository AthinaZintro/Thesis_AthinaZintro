package register;

import java.util.HashMap;

public interface IFileManager {

	String register(String inFilePath, String columnsDelimiter, String[] columnsNames, String columnsCount);

	HashMap<String, String> getFileAndPathCollection();

}