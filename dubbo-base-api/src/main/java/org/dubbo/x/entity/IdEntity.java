/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package org.dubbo.x.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * saas模式采用uuid
 *
 * @author baiqw
 */
// JPA 基类的标识
@MappedSuperclass
@Getter
@Setter
@ToString
public abstract class IdEntity implements Serializable,Idable {
	protected String id;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", length = 36)
	public String getId() {
		return id;
	}
}
