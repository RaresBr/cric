package ro.cric.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORGANIZATIONS")
public class Organization {

	@Id
	@Column(name = "ORGANIZATION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long organizationId;

	@Column(name = "USERNAME", unique = true)
	private String username;

	@Column(name = "EMAIL", unique = true)
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ALERT_ID")
	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Alert> alerts = new ArrayList<>();

	public Organization() {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
