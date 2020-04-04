--数据库初始化脚本----

-- BEGIN generate DDL --

DROP DATABASE IF EXISTS it;
--创建数据库
CREATE DATABASE it;
--使用数据库
USE it;

CREATE TABLE ad_slots (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL,
  height BIGINT NOT NULL,
  numAutoFill BIGINT NOT NULL,
  numSlots BIGINT NOT NULL,
  price BIGINT NOT NULL,
  updatedAt BIGINT NOT NULL,
  version BIGINT NOT NULL,
  width BIGINT NOT NULL,
  alias VARCHAR(32) NOT NULL,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  adAutoFill TEXT NOT NULL,
  PRIMARY KEY(id)
) Engine=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE wiki_pages (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL,
  displayOrder BIGINT NOT NULL,
  parentId BIGINT NOT NULL,
  publishAt BIGINT NOT NULL,
  textId BIGINT NOT NULL,
  updatedAt BIGINT NOT NULL,
  version BIGINT NOT NULL,
  views BIGINT NOT NULL,
  wikiId BIGINT NOT NULL,
  name VARCHAR(100) NOT NULL,
  INDEX IDX_WIKIID (wikiId),
  PRIMARY KEY(id)
) Engine=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE resources (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL,
  updatedAt BIGINT NOT NULL,
  version BIGINT NOT NULL,
  encoding VARCHAR(50) NOT NULL,
  hash VARCHAR(64) NOT NULL,
  content MEDIUMTEXT NOT NULL,
  CONSTRAINT UNI_HASH UNIQUE (hash),
  PRIMARY KEY(id)
) Engine=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE articles (
  id BIGINT NOT NULL,
  categoryId BIGINT NOT NULL '文章分类id',
  createdAt BIGINT NOT NULL '文章创建时间',
  imageId BIGINT NOT NULL '文章图片',
  publishAt BIGINT NOT NULL '文章发布时间',
  textId BIGINT NOT NULL '文本内容id',
  updatedAt BIGINT NOT NULL '文章更新时间',
  userId BIGINT NOT NULL '文章作者',
  version BIGINT NOT NULL '版本号',
  views BIGINT NOT NULL '观点，看法',
  name VARCHAR(100) NOT NULL '文章名称',
  tags VARCHAR(100) NOT NULL '文章标签',
  description VARCHAR(1000) NOT NULL '文章描述',
  INDEX IDX_CAT_PUB (categoryId,publishAt),
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COMMENT='文章表';

CREATE TABLE categories (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL '创建时间',
  displayOrder BIGINT NOT NULL '分类显示顺序',
  updatedAt BIGINT NOT NULL '更新时间',
  version BIGINT NOT NULL '版本号',
  tag VARCHAR(32) NOT NULL '分类标签',
  name VARCHAR(100) NOT NULL '分类名称',
  description VARCHAR(1000) NOT NULL '分类描述',
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COMMENT='文章分类表';

CREATE TABLE boards (
  id BIGINT NOT NULL,
  locked BOOL NOT NULL '版块是否被锁',
  createdAt BIGINT NOT NULL '创建时间',
  displayOrder BIGINT NOT NULL '版块显示顺序',
  topicNumber BIGINT NOT NULL '版块帖子数量',
  updatedAt BIGINT NOT NULL '更新时间',
  version BIGINT NOT NULL '版本号',
  tag VARCHAR(32) NOT NULL '版块标签',
  name VARCHAR(100) NOT NULL '版块名称',
  description VARCHAR(1000) NOT NULL '版块描述',
  CONSTRAINT UNI_TAG UNIQUE (tag),
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COMMENT='版块表';

CREATE TABLE topics (
  id BIGINT NOT NULL,
  locked BOOL NOT NULL '帖子是否被锁',
  boardId BIGINT NOT NULL '版块id',
  createdAt BIGINT NOT NULL '创建时间',
  refId BIGINT NOT NULL '帖子评论的资源id',
  replyNumber BIGINT NOT NULL '帖子被回复的数量',
  updatedAt BIGINT NOT NULL '更新时间',
  userId BIGINT NOT NULL '帖子作者id',
  version BIGINT NOT NULL '版本号',
  refType VARCHAR(50) NOT NULL '帖子评论的资源类型',
  name VARCHAR(100) NOT NULL '帖子名称',
  userName VARCHAR(100) NOT NULL '帖子作者名称',
  userImageUrl VARCHAR(1000) NOT NULL '帖子作者头像',
  content TEXT NOT NULL '帖子内容',
  INDEX IDX_BOARDID (boardId),
  INDEX IDX_REFID (refId),
  INDEX IDX_UPDATEDAT (updatedAt),
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COMMENT='帖子表';


CREATE TABLE attachments (
  id BIGINT NOT NULL,
  height INTEGER NOT NULL,
  width INTEGER NOT NULL,
  createdAt BIGINT NOT NULL,
  resourceId BIGINT NOT NULL,
  size BIGINT NOT NULL,
  updatedAt BIGINT NOT NULL,
  userId BIGINT NOT NULL,
  version BIGINT NOT NULL,
  mime VARCHAR(100) NOT NULL,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
) Engine=INNODB DEFAULT CHARSET=UTF8MB4;


CREATE TABLE single_pages (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL,
  publishAt BIGINT NOT NULL,
  textId BIGINT NOT NULL,
  updatedAt BIGINT NOT NULL,
  version BIGINT NOT NULL,
  name VARCHAR(100) NOT NULL,
  tags VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
) Engine=INNODB DEFAULT CHARSET=UTF8MB4;


CREATE TABLE wikis (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL,
  imageId BIGINT NOT NULL,
  publishAt BIGINT NOT NULL,
  textId BIGINT NOT NULL,
  updatedAt BIGINT NOT NULL,
  userId BIGINT NOT NULL,
  version BIGINT NOT NULL,
  views BIGINT NOT NULL,
  tag VARCHAR(32) NOT NULL,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  PRIMARY KEY(id)
) Engine=INNODB DEFAULT CHARSET=UTF8MB4;


CREATE TABLE ad_periods (
  id BIGINT NOT NULL,
  adSlotId BIGINT NOT NULL,
  createdAt BIGINT NOT NULL,
  displayOrder BIGINT NOT NULL,
  updatedAt BIGINT NOT NULL,
  userId BIGINT NOT NULL,
  version BIGINT NOT NULL,
  endAt VARCHAR(10) NOT NULL,
  startAt VARCHAR(10) NOT NULL,
  PRIMARY KEY(id)
) Engine=INNODB DEFAULT CHARSET=UTF8MB4;


CREATE TABLE replies (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL,
  topicId BIGINT NOT NULL,
  updatedAt BIGINT NOT NULL,
  userId BIGINT NOT NULL,
  version BIGINT NOT NULL,
  userName VARCHAR(100) NOT NULL,
  userImageUrl VARCHAR(1000) NOT NULL,
  content TEXT NOT NULL,
  INDEX IDX_TOPICID (topicId),
  PRIMARY KEY(id)
) Engine=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE texts (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL '创建时间',
  updatedAt BIGINT NOT NULL '更新时间',
  version BIGINT NOT NULL '版本号',
  hash VARCHAR(64) NOT NULL '文本hash',
  content TEXT NOT NULL '文本内容',
  CONSTRAINT UNI_HASH UNIQUE (hash),
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COMMENT='文本表';

CREATE TABLE oauths (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL,
  expiresAt BIGINT NOT NULL,
  updatedAt BIGINT NOT NULL,
  userId BIGINT NOT NULL,
  version BIGINT NOT NULL,
  authProviderId VARCHAR(32) NOT NULL,
  authId VARCHAR(255) NOT NULL,
  authToken VARCHAR(255) NOT NULL,
  CONSTRAINT UNI_AUTH UNIQUE (authProviderId,authId),
  PRIMARY KEY(id)
) Engine=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE settings (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL '创建时间',
  updatedAt BIGINT NOT NULL '更新时间',
  version BIGINT NOT NULL '版本号',
  settingGroup VARCHAR(32) NOT NULL '设置组别',
  settingKey VARCHAR(32) NOT NULL 'key',
  settingValue TEXT NOT NULL 'value',
  CONSTRAINT UNI_GRP_KEY UNIQUE (settingGroup, settingKey),
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COMMENT='网站设置表';

CREATE TABLE navigations (
  id BIGINT NOT NULL,
  blank BOOL NOT NULL '是否在新窗口打开',
  createdAt BIGINT NOT NULL '创建时间',
  displayOrder BIGINT NOT NULL '显示顺序',
  updatedAt BIGINT NOT NULL '更新时间',
  version BIGINT NOT NULL '版本号',
  name VARCHAR(100) NOT NULL '导航名称',
  icon VARCHAR(8192) NOT NULL '导航图标',
  url VARCHAR(1000) NOT NULL '导航url',
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COMMENT='导航表';

CREATE TABLE ad_materials (
  id BIGINT NOT NULL,
  adPeriodId BIGINT NOT NULL,
  createdAt BIGINT NOT NULL,
  imageId BIGINT NOT NULL,
  updatedAt BIGINT NOT NULL,
  version BIGINT NOT NULL,
  weight BIGINT NOT NULL,
  endAt VARCHAR(10) NOT NULL,
  geo VARCHAR(32) NOT NULL,
  startAt VARCHAR(10) NOT NULL,
  tags VARCHAR(100) NOT NULL,
  url VARCHAR(1000) NOT NULL,
  PRIMARY KEY(id)
) Engine=INNODB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE local_auths (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL '创建时间',
  updatedAt BIGINT NOT NULL '更新时间',
  userId BIGINT NOT NULL '用户id',
  version BIGINT NOT NULL '版本号',
  passwd VARCHAR(64) NOT NULL '用户密码',
  salt VARCHAR(64) NOT NULL '摘要盐值',
  CONSTRAINT UNI_UID UNIQUE (userId),
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用户认证表';

CREATE TABLE users (
  id BIGINT NOT NULL,
  createdAt BIGINT NOT NULL '创建时间',
  lockedUntil BIGINT NOT NULL '锁定用户时长',
  updatedAt BIGINT NOT NULL '更新时间',
  version BIGINT NOT NULL '版本号',
  role VARCHAR(50) NOT NULL '用户角色',
  email VARCHAR(100) NOT NULL '登录邮箱',
  name VARCHAR(100) NOT NULL '用户名称',
  imageUrl VARCHAR(1000) NOT NULL '用户头像',
  CONSTRAINT UNI_EMAIL UNIQUE (email),
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COMMENT='用户表';


-- END generate DDL --
