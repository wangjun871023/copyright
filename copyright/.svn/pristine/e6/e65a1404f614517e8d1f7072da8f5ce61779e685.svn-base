package com.copyright.common.base;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 所有实体对象的抽象基类
 * 
 * @author
 */
public abstract class BaseEntity {
	/**
	 * 
	 */
	public String toString() {
		// ToStringStyle.MULTI_LINE_STYLE
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * 
	 */
	public boolean equals(Object object) {
		return EqualsBuilder.reflectionEquals(this, object);
	}

	/**
	 * 
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}