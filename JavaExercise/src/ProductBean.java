import java.io.Serializable;

// ProductBeanクラス（DTO）の定義
public class ProductBean implements Serializable {
	private String id;		//商品ID
	private String name;	//商品名
	private int price;		//単価
	public ProductBean(String id, String name, int price) {
		setId(id);
		setName(name);
		setPrice(price);
	}
	public void setId(String id) {		//商品IDを設定する
		this.id = id;
	}
	public String getId() {				//商品IDを取得する
		return id;
	}
	public void setName(String name) {	//商品名を設定する
		this.name = name;
	}
	public String getName() {			//商品名を取得する
		return name;
	}
	public void setPrice(int price) {	//単価を設定する
		this.price = price;
	}
	public int getPrice() {				//単価を取得する
		return price;
	}
}
