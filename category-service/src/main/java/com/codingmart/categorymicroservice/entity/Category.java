package com.codingmart.categorymicroservice.entity;

import com.codingmart.categorymicroservice.audit.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

import javax.persistence.*;

@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Category extends Auditable {
	
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
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", active=" + active + ", subCategory=" + subCategory + "]";
	}


	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="category_id")
	private List<SubCategory> subCategory;

	public List<SubCategory> getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(List<SubCategory> subCategory) {
		this.subCategory = subCategory;
	}
	
	
}
