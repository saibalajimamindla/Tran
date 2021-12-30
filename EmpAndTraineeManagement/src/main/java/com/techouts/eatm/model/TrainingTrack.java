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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "training_tracks")
public class TrainingTrack {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true, name = "track_name")
	private String trackName;

	@Column(name = "track_duration")
	private long trackDuration;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "techtrack_tech", joinColumns = { @JoinColumn(name = "techtrack_id") }, inverseJoinColumns = {
			@JoinColumn(name = "tech_id") })
	private Set<Technology> technologies = new HashSet<>();

	public TrainingTrack() {
		super();
	}

	public TrainingTrack(String trackName) {
		super();
		this.trackName = trackName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public Set<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(Set<Technology> technologies) {
		this.technologies = technologies;
	}

	public long getTrackDuration() {
		return trackDuration;
	}

	public void setTrackDuration(long trackDuration) {
		this.trackDuration = trackDuration;
	}

	@Override
	public String toString() {
		return "TrainingTrack [id=" + id + ", trackName=" + trackName + ", trackDuration=" + trackDuration
				+ ", technologies=" + technologies + "]";
	}

}
