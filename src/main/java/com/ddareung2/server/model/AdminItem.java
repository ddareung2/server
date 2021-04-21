package com.ddareung2.server.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Entity(name="admin") 
public class AdminItem extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, insertable = false)
	private long id;
	
	@Column(name = "userName", nullable = false, updatable = true, insertable = true)
	private String userName;
	@Column(name = "name", nullable = false, updatable = true, insertable = true)
	private String name;
	@Column(name = "password", nullable = false, updatable = true, insertable = true)
	private String password;
	
	@OneToOne(targetEntity = AdminRoleItem.class)
	@JoinColumn(name="role", referencedColumnName = "name", nullable = false, updatable = true, insertable = true)
	private String role;
	
	@Column(name = "modified_at", nullable = false, updatable = true, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime modifiedAt;
	@Column(name = "status", nullable = false, updatable = true, insertable = false, columnDefinition = "INTEGER DEFAULT 1")
	private int status;
	
	public AdminItem() {}
	
	@Builder
	public AdminItem(String userName, String name, String password, String role, LocalDateTime createAt,
			LocalDateTime modifiedAt, int status) {
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.role = role;
		this.modifiedAt = modifiedAt;
		this.status = status;
	}
}
