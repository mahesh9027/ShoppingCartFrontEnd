package com.niit.shoppingcart.controller;




import java.util.logging.Logger;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.model.Cart;

public class CartController {
	
	@Autowired
	private Cart cart;
	@Autowired
	private CartDAO cartDAO;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String liStCategories(Model model) {
		
		model.addAttribute("cart", cart);
		model.addAttribute("cartList", this.cartDAO.list());
		return "cart";
	}

	@RequestMapping(value = "/cart/add", method = RequestMethod.POST)
	public String addCart(@ModelAttribute("cart") Cart cart){
		cartDAO.saveOrUpdate(cart);
		return "home";
	}
	
	@RequestMapping("cart/remove/{id}")
	public String deleteCart(@PathVariable("id") String id, ModelMap model) throws Exception{
	
	cartDAO.delete(id);
	return "cart";
	}
	@RequestMapping("cart/edit/{id}")
	public String editCart(@PathVariable("id") String id,Model model){
		cart= cartDAO.get(id);
		model.addAttribute("cart", cart);
		model.addAttribute("cartList", cartDAO.list());
		return "cart";
	}
}
