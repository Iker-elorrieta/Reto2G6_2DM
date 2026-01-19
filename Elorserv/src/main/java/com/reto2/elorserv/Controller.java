package com.reto2.elorserv;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import modelo.*;
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

	@PostMapping("/usuarios/")
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
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @RequestBody Users user) {
		try {
			user.setId(id);
			Users usuarioActualizado = user.actualizarUsuario();
			return ResponseEntity.ok(usuarioActualizado);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	@PutMapping("/usuarios/{id}/contrasena")
	public ResponseEntity<?> cambiarContrasena(@PathVariable int id, @RequestParam String nuevaContrasena) {
		try {
			Users user = new Users(id);
			user.cambiarPassword(nuevaContrasena);
			return ResponseEntity.ok("Contraseña cambiada exitosamente.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
	@GetMapping("/usuarios/{username}/exists")
	public ResponseEntity<?> verificarUsername(@PathVariable String username) {
		try {
			boolean exists = Users.usernameExiste(username,null);
			return ResponseEntity.ok(exists);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}	}
	@GetMapping("/usuarios/{id}/{username}/exists")
	public ResponseEntity<?> verificarUsername(@PathVariable String username, @PathVariable Integer id) {
		try {
			boolean exists = Users.usernameExiste(username,id);
			return ResponseEntity.ok(exists);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
	@GetMapping("/reuniones/{id}")
	public List<Reuniones> getReunionesByUserID(@PathVariable Integer id) {
		return Reuniones.getReunionesByUserID(id);
	}
	@PostMapping("/reuniones/{id}")
	public ResponseEntity<?> cambiarEstadoReunion(@PathVariable Integer id, @RequestParam String nuevoEstado) {
		try {
			Reuniones reunion = new Reuniones(id).cambiarEstadoReunion(Reuniones.EstadoReunion.valueOf(nuevoEstado));
			return ResponseEntity.ok(reunion);
		} catch (IllegalStateException | IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	@PostMapping("/reuniones/")
	public ResponseEntity<?> crearReunion(@RequestBody Reuniones reunion) {
		try {
			Reuniones nuevaReunion = reunion.crearReunion();
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReunion);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/* HORARIOS */
	@GetMapping("/horarios/{id}")
	public ResponseEntity<?> getHorariosByUserID(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(Horarios.getHorariosByUserId(id));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}