package br.com.generation.blogpessoal.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.generation.blogpessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails{

	private static final long serviceVersionUID =1L;
	
	private String userName;
	
	private String password;
	
	public UserDetailsImpl (Usuario user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}

	public UserDetailsImpl () {}
		
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
			return null;
	}	
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {
		return userName;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}	
	
}
