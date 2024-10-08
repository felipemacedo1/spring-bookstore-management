//package com.jbs.javabookstore.security;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JwtService {
//
//	public static final String SECRET = "6b0f586c4c1c54178854b1e0dbca4e3b6daf03c1eca2e7c09a96064bf6bb173c";
//
//	private Key getSignKey() {
//		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//		return Keys.hmacShaKeyFor(keyBytes);
//	}
//
//	private Claims extractAllClaims(String token) {
//		return Jwts.parserBuilder()
//				.setSigningKey(getSignKey()).build()
//				.parseClaimsJws(token).getBody();
//	}
//
//	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//		final Claims claims = extractAllClaims(token);
//		return claimsResolver.apply(claims);
//	}
//
//	public String extractUsername(String token) {
//		return extractClaim(token, Claims::getSubject);
//	}
//
//	public Date extractExpiration(String token) {
//		return extractClaim(token, Claims::getExpiration);
//	}
//
//	private Boolean isTokenExpired(String token) {
//		return extractExpiration(token).before(new Date());
//	}
//
//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String username = extractUsername(token);
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	}
//
//	private String createToken(Map<String, Object> claims, String username) {
//		return Jwts.builder()
//				.setClaims(claims)
//				.setSubject(username)
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora de validade
//				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//	}
//
//	public String generateToken(String username) {
//		Map<String, Object> claims = new HashMap<>();
//		return createToken(claims, username);
//	}
//}
