package com.techouts.eatm.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "technologies")
public class Technology {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, name = "technology_name")
	private String technologyName;

	@Column(name = "technology_type")
	private String technologyType;

	@Column(name = "technology_duration")
	private long technologyduration;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "technologies")
	private Set<TrainingTrack> tracks = new HashSet<>();

	public Technology() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTechnologyName() {
		return technologyName;
	}

	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}

	public Set<TrainingTrack> getTracks() {
		return tracks;
	}

	public void setTracks(Set<TrainingTrack> tracks) {
		this.tracks = tracks;
	}

	public String getTechnologyType() {
		return technologyType;
	}

	public void setTechnologyType(String technologyType) {
		this.technologyType = technologyType;
	}

	public long getTechnologyduration() {
		return technologyduration;
	}

	public void setTechnologyduration(long technologyduration) {
		this.technologyduration = technologyduration;
	}

	@Override
	public String toString() {
		return "Technology [id=" + id + ", technologyName=" + technologyName + ", technologyType=" + technologyType
				+ ", technologyduration=" + technologyduration + ", tracks=" + tracks + "]";
	}

	

}
