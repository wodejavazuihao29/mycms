package com.itranswarp.web.controller;

import com.itranswarp.bean.DownloadBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FileController extends AbstractController {

	String maxAge = "max-age=" + 3600 * 24 * 365;

	@GetMapping("/files/attachments/" + ID)
	public void process(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		process(id, '0', response);
	}

	@GetMapping("/files/attachments/" + ID + "/0")
	public void process0(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		process(id, '0', response);
	}

	@GetMapping("/files/attachments/" + ID + "/l")
	public void processL(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		process(id, 'l', response);
	}

	@GetMapping("/files/attachments/" + ID + "/m")
	public void processM(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		process(id, 'm', response);
	}

	@GetMapping("/files/attachments/" + ID + "/s")
	public void processS(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
		process(id, 's', response);
	}

	void process(long id, char size, HttpServletResponse response) throws IOException {
		DownloadBean bean = attachmentService.downloadAttachment(id, size);
		response.setContentType(bean.mime);
		response.setContentLength(bean.data.length);
		response.setHeader("Cache-Control", maxAge);
		@SuppressWarnings("resource")
        ServletOutputStream output = response.getOutputStream();
		output.write(bean.data);
		output.flush();
	}
}
