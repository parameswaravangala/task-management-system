package com.bank.auth.dto;

import lombok.Getter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails, CredentialsContainer, Serializable {

	private  String password;

	private  final String username;

	private  final boolean accountNonExpired;
	private  final boolean accountNonLocked;
	private  final boolean credentialsNonExpired;
	private  final boolean enabled;
	private  final List<GrantedAuthority> authorities;
	@Getter
    private  final List<GrantedAuthority> roles;

	public UserDetails(String username, String encodedPassword, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, List<GrantedAuthority> authorities, List<GrantedAuthority> roles) {
		this.username = username;
		this.password = encodedPassword;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorities = authorities;
		this.roles = roles;
	}


	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

    @Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public void eraseCredentials() {
		this.password = null;
	}

	public static UserBuilder builder() {
		return new UserBuilder();
	}


	public static final class UserBuilder {

		private String username;

		private String password;

		private List<GrantedAuthority> authorities = new ArrayList<>();
		private List<GrantedAuthority> roles = new ArrayList<>();

		private boolean accountExpired;

		private boolean accountLocked;

		private boolean credentialsExpired;

		private boolean disabled;

		private Function<String, String> passwordEncoder = (password) -> password;


		private UserBuilder() {
		}


		public UserBuilder username(String username) {
			Assert.notNull(username, "username cannot be null");
			this.username = username;
			return this;
		}

		public UserBuilder password(String password) {
			Assert.notNull(password, "password cannot be null");
			this.password = password;
			return this;
		}


		public UserBuilder passwordEncoder(Function<String, String> encoder) {
			Assert.notNull(encoder, "encoder cannot be null");
			this.passwordEncoder = encoder;
			return this;
		}


		public UserBuilder roles(String... roles) {
			List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
			for (String role : roles) {
				Assert.isTrue(!role.startsWith("ROLE_"),
						() -> role + " cannot start with ROLE_ (it is automatically added)");
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
			}
			return roles(authorities);
		}


		public UserBuilder authorities(GrantedAuthority... authorities) {
			Assert.notNull(authorities, "authorities cannot be null");
			return authorities(Arrays.asList(authorities));
		}


		public UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
			Assert.notNull(authorities, "authorities cannot be null");
			this.authorities = new ArrayList<>(authorities);
			return this;
		}

		public UserBuilder roles(Collection<? extends GrantedAuthority> authorities) {
			Assert.notNull(authorities, "authorities cannot be null");
			this.roles = new ArrayList<>(authorities);
			return this;
		}


		public UserBuilder authorities(String... authorities) {
			Assert.notNull(authorities, "authorities cannot be null");
			return authorities(AuthorityUtils.createAuthorityList(authorities));
		}


		public UserBuilder accountExpired(boolean accountExpired) {
			this.accountExpired = accountExpired;
			return this;
		}


		public UserBuilder accountLocked(boolean accountLocked) {
			this.accountLocked = accountLocked;
			return this;
		}


		public UserBuilder credentialsExpired(boolean credentialsExpired) {
			this.credentialsExpired = credentialsExpired;
			return this;
		}


		public UserBuilder disabled(boolean disabled) {
			this.disabled = disabled;
			return this;
		}

		public org.springframework.security.core.userdetails.UserDetails build() {
			String encodedPassword = this.passwordEncoder.apply(this.password);
			return new UserDetails(this.username, encodedPassword, !this.disabled, !this.accountExpired,
					!this.credentialsExpired, !this.accountLocked, this.authorities,this.roles);
		}

	}

}
