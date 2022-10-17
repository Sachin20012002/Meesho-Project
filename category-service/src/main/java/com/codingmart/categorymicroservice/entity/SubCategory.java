package com.codingmart.categorymicroservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class SubCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private long id;
	
	@Column(nullable=false)
	private String name;
	
	
	@Column(nullable=false)
	private boolean active;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="subCategory_id")
	private List<ChildCategory> childCategory;
	
	public List<ChildCategory> getChildCategory() {
		return childCategory;
	}
	public void setChildCategory(List<ChildCategory> childCategory) {
		this.childCategory = childCategory;
	}
	@Override
	public String toString() {
		return "SubCategory [id=" + id + ", name=" + name + ", active=" + active + ", childCategory=" + childCategory
				+ "]";
	}
   	

}
