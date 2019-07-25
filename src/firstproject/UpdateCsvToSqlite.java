package firstproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UpdateCsvToSqlite {
	public static void main(String[] args) {
		File f1 = new File("../Account.csv");
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite://../SqliteDatabase/Tables.db");
			if (conn != null) {
				System.out.println("Connected to the database");
				System.out.println(conn);
			}
			 DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
			 ResultSet rs = dm.getTables(null, null, "%", null);
						while (rs.next()) {
						System.out.println(rs.getString(3));
		}
			PreparedStatement ps;
			BufferedReader br = new BufferedReader(new FileReader(f1));
			br.readLine(); //header 
			String line;
			while ((line = br.readLine()) != null) {
				String[] arr = line.trim().split(",");
				System.out.println(Arrays.toString(arr));
				ps = conn.prepareStatement("insert into Account (Id, Name) values ('"+arr[0]+"','"+arr[0]+"')");
				ps.executeUpdate();
			}

			br.close();
			conn.close();
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
