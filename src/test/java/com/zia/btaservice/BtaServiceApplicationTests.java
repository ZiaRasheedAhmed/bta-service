package com.zia.btaservice;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zia.btaservice.Controller.BookTrackerController;
import com.zia.btaservice.Model.BookTracker;
import com.zia.btaservice.Repository.IBookRepository;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class BtaServiceApplicationTests {

	@Mock
	private IBookRepository bookRepository;
	@InjectMocks
	private BookTrackerController bookTrackerController;
	private MockMvc mvc;
	private JacksonTester<BookTracker> jsonBook;
	private JacksonTester<List<BookTracker>> jsonBooks;

	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(bookTrackerController).build();
	}

	@Test
	public void canAddBook() throws Exception {
		BookTracker bookTracker = new BookTracker("Spider Man", "Alex", Date.valueOf(LocalDate.now()),
				Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 100, "Good Movie");
		mvc.perform(post("/trackbooks/addBooks").contentType(MediaType.APPLICATION_JSON)
				.content(jsonBook.write(bookTracker).getJson())).andExpect(status().isOk());
	}

	@Test
	public void canReturnBooks() throws Exception {
		BookTracker bookTracker = new BookTracker("Spider Man", "Alex", Date.valueOf(LocalDate.now()),
				Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 100, "Good Movie");
		BookTracker bookTracker1 = new BookTracker("Super Man", "Alex", Date.valueOf(LocalDate.now()),
				Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 100, "Good Movie");
		BookTracker bookTracker2 = new BookTracker("Spider Man", "Alex", Date.valueOf(LocalDate.now()),
				Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 100, "Good Movie");
		List<BookTracker> bookList = new LinkedList<>();
		bookList.add(bookTracker);
		bookList.add(bookTracker1);
		bookList.add(bookTracker2);
		// when it goes to find all on Repository then return booklist
		when(bookRepository.findAll()).thenReturn(bookList);
		// now run mvc perform
		mvc.perform(get("/trackbooks/allbooks").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(jsonBooks.write(bookList).getJson()));
	}

	@Test
	public void canUpdateBook() throws Exception {
		BookTracker bookTracker = new BookTracker(1L, "Spider Man", "Alex", Date.valueOf(LocalDate.now()),
				Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 100, "Good Movie");
		mvc.perform(put("/trackbooks/addBooks").contentType(MediaType.APPLICATION_JSON)
				.content(jsonBook.write(bookTracker).getJson())).andExpect(status().isOk());
	}

	@Test
	public void canReturnBooksById() throws Exception {
		BookTracker bookTracker = new BookTracker(1L, "Spider Man", "Alex", Date.valueOf(LocalDate.now()),
				Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 100, "Good Movie");
		// when it goes to find all on Repository then return booklist
		when(bookRepository.findById(1L)).thenReturn(Optional.of(bookTracker));
		// now run mvc perform
		mvc.perform(get("/trackbooks//list/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().json(jsonBook.write(bookTracker).getJson()));
	}
}