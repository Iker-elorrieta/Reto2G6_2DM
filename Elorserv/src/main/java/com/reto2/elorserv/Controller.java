package com.reto2.elorserv;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.reto2.elorserv.modelo.*;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // todos los endpoints
				.allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowCredentials(true);

	}
	/* USUARIOS */
	@GetMapping("/usuarios/")
	public List<Users> getUsuarios() {
		return Users.getAllUsuarios();
	}
	
	@GetMapping("/usuarios/{id}")
	public Users getUsuarioPorID(@PathVariable int id) {
		return new Users(id).getUsuarioPorID();
	}


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String contrasena) {
		try {
			Users user = new Users(username,contrasena).iniciarSesion();
			return ResponseEntity.ok(user);

		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}

	@PostMapping("/usuarios/crear")
	public ResponseEntity<String> crearUsuario(@RequestBody Users user) {
		try {
			user.crearUsuario();
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> borrarUsuario(@PathVariable int id) {
		try {
			new Users(id).borrarUsuario();
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al borrar el usuario");
		}
	}
	/* CENTROS */
	@GetMapping("/centros")
	public ResponseEntity<?> getAllCentros() {
		return ResponseEntity.ok(Centros.getAllCentros());
	}

	@GetMapping("/centros/{id}")
	public ResponseEntity<?> getAllCentrosById(@PathVariable Integer id) {
		Centros c = new Centros();
		c.setCCEN(id);
		return ResponseEntity.ok(c.getCentroById());
	}
	/* REUNIONES */
	@GetMapping("/reuniones/")
	public List<Reuniones> getReuniones() {
		return Reuniones.getAllReuniones();
	}

}