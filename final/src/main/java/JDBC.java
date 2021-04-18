import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	private static String URL = "jdbc:postgresql://localhost:5432/group";
	private static String USER = "postgres";
    private static String PASSWORD= "tianguang";
  

    public static int execute(String sql){
    	int i=0;								//return 0 when failed
    	try {
    		Class.forName("org.postgresql.Driver");
	        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement st= conn.createStatement();
			i=st.executeUpdate(sql);
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("failed");
		}
    	return i;
    }
       
    public static ResultSet result(String sql){
    	ResultSet rs=null;						//return null when failed
    	try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement st= conn.createStatement();
			rs=st.executeQuery("SELECT * FROM users");
			st.close();
			conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return rs;
    }
    
    public static boolean exist(String sql){
    	boolean f=false;
    	try {
    		Class.forName("org.postgresql.Driver");
	        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement st= conn.createStatement();
			f=st.execute(sql);
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("failed");
		}
    	return f;
    }
    
    public JDBC() {
    	String URL = "jdbc:postgresql://localhost:5432/group";
    	String PASSWORD= "tianguang";
    }
    
    
    
    
}
