package com.masterdevskills.cha2.ext3;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.stream.Collectors.collectingAndThen;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 07 August 2020
 */
@Getter
public class Document {
	private final String title;
	private final List<Page> pages;

	public Document(String title, List<Page> pages) {
		this.title = title;
		this.pages = List.copyOf(pages);
	}

	private Page appendFooter(Page original) {
		String footer = "Document: " + getTitle();
		return new Page(format("%s%n%s", original.getContent(), footer));
	}

	private Document copyWithPages(List<Page> newPages) {
		return new Document(title, newPages);
	}

	public Document copyWithFooter() {
		return getPages().stream()
						.map(this::appendFooter)
						.collect(collectingAndThen(Collectors.toList(), this::copyWithPages));
	}

	@Getter
	@Setter
	public static class Page {
		private final String content;

		public Page(String content) {
			this.content = content;
		}
	}
}
