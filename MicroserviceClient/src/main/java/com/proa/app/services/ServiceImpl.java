package com.proa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proa.app.dao.IclientDao;
import com.proa.app.entities.Client;
import com.proa.app.exceptions.ClientNotFoundException;

//clase que implementa la interface IService y nos pide todo los metodos de la interface

//la clase la convierte en una clase inyectable con @Service
@Service
public class ServiceImpl implements IService {

	//inyeccion de dependencia
	@Autowired
	private IclientDao dao;
	
	@Override
	public boolean insert(Client c) {
		
		Client response = null;
		
		if(c.getId()==0) { 
		response= dao.save(c);
		}
		
		return response!=null;
	}

	@Override
	public List<Client> selectAll() {
		
		return (List<Client>)dao.findAll();
	}

	@Override
	public boolean update(Client c) throws ClientNotFoundException {
		if(dao.existsById(c.getId())) {
			var response=dao.save(c);
			return response!=null;
		}
		var message ="Client "+c.getId()+"not found"; 
		throw new ClientNotFoundException(message);
	}

	@Override
	public Client findById(long id)throws ClientNotFoundException {
		
		return dao.findById(id)
				.orElseThrow(
						()->new ClientNotFoundException("Client "+id+"not found")
						);
	}

	@Override
	public boolean delete(long id) throws ClientNotFoundException {
		if(dao.existsById(id)) {
			dao.deleteById(id);
			return true;
		}
		throw new ClientNotFoundException("Client "+id+"not found");
	}
	
}

