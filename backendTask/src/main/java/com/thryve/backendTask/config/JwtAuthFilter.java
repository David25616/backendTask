package com.thryve.backendTask.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.thryve.backendTask.controller.AuthTokenValidator;

public class JwtAuthFilter extends OncePerRequestFilter {

	private final AuthTokenValidator authTokenValidator;

	public JwtAuthFilter(AuthTokenValidator authTokenValidator) {
		super();
		this.authTokenValidator = authTokenValidator;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = "";
		String authorization = request.getHeader("Authorization");
		if (null != authorization && authorization.startsWith("Bearer"))
			token = authorization.split(" ")[1];

		if (authTokenValidator.validateAuthToken(token)) {
			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken("example", null, null);
			upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(upat);
		}

		filterChain.doFilter(request, response);
	}

}
