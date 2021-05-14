package com.ddareung2.server.voc;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ddareung2.server.common.BaseTimeEntity;
import com.ddareung2.server.station.StationInformation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@Entity(name="voc_question")
public class VocQuestion extends BaseTimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
	private long id;
	
//	@Column(name = "category", nullable = false, updatable = true, insertable = true)
	@OneToOne(fetch = FetchType.LAZY, targetEntity = VocCategory.class)
	@JoinColumn(name="category", referencedColumnName = "name", nullable = false)
	private VocCategory category;
	
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "content", nullable = false)
	private String content;
	@Column(name = "username")
	private String username;
	@Column(name = "email")
	private String email;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = StationInformation.class)
	@JoinColumn(name="station_id", referencedColumnName = "station_id", nullable = false)
	private StationInformation stationId;
	
	
	@Column(name = "need_reply", nullable = false)
	private int needReply;
	
	public VocQuestion() {}

	@Builder
	public VocQuestion(VocCategory category, String title, String content, String username, String email, StationInformation stationId,
			int needReply, LocalDateTime createAt) {
		this.category = category;
		this.title = title;
		this.content = content;
		this.username = username;
		this.email = email;
		this.stationId = stationId;
		this.needReply = needReply;
	}
}
