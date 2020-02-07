package com.wildcodeschool.skillhub.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// IMPORTANT: Do NOT use lombok @Data, @EqualsAndHashCode or @ToString
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Skill implements Comparable<Skill> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	@NotNull
	private String name;

	private String imageURL;

	@OneToMany(mappedBy = "skill")
	@Builder.Default
	private Set<UserSkill> userSkills = new HashSet<>();

	// Make the Collection read-only
	Set<UserSkill> getUserSkills() {
		return Collections.unmodifiableSet(this.userSkills);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Skill))
			return false;
		Skill other = (Skill) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int compareTo(Skill other) {

		// Use null-safe compare method from Apache StringUtils
		return StringUtils.compare(this.getName(), other.getName());
	}

}
