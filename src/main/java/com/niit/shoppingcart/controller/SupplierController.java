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
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Supplier;


public class SupplierController {
	private static Logger Log=LoggerFactory.getLogger(SupplierController.class);
	
	@Autowired
	private Supplier supplier;
	@Autowired
	private SupplierDAO supplierDAO;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String liStCategories(Model model) {
		Log.debug("starting of the method listCategories");
		model.addAttribute("supplier", supplier);
		model.addAttribute("supplierList", this.supplierDAO.list());
		Log.debug("End of the method listCategories");
		return "supplier";
	}

	@RequestMapping(value = "/supplier/add", method = RequestMethod.POST)
	public String addSupplier(@ModelAttribute("supplier") Supplier supplier){
		Log.debug("starting of the method addSupplier");
		supplierDAO.saveOrUpdate(supplier);
		Log.debug("Ending of the method addSupplier");
		return "supplier";
	}
	
	@RequestMapping("supplier/remove/{id}")
	public String deleteSupplier(@PathVariable("id") String id, ModelMap model) throws Exception{
	
	supplierDAO.delete(id);
	return "supplier";
	}
	@RequestMapping("supplier/edit/{id}")
	public String editSupplier(@PathVariable("id") String id,Model model){
		Log.debug("starting of the method editSupplier");
		Log.info("Supplier id going edit is :"+id);
		supplier= supplierDAO.get(id);
		model.addAttribute("supplier", supplier);
		model.addAttribute("supplierList", supplierDAO.list());
		return "supplier";
	}
}
