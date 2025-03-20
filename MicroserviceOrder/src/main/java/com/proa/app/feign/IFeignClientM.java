package com.proa.app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proa.app.entities.Client;

//ME PUEDA CONECTAR AL MICRO SERVICIO CLIENTE
//NOMBRE DEL MICROSERVICIO Y RUTA DEL MICROSERVICIO
//@FeignClient(name="micro-client", url="http://localhost:8081/client") sin eureka
@FeignClient(name="micro-client")
public interface IFeignClientM {
	
	//que recibe y que retorna
	@GetMapping("/client/id")
	Client selectById(@RequestParam long id);
	
	
}
