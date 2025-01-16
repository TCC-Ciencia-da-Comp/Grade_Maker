package com.datamonki.ApiCadastro.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtUtil {
	private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final long expirationTime = 1000 * 60 *60 * 10; //10 horas em milissegundos
	
	public String generateToken(String userName) {
		return Jwts.builder()
				.setSubject(userName) //Define o usuário do payload
				.setIssuedAt(new Date()) //Define a data de emissão
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(secretKey) //Assina o token com a chave
				.compact(); //Compacta tudo em uma string
	}
	
	//Captura o nome do usuário do token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }
    //Validação se o nome do usuário está correta condizente com o do toke, caso não tenha espirado
    public boolean validateToken(String token, String userName) {
    	return userName.equals(extractUsername(token)) && !isTokenExpired(token);
    }
    //Caso o token tenha espirado retorna true
    private boolean isTokenExpired(String token) {
    	return getClaims(token).getExpiration().before(new Date());
    }
    //Usa chave para verificar a integridade do token e decodifica o toke, para puxar as informações do payload
    private Claims getClaims(String token) {
    	return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }
}
