
// ①java.sqlパッケージのクラスをインポートする
import java.sql.Connection; //DB接続管理
import java.sql.DriverManager; //JDBCドライバ管理
import java.sql.PreparedStatement; //SQL文の実行管理
import java.sql.ResultSet; //SQL文の実行結果
import java.sql.SQLException; //SQL関連の例外

public class 演習3 {
	public static void main(String[] args) {
		// 変数の初期化
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// ②JDBCドライバをロードする
			Class.forName("com.mysql.cj.jdbc.Driver");
			// ③DBMSとの接続を確立する
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/受注管理db",
					"user1",
					"pass1");
			// ④SQL文を作成する
			String sql1 = "SELECT 商品ID,商品名,単価 FROM 商品マスタ WHERE 商品ID = 'F-006';";
			// ⑤SQL文を実行するための準備を行う
			pstmt = con.prepareStatement(sql1);
			// ⑥SQL文を実行してDBMSから結果を受信する
			rs = pstmt.executeQuery();

			if(rs.next() == false) {
				String sql2 = "INSERT INTO 商品マスタ VALUES('F-006','ピンクグレープフルーツ',1400);";
				pstmt = con.prepareStatement(sql2);
				int count1 = pstmt.executeUpdate();
			} else {
				String sql3 = "UPDATE 商品マスタ SET 単価 = 1500 WHERE 商品ID = 'F-006';";
				pstmt = con.prepareStatement(sql3);
				int count2 = pstmt.executeUpdate();
			}
			String sql = "SELECT 商品ID, 商品名, 単価 FROM 商品マスタ;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("商品ID\t商品名\t\t\t単価");

			// ⑦実行結果を含んだインスタンスからデータを取り出す
			while (rs.next() == true) {
				String id = rs.getString("商品ID");
				String name = rs.getString("商品名");
				int price = rs.getInt("単価");
				System.out.println(id + "\t" + name + "\t\t\\" + price);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
			// ⑧DBMSから切断する
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}
}