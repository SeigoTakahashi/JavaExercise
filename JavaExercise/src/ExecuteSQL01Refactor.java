import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SQL実行サンプル
 */
public class ExecuteSQL01Refactor {

	/**
	 * 商品マスタ全件検索実行
	 *
	 * @param args 実行時引数（使用なし）
	 */
	public static void main(String[] args) {

		String dburl = "jdbc:mysql://localhost:3306/受注管理db";
		String sql = "SELECT 商品ID, 商品名, 単価 FROM 商品マスタ;";

		// ③DBMSとの接続を確立, ⑤実行の情報を生成（try で囲むと autoclose でclose 処理が不要）
		try (
				Connection con = DriverManager.getConnection(dburl, "user1", "pass1");
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			// ⑥SQL文を実行してDBMSから結果を受信する
			ResultSet rs = pstmt.executeQuery();

			System.out.println("商品ID\t商品名\t\t\t単価");

			// ⑦実行結果を含んだインスタンスからデータを取り出す
			while (rs.next() == true) {
				String id = rs.getString("商品ID");
				String name = rs.getString("商品名");
				int price = rs.getInt("単価");
				System.out.println(id + "\t" + name + "\t\t\\" + price);
			}

		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
	}
}