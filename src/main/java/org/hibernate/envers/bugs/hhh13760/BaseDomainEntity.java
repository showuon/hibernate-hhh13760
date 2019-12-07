package org.hibernate.envers.bugs.hhh13760;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseDomainEntity extends BaseDomainEntityMetadata {
	private static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id = 0;

	protected BaseDomainEntity() {
	}

	protected BaseDomainEntity(Instant timestamp, String who) {
		super(timestamp, who);
	}

	public final long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseDomainEntity other = (BaseDomainEntity) obj;
		return id == other.id;
	}

}
