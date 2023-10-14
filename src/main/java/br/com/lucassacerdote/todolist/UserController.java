package br.com.lucassacerdote.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.lucassacerdote.todolist.user.IUserRepository;
import br.com.lucassacerdote.todolist.user.UserModel;


/*
 * Modificador
 * public
 * private
 * protected
 */
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

/**
 * String (texto)
 * Integer (int) numeros inteiros
 * Double (double) numeros 0.0000
 * Float (float) Número de 0.000
 * Char (A C)
 * Date (data)
 * Void
 * body
 */
//https://localhost:8080/h2-console
@PostMapping("/")
public ResponseEntity create(@RequestBody UserModel userModel) {
 var user = this.userRepository.findByUsername(userModel.getUsername());


if(user != null) {
  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
}
 var passwordHashred = BCrypt.withDefaults()
 .hashToString(12, userModel.getPassword().toCharArray());
 userModel.setPassword((passwordHashred));
 

var userCreated = this.userRepository.save(userModel);
return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
}
}