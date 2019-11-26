package com.grocery.project;

import java.util.List;

import grocery.Cart;

public class CartWrapper {

	List<Cart> cartItems;

	public List<Cart> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<Cart> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "CartWrapper [cartItems=" + cartItems + "]";
	}

}
