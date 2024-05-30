import java.util.List;

/**
 * ExecuteSQL03クラス
 */
public class ExecuteSQL03Refactor {

	/**
	 * 商品リストを表示します
	 *
	 * @param args 実行時引数（使用しない）
	 */
	public static void main(String[] args) {
		// DAOを生成する
		OrderControlDBAccessRefactor dao = new OrderControlDBAccessRefactor();
		// DAOから商品リストを取得する
		List<ProductBean> list = dao.findAllProducts();
		System.out.println("商品ID\t商品名\t\t\t単価");
		for (ProductBean bean : list) {
			System.out.println(bean.getId() + "\t" +
					bean.getName() + "\t\t\\" +
					bean.getPrice());
		}
	}
}
