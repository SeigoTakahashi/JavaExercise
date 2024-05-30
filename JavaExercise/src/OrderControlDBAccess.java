import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// OrderControlDBAccessクラス（DAO）の定義
public class OrderControlDBAccess {
	// DBとの接続を確立する
	private Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/受注管理db",
					"user1",
					"pass1");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。");
			e.printStackTrace();
		}
		return con;
	}

	// DBとの接続を閉じる
	private void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("DB切断時にエラーが発生しました。");
			e.printStackTrace();
		}
	}

	// 商品リストを取得する
	public ArrayList<ProductBean> findAllProducts() {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		try {
			if (con != null) {
				String sql = "SELECT 商品ID, 商品名, 単価 " +
						"FROM 商品マスタ;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next() == true) {
					String id = rs.getString("商品ID");
					String name = rs.getString("商品名");
					int price = rs.getInt("単価");
					ProductBean bean = new ProductBean(id, name, price);
					list.add(bean);
				}
			}
		} catch (SQLException e) {
			System.out.println(
					"DB切断時にエラーが発生しました（商品検索）。");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return list;
	}
}
