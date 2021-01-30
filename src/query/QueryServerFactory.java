package query;

public class QueryServerFactory {

		public IQueryServer generateExecuteQuery() {
			return new QueryServer();
		}
	
}
