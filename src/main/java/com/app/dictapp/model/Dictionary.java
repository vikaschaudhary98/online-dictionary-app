package com.app.dictapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="dictionary")
public class Dictionary {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(length= 100 ,nullable=false)
private String word;
@Column(length=200,nullable=false)
private String meaning;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getWord() {
	return word;
}
public void setWord(String word) {
	this.word = word;
}
public String getMeaning() {
	return meaning;
}
public void setMeaning(String meaning) {
	this.meaning = meaning;
}

}
