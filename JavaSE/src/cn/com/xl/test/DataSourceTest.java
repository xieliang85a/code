package cn.com.xl.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

@SuppressWarnings("all")
public class DataSourceTest {
	public static void main(String[] args) throws Exception {
		/*Hashtable props = new Hashtable();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		props.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		Context ctx = new InitialContext(props);

		Context c = new InitialContext(props);
		DataSource ds = (DataSource) c.lookup("java:/OracleDS");
		Connection conn = ds.getConnection();*/
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		try{Class.forName("oracle.jdbc.driver.OracleDriver");
		// oracle.jdbc.driver.OracleDriver();
//		jdbc:oracle:thin:@127.0.0.1:1521:dbtest
		conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:dbtest","raadmin","raadmin");
		psmt = conn.prepareStatement("select * from rarp.trpma00 where rownum <2");
		rs = psmt.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		int colCount = md.getColumnCount();
		while(rs.next()){
			int i = 1;
			while(i <= colCount){
				System.out.println(md.getColumnName(i) + ":" + rs.getString(i++));
			}
		}
		//System.out.println(conn.getMetaData().getDatabaseProductName());
		}finally{
			try{
			rs.close();
			psmt.close();
			conn.close();
			}finally{}
		}
	}
}
