package com.itranswarp.service;

import com.itranswarp.markdown.Markdown;
import com.itranswarp.model.Text;
import com.itranswarp.util.HashUtil;
import com.itranswarp.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TextService extends AbstractService<Text> {

	@Autowired
    Markdown markdown;

	private static final long EXPIRES_IN_SECONDS = 3600 * 24;
	private static final String KEY_TEXT_PREFIEX = "__text__";

	@Transactional
	public Text createText(String content) {
		String hash = HashUtil.sha256(content);
		Text t = this.db.from(Text.class).where("hash = ?", hash).first();
		if (t != null) {
			return t;
		}
		t = new Text();
		t.id = IdUtil.nextId();
		t.hash = hash;
		t.content = content;
		this.db.insert(t);
		return t;
	}

	public String getTextFromCache(Long textId) {
		final String key = KEY_TEXT_PREFIEX + textId;
		String content = this.redisService.get(key);
		if (content == null) {
			content = this.getById(textId).content;
			this.redisService.set(key, content, EXPIRES_IN_SECONDS);
		}
		return content;
	}

	public String getHtmlFromCache(Long textId) {
		final String key = KEY_TEXT_PREFIEX + textId;
		String content = this.redisService.get(key);
		if (content == null) {
			content = markdown.toHtml(this.getById(textId).content);
			this.redisService.set(key, content, EXPIRES_IN_SECONDS);
		}
		return content;
	}
}
