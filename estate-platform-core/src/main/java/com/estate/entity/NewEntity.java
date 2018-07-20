package com.estate.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "new")
public class NewEntity extends BaseEntity {

	private static final long serialVersionUID = -5633320242625747426L;

	@Column
	private String code;

	@Column
	private String thumbnail;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private String shortDescription;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
}
