package com.codingdojo.joybundler.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp="^[A-Za-z]*$",message = "User name must contain letters only")
    @NotEmpty(message="Last name is required")
    @Size(min=2, max=40, message="User name must be between 2 and 40 characters")
    private String name;
    
    //Validate: Not existing in database - locate in service
    @NotEmpty(message="Email is required")
    @Email(regexp = ".+@.+\\..+",message="Please enter a valid email address")
    private String email;
    
    @NotEmpty(message="Password is required")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters")
    private String password;
    
    //Validate: Matches password - locate in service
    @Transient
    @NotEmpty(message="Confirm Password is required")
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @OneToMany(mappedBy="poster", fetch = FetchType.LAZY)
    private List<Name> postedNames;
     
    //new
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	    name = "upvotes", 
	    joinColumns = @JoinColumn(name = "user_id"), 
	    inverseJoinColumns = @JoinColumn(name = "name_id")
	)
	private List<Name> votedNames;
  
    public User() {
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}
	public List<Name> getPostedNames() {
		return postedNames;
	}
	public void setPostedNames(List<Name> postedNames) {
		this.postedNames = postedNames;
	}
	public List<Name> getVotedNames() {
		return votedNames;
	}
	public void setVotedNames(List<Name> votedNames) {
		this.votedNames = votedNames;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}
