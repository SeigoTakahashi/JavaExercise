import java.util.ArrayList;

// ExecuteSQL03クラスの定義
public class ExecuteSQL03 {
	public static void main(String[] args) {
		// DAOを生成する
		OrderControlDBAccess dao = new OrderControlDBAccess();
		// DAOから商品リストを取得する
		ArrayList<ProductBean> list = dao.findAllProducts();
		System.out.println("商品ID\t商品名\t\t\t単価");
		for(ProductBean bean : list) {
			System.out.println(	bean.getId() + "\t" +
								bean.getName() + "\t\t\\" +
								bean.getPrice());
		}
	}
}
