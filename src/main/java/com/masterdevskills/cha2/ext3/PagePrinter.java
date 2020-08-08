package com.masterdevskills.cha2.ext3;

import static java.lang.String.format;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 07 August 2020
 */
public class PagePrinter {
	private final String pageBreak;

	public PagePrinter(String pageBreak) {
		this.pageBreak = pageBreak;
	}

	public String printTitlePage(Document document) {
		return format(
						"%s%n" +
										"%s%n", document.getTitle(), pageBreak);
	}

	public String printPage(Document.Page page) {
		return format(
						"%s%n" +
										"%s%n", page.getContent(), pageBreak);
	}
}
