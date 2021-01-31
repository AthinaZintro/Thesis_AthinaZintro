package query;


import register.IFileManager;

public interface IQueryServer {

	Integer execute(IFileManager manager, String query ) ;

}
