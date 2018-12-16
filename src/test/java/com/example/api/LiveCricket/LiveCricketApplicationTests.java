package com.example.api.LiveCricket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.api.LiveCricket.controller.AllMatchesController;
import com.example.api.LiveCricket.service.AllMatchesService;

@RunWith(SpringRunner.class)
@WebMvcTest(AllMatchesController.class)
public class LiveCricketApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AllMatchesService mockService;

	private String MOCK_RESPONSE = "Sample Response";
	private String MOCK_MACTCH_ID = "Match_1515.html";

	@Test
	public void testAllLiveMatchesScore() throws Exception {
		when(mockService.getAllCurrentMatches()).thenReturn(MOCK_RESPONSE);
		MvcResult result = mockMvc.perform(get("/v1/matches.html").contentType(MediaType.TEXT_HTML_VALUE))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result.getResponse().getContentAsString());
		assertEquals(result.getResponse().getContentAsString(), MOCK_RESPONSE);
	}

	@Test
	public void testSingleLiveMatchScore() throws Exception {
		when(mockService.getSelectedMatchScore(MOCK_MACTCH_ID)).thenReturn(MOCK_RESPONSE);
		MvcResult result = mockMvc.perform(get("/v1/matchScore/{matchId}", MOCK_MACTCH_ID).contentType(MediaType.TEXT_HTML_VALUE))
				.andExpect(status().isOk()).andReturn();

		assertNotNull(result.getResponse().getContentAsString());
		assertEquals(result.getResponse().getContentAsString(), MOCK_RESPONSE);
	}
}

