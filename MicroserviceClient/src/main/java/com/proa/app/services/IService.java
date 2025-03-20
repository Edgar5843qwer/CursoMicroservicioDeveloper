package com.proa.app.services;

import java.util.List;

import com.proa.app.entities.Client;
import com.proa.app.exceptions.ClientNotFoundException;

//controlar las operaciones que estan fuera de nuestro microservicio
public interface IService {
	
	//insertar un cliente
	boolean insert(Client c);
	
	//selecciona todos los clientes
	List<Client> selectAll();
	
	//actualizar un clienta
	boolean update(Client c)throws ClientNotFoundException;
	
	//selecciona un cliente por id
	Client findById(long id)throws ClientNotFoundException;;
	
	//borrar un cliente
	boolean delete(long id)throws ClientNotFoundException;;

}
