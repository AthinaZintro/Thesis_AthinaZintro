package query;

import java.io.IOException;

import register.IFileManager;

public interface IQueryServer {

	Integer execute(IFileManager manager, String query ) throws IOException;

}
