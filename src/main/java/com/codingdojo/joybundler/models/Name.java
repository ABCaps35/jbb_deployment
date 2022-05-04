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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="names")
public class Name {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
		
	@NotEmpty(message="Name is required")
    @Size(min = 3, max = 100, message="Title must be between 3 and 100 characters")
    private String name;
	
	@NotNull(message="Typical gender is required")
    @Min(value = 0, message="Must select a typical gender")
	@Max(value = 2, message="Must select a typical gender")
    private int typicalGender; //0=neutral, 1=feminine, 2=masculine
	
	@NotEmpty(message="Origin is required")
    @Size(min = 3, max = 100, message="Origin must be between 3 and 100 characters")
    private String origin;
    
	@NotEmpty(message="Meaning is required")
    @Size(min = 3, max = 255,  message="Meaning must be between 3 and 255 characters")
    private String meaning;
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="poster_id")
    private User poster;
    
    //new
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	    name = "upvotes", 
	    joinColumns = @JoinColumn(name = "name_id"), 
	    inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> votedUsers;
    
    //new
    @Transient
    private boolean didVote;
    
    public Name() {
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
	public int getTypicalGender() {
		return typicalGender;
	}
	public void setTypicalGender(int typicalGender) {
		this.typicalGender = typicalGender;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public User getPoster() {
		return poster;
	}
	public void setPoster(User poster) {
		this.poster = poster;
	}
	public List<User> getVotedUsers() {
		return votedUsers;
	}
	public void setVotedUsers(List<User> votedUsers) {
		this.votedUsers = votedUsers;
	}
	public boolean getDidVote() {
		return didVote;
	}
	public void setDidVote(boolean didVote) {
		this.didVote = didVote;
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
