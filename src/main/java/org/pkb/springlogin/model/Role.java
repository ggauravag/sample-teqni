package org.pkb.springlogin.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "USER_ROLE")
public class Role implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1290005114048803725L;

	@Column(name = "NAME", unique = true, nullable = false)
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
	private List<User> users;

	/**
	 * 
	 */
	public Role() {
		super();
	}

	/**
	 * @param name
	 * @param id
	 */
	public Role(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
	}

	@Override
	public String getAuthority() {
		return "ROLE_" + name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
