package com.niit.shoppingcart.controller;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.model.Category;


public class CategoryController {
	private static Logger Log=LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired
	private Category category;
	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String liStCategories(Model model) {
		Log.debug("starting of the method listCategories");
		model.addAttribute("category", category);
		model.addAttribute("categoryList", this.categoryDAO.list());
		Log.debug("End of the method listCategories");
		return "category";
	}

	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	public String addCategory(@ModelAttribute("category") Category category){
		Log.debug("starting of the method addCategory");
		categoryDAO.saveOrUpdate(category);
		Log.debug("Ending of the method addCategory");
		return "category";
	}
	
	@RequestMapping("category/remove/{id}")
	public String deleteCategory(@PathVariable("id") String id, ModelMap model) throws Exception{
	
	categoryDAO.delete(id);
	return "category";
	}
	@RequestMapping("category/edit/{id}")
	public String editCategory(@PathVariable("id") String id,Model model){
		Log.debug("starting of the method editCategory");
		Log.info("Category id going edit is :"+id);
		category= categoryDAO.get(id);
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryDAO.list());
		return "category";
	}
}
