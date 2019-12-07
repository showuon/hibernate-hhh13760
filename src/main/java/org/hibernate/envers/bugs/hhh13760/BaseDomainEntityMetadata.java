package org.hibernate.envers.bugs.hhh13760;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;

import com.sun.istack.NotNull;

@MappedSuperclass
public abstract class BaseDomainEntityMetadata extends Base implements Serializable {

	private static final long serialVersionUID = 1l;

	@Column(name = "created_by", nullable = false, updatable = false)
	@NotNull
	private String createdBy;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	@NotNull
	private Instant createdAt;

	protected BaseDomainEntityMetadata() {
	}

	protected BaseDomainEntityMetadata(Instant timestamp, String who) {
		setCreatedAt(timestamp);
		setCreatedBy(who);
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

}

class ArrayClassUtil {

	static final Class<String[]> STRING_ARRAY_CLASS = ArrayClassUtil.<String>getArrayClass();

	static <T> Class getArrayClass(T... param) {
		return param.getClass();
	}
}
