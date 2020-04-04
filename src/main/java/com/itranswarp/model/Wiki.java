package com.itranswarp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wikis")
public class Wiki extends AbstractEntity {

	@Column(nullable = false)
	public long imageId;

	@Column(nullable = false)
	public long textId;

	@Column(nullable = false)
	public long userId;

	@Column(nullable = false, length = VAR_ENUM)
	public String tag;

	@Column(nullable = false, length = VAR_CHAR_NAME)
	public String name;

	@Column(nullable = false, length = VAR_CHAR_DESCRIPTION)
	public String description;

	@Column(nullable = false)
	public long views;

	@Column(nullable = false)
	public long publishAt;

	@Transient
	private List<WikiPage> children = null;

	public void addChild(WikiPage wikiPage) {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		this.children.add(wikiPage);
	}

	@Transient
	public List<WikiPage> getChildren() {
		return this.children == null ? List.of() : this.children;
	}

}
