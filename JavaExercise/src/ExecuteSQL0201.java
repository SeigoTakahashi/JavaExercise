
// java.sqlパッケージのクラスをインポートする
import java.sql.Connection; //DB接続管理
import java.sql.DriverManager; //JDBCドライバ管理
import java.sql.PreparedStatement; //SQL文の実行管理
import java.sql.SQLException; //SQL関連の例外

public class ExecuteSQL0201 {
	public static void main(String[] args) {
		// 変数の初期化
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// JDBCドライバをロードする
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DBMSとの接続を確立する
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/受注管理db",
					"user1",
					"pass1");
			// SQL文を作成する
			String sql = "INSERT INTO 商品マスタ VALUES(?, ?, ?);";
			// SQL文を実行するための準備を行う
			pstmt = con.prepareStatement(sql);
			// プレースホルダに値を埋め込む
			pstmt.setString(1, "F-006");
			pstmt.setString(2, "ピンクグレープフルーツ");
			pstmt.setInt(3, 1400);
			// SQL文を実行してDBMSから結果を受信する
			int result = pstmt.executeUpdate();

			System.out.println("挿入したレコード件数：" + result);

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
			// DBMSから切断する
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
