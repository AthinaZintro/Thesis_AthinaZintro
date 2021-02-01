package query;


import register.IFileManager;

public interface IQueryServer {

	int execute(IFileManager manager, String query ) ;

}
