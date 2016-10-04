package model;
import java.util.Vector;

/**
 * Class for manage shopping Cart
 * 
 *
 */
public class		CartBean {
	private 		Vector<ProductBean>	cart = new Vector<ProductBean>();
	
	
	public Vector<ProductBean> getCart() {
		return cart;
	}

	public void setCart(Vector<ProductBean> cart) {
		this.cart = cart;
	}

	public void		upQuantity(int id) throws ClassNotFoundException
	{
		ProductBean product = new ProductBean(id);
		
		for (int i = 0; i < cart.size(); ++i)
			if (id == cart.elementAt(i).getId()
				&& cart.elementAt(i).getQuantity() < product.getQuantity())
				cart.elementAt(i).setQuantity(cart.elementAt(i).getQuantity() + 1);
	}


	public void		downQuantity(int id)
	{

		for (int i = 0; i < cart.size(); ++i)
			if (id == cart.elementAt(i).getId())
			{
				if (cart.elementAt(i).getQuantity() == 1)
					removeProduct(id);
				else
					cart.elementAt(i).setQuantity(cart.elementAt(i).getQuantity() - 1);
			}

	}
	
	/**
	 * Add product into shopping cart
	 * @param id
	 * @throws ClassNotFoundException
	 */
	public void		addToCart(int id) throws ClassNotFoundException
	{
		ProductBean product = new ProductBean(id);
		product.setQuantity(1);
		this.cart.add(product);
	}
	
	/**
	 * Remove product into shopping cart
	 * @param product
	 */
	public void		removeProduct(int id)
	{
		for (int i = 0; i < cart.size(); ++i)
			if (id == cart.elementAt(i).getId())
				this.cart.remove(i);
	}
}
