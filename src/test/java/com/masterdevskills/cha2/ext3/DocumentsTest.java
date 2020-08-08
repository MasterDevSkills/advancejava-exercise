package com.masterdevskills.cha2.ext3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.masterdevskills.cha2.ext3.Document.Page;
import static com.masterdevskills.util.CodeUsesMethodReferencesMatcher.usesMethodReferences;
import static com.masterdevskills.util.StringWithComparisonMatcher.isString;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;

/**
 * @author A N M Bazlur Rahman @bazlur_rahman
 * @since 07 August 2020
 */
class DocumentsTest {

	@Test
	void getListOfDocumentTitlesUsingMethodReference() {
		Document expenses = new Document("Expenses",
						Arrays.asList(new Page("TTC : $3"), new Page("Subway: £100")));

		Document toDoList = new Document("ToDo List",
						Arrays.asList(new Page("Learn Lambda expression for 2 hours "), new Page("Post a status on facebook that I konw lambda")));
		Document certificates = new Document("My Certificates",
						Arrays.asList(new Page("MasterDevSkills Certified Lambda Expression Professional"), new Page("MasterDevSkills Certified Concurrency Professional")));

		assertThat(Documents.titlesOf(expenses, toDoList, certificates),
						contains("Expenses", "ToDo List", "My Certificates"));
		assertThat("Use Method Reference", Documents.class, usesMethodReferences("getTitle"));
	}

	@Test
	public void getListOfPageCharacterCountsFromDocumentUsingMethodReference() {
		Document diary = new Document("Hello world", Arrays.asList(
						new Page("Java is the best programming language"),
						new Page("I love lambda Expression"),
						new Page("Stream API is the bested")));

		assertThat(Documents.pageCharacterCounts(diary), contains(37, 24, 24));
		assertThat(Documents.class, usesMethodReferences("characterCount"));
	}

	@Test
	public void printContentsOfDocumentUsingReferenceO() {
		Document diary = new Document("My Diary", Arrays.asList(
						new Page("Today I've learned Lambda Expression"),
						new Page("I'm so excited"),
						new Page("I'm looking forward to having more lessons")));

		assertThat(Documents.print(diary, new PagePrinter("----")),
						isString(format("My Diary%n" +
										"----%n" +
										"Today I've learned Lambda Expression%n" +
										"----%n" +
										"I'm so excited%n" +
										"----%n" +
										"I'm looking forward to having more lessons%n" +
										"----%n")));
		assertThat(Documents.class, allOf(usesMethodReferences("printPage"), usesMethodReferences("append")));
	}

}