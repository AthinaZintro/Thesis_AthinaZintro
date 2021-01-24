package query;

import java.io.IOException;

import register.FileManager;

public interface IExecuteQuery {

	Integer execute(FileManager manager, QueryParsing quer) throws IOException;

}
