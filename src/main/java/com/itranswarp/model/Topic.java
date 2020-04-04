package com.itranswarp.model;

import com.itranswarp.enums.RefType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "topics", indexes = { @Index(name = "IDX_BOARDID", columnList = "boardId"),
		@Index(name = "IDX_REFID", columnList = "refId"), @Index(name = "IDX_UPDATEDAT", columnList = "updatedAt") })
public class Topic extends AbstractEntity {

	@Column(nullable = false, updatable = false)
	public long boardId;

	@Column(nullable = false, updatable = false)
	public long userId;

	@Column(nullable = false, length = VAR_CHAR_NAME)
	public String userName;

	@Column(nullable = false, length = VAR_CHAR_URL)
	public String userImageUrl;

	@Column(nullable = false, updatable = false, length = VAR_ENUM)
	public RefType refType;

	@Column(nullable = false, updatable = false)
	public long refId;

	@Column(nullable = false)
	public long replyNumber;

	@Column(nullable = false)
	public boolean locked;

	@Column(nullable = false, updatable = false, length = VAR_CHAR_NAME)
	public String name;

	@Column(nullable = false, updatable = false, columnDefinition = "TEXT")
	public String content;

}
