package com.github.sejoung.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "Test")
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long key;

	@Column
	private String test;

}
