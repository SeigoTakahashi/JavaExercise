import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderControlDBAccess（DAO）
 */
public class OrderControlDBAccessRefactor {

	/**
	 * 商品リストを取得する
	 *
	 * @return 商品リスト
	 */
	public List<ProductBean> findAllProducts() {

		List<ProductBean> list = new ArrayList<ProductBean>();
		String dburl = "jdbc:mysql://localhost:3306/受注管理db";
		String sql = "SELECT 商品ID, 商品名, 単価 FROM 商品マスタ;";
		try (
				Connection con = DriverManager.getConnection(dburl, "user1", "pass1");
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next() == true) {
				String id = rs.getString("商品ID");
				String name = rs.getString("商品名");
				int price = rs.getInt("単価");
				ProductBean bean = new ProductBean(id, name, price);
				list.add(bean);
			}

		} catch (SQLException e) {
			System.out.println(
					"DB切断時にエラーが発生しました（商品検索）。");
			e.printStackTrace();
		}
		return list;
	}
}
