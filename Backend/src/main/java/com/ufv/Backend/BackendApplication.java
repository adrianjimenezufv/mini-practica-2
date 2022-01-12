package com.ufv.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		Controller.Actualizar();
		Controller.parser.Guardar_datos("Data/Datos2.JSON");
		SpringApplication.run(BackendApplication.class, args);
	}

}
