package org.hibernate.envers.bugs.hhh13760;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseDomainEntityVersion extends BaseDomainEntityMetadata {
	private static final long serialVersionUID = 1l;

	public abstract Object getId();

	@Id
	@Column(name = "version", nullable = false, updatable = false)
	private long version;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseDomainEntityVersion other = (BaseDomainEntityVersion) obj;
		return Objects.equals(getId(), other.getId()) && version == other.version;
	}

}
