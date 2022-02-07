package com.buenoezandro.app.ws.ui.model.response;

import java.time.LocalDate;

public class ErrorMessage {
	private LocalDate timestamp;
	private String message;

	public ErrorMessage() {
	}

	public ErrorMessage(LocalDate timestamp, String message) {
		this.timestamp = timestamp;
		this.message = message;
	}

	public LocalDate getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
