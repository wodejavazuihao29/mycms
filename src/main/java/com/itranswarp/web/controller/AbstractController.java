package com.itranswarp.web.controller;

import com.itranswarp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected static final String ID = "{id:[0-9]{1,17}}";
	protected static final String ID2 = "{id2:[0-9]{1,17}}";

	@Autowired
	protected EncryptService encryptService;

	@Autowired
	protected AdService adService;

	@Autowired
	protected ArticleService articleService;

	@Autowired
	protected AttachmentService attachmentService;

	@Autowired
	protected BoardService boardService;

	@Autowired
	protected NavigationService navigationService;

	@Autowired
	protected SettingService settingService;

	@Autowired
	protected SinglePageService singlePageService;

	@Autowired
	protected TextService textService;

	@Autowired
	protected UserService userService;

	@Autowired
	protected WikiService wikiService;

}
