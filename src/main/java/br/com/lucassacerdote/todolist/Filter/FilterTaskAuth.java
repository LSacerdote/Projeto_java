package br.com.lucassacerdote.todolist.Filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.lucassacerdote.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.var;

import org.springframework.beans.factory.annotation.Autowired;


@Component
public class FilterTaskAuth extends OncePerRequestFilter{



@Autowired
private IUserRepository userRepository;
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
  
        var servletPath = request.getServletPath();
        if(servletPath.equals("/tasks/")) {
// pegar a autenticação (usuario e senha)
var authorization = request.getHeader("Authorization");

var authEncoded = authorization.substring("Basic". length()).trim();

byte[] authDecode = Base64.getDecoder().decode(authEncoded);

var authString = new String (authDecode);
Base64.getDecoder().decode(authEncoded);

      
String[] credentials = authString.split(":");
String username = credentials[0];
String password = credentials[1];



// validar usuario
var user = this.userRepository.findByUsername(username);
if(user == null) {
response.sendError(401, "Usuário não autorizado");
} else {
//validar a senha x
var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
if(passwordVerify.verified){
  request.setAttribute("idUser", user.getId());
  filterChain.doFilter(request, response);
} else {
  response.sendError(401, "Usuário não autorizado");
}
//segue viagem

  }
      } else  {
        filterChain.doFilter(request, response);
      }

}
        }
