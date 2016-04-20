package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCConnection {
	public static Connection connection;
	public static PreparedStatement pStatement;
	public static Statement statement;
	public static ResultSet resultSet;
	public static final String userid ="yinyixin";
	public static final String password ="yinyixin";
	public static final String URL = "jdbc:mysql://db4free.net:3306/nasa_mars_system?autoReconnect=true&useSSL=false";
	public static final String driver = "com.mysql.jdbc.Driver";

	public static void setConnection(){
		connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(URL,userid,password);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void executeQuery(String sqlString)
	{
		resultSet = null;
		try {
			resultSet = statement.executeQuery(sqlString);
			//System.out.println("SQL STATEMENT RUN");
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("SQL STATEMENT ERROR");
			//System.exit(0);
			return;
		}
	}
	public static void executeUpdate(String sqlString)
	{
		resultSet = null;
		try {
			statement.executeUpdate(sqlString);	
			//System.out.println("SQL UPDATE RUN");

		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("SQL UPDATE ERROR");
			//System.exit(0);
			return;
		}
	}
	
//	public static void executePreparedQuery(String sqlString, int[] params)
//	{
//		resultSet = null;
//		try {
//			pStatement = connection.prepareStatement(sqlString);
//			
//			for (int i = 0; i < params.length; i++){
//				pStatement.setInt(i + 1, params[i]);
//			}
//			resultSet = pStatement.executeQuery();
//			//System.out.println("SQL PRESTATEMENT RUN");
//
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//			System.out.println("SQL PRESTATEMENT ERROR");
//			//System.exit(0);
//			return;
//		}
//	}
	
//	public static void executePreparedUpdate()
//	{
//		resultSet = null;
//		
//		try {
//			pStatement.executeUpdate();	
//			//System.out.println("SQL UPDATE RUN");
//
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//			System.out.println("SQL UPDATE ERROR");
//			System.exit(0);
//			return;
//		}
//	}
	
	public static boolean next()
	{
		try {
			//System.out.println("NEXT");
			return resultSet.next();

		} catch (SQLException e1) {
			System.out.println("ERROR");
			System.exit(0);
		}
		return false;
	}
	
//	public static void main(String[] args) {
//		JDBCConnection jd = new JDBCConnection();
//		jd.setConnection();
//		String sql = "select * from test where id = ?;";
//		int param[] = {2};
////		jd.executeQuery(sql);
//		jd.executePreparedQuery(sql, param);
//		while(jd.next())
//		{
//			try {
//				System.out.println(jd.resultSet.getString("name"));
//				System.out.println(jd.resultSet.getString("age"));
//
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}	

}
