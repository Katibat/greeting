package com.example.greeting.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "table_greeting")
public class Greeting {
	@Id
	@Column(name = "greeting_id")
	private Long greetingId;
	@Column(name = "content")
	private String content;
	@Column(name = "data")
	private LocalDate data;

	public Greeting(String content) {
		this.content = content;
	}

	public Greeting() {
	}

	@Override
	public String toString() {
		return "Greeting{" +
				"greetingId=" + greetingId +
				", content='" + content + '\'' +
				", data=" + data +
				'}';
	}

	public Long getGreetingId() {
		return greetingId;
	}

	public void setGreetingId(Long greetingId) {
		this.greetingId = greetingId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getData() {
		return LocalDate.now();
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}