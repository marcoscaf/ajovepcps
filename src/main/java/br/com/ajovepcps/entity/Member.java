package br.com.ajovepcps.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name = "member")
@NamedQueries({ @NamedQuery(name ="findAllMembers", query = "SELECT m FROM Member m") })
public class Member implements Serializable, Cloneable, Comparable<Member> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "id")
	private Integer id;

	@Column(nullable = false, name = "name")
	private String name;

	@Column(nullable = false, name = "date_birth")
	private Date birthDate;

	@Column(nullable = false, name = "gender")
	private Integer gender;

	@Column(nullable = false, name = "civil_status")
	private Integer civilStatus;
	
	@Column(nullable = true, name = "rg")
	private String rg;

	@Column(nullable = true, name = "phone")
	private String phone;

	@Column(nullable = true, name = "email")
	private String email;

	@Column(nullable = true, name = "street")
	private String street;

	@Column(nullable = true, name = "number")
	private String number;

	@Column(nullable = true, name = "neighborhood")
	private String neighborhood;

	@Column(nullable = true, name = "city")
	private String city;

	
	public Integer getId() {
		return id;
		
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getCivilStatus() {
		return civilStatus;
	}

	public void setCivilStatus(Integer civilStatus) {
		this.civilStatus = civilStatus;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}



	@Override
	public Member clone() {
		Member member = null;
		try {
			member = (Member) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return member;
	}

	public void clear() {
		this.setCity(null);
		this.setCivilStatus(null);
		this.setBirthDate(null);
		this.setEmail(null);
		this.setGender(null);
		this.setId(null);
		this.setName(null);
		this.setNeighborhood(null);
		this.setNumber(null);
		this.setPhone(null);
		this.setRg(null);
		this.setStreet(null);
	}

	@Override
	public int compareTo(Member o) {
		// TODO Auto-generated method stub
		return getName().compareTo(o.getName());
	}

}
