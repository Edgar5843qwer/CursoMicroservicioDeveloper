package com.proa.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proa.app.entities.Order;
import com.proa.app.exceptions.OrderNotFoundException;
import com.proa.app.services.IService;


@RestController
@RequestMapping("/order") //http://localhost:port/order
public class MicroController {
	
	//inyeccion de la interface
	@Autowired
	private IService service;
	private static final Logger LOGGER =  LoggerFactory.getLogger(MicroController.class);
	
	//ResponseEntity nos ayuda a convertir la respuesta
	@PostMapping
	public ResponseEntity<String> insert(@RequestParam long idClient,
			                             @RequestParam double total){
	
		try {
			if(service.insert(idClient, total))
				return new ResponseEntity<>("inserted", HttpStatus.CREATED);
			
		} catch (Exception ex) {
			LOGGER.error("ERROR INSERTADO ", ex.getMessage());
		}
		return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Order>>  selectAll(){
		
		return new ResponseEntity<>(service.selectAll(),HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String>  delete(long id){
		
		try {
			if(service.delete(id))
			     return new ResponseEntity<>("Eliminado", HttpStatus.OK);	
			
		} catch (OrderNotFoundException ex) {
			
			LOGGER.info(ex.getLocalizedMessage());
			return new ResponseEntity<>("NO EXISTE", HttpStatus.NOT_FOUND);
			
		} catch (Exception ex) {
			LOGGER.error("ERROR DELETE ", ex.getMessage());
		}
		
		return new ResponseEntity<>("INTERNAL ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
/*
 * 
	  boolean insert(long idClient, double total)throws ClientNotFoundException;
	  List<Order> selectAll();
	  boolean delete(long id) throws OrderNotFoundException;

*/
	
	
}
