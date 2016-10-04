package model;

public class 		ProductPageBean {
	ProductBean		product;
	
	public ProductBean getProduct() {
		return product;
	}
	
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	
	public String			initProductPage(int id)
	{
		System.out.println("ProducPageBean Call");
		try {
			product = new ProductBean(id);
			return "ProductPage";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Error";
		}
	}
}
