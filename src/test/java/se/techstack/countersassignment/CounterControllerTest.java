package se.techstack.countersassignment;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import se.techstack.countersassignment.controllers.CounterController;
import se.techstack.countersassignment.service.CounterService;
import se.techstack.countersassignment.vo.CounterVO;
import se.techstack.countersassignment.vo.ResponseVO;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CounterController.class)
public class CounterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CounterService counterService;

	List<CounterVO> mockCounters = Arrays.asList(new CounterVO(1, 5, "Test1", 1), new CounterVO(2, 3, "Test2", 1));
	String sampleCounters = "[{\"id\":1,\"value\":5,\"name\":\"Test1\",\"incrementOrder\":1},{\"id\":2,\"value\":3,"
			+ "\"name\":\"Test2\",\"incrementOrder\":1}]";

	@Test
	public void getCounters() throws Exception {
		Mockito.when(counterService.getCounterList()).thenReturn(mockCounters);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/counters/").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"message\":\"Response returned successfully\",\"status\":\"OK\",\"statusCode\":200,"
				+ "\"data\":[{\"id\":1,\"value\":5,\"name\":\"Test1\",\"incrementOrder\":1},{\"id\":2,\"value\":3,"
				+ "\"name\":\"Test2\",\"incrementOrder\":1}]}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}

	@Test
	public void getByName() throws Exception {
		Mockito.when(counterService.getCounterByName("Test2")).thenReturn(mockCounters.get(1));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/counters/Test2")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"message\":\"Response returned successfully\",\"status\":\"OK\","
				+ "\"statusCode\":200,\"data\":{\"id\":2,\"value\":3,\"name\":\"Test2\",\"incrementOrder\":1}}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}

	@Test
	public void add() throws Exception {
		ResponseVO<CounterVO> mockCreateCounterResponse = new ResponseVO<>(
				String.format("Counter Named '%s' created successfully", "Test3"), HttpStatus.CREATED,
				new CounterVO(3, 1, "Test3", 1));
		String toAddCounterList = "[{\"name\": \"Test3\"}]";
		Mockito.when(counterService.add(Mockito.any())).thenReturn(mockCreateCounterResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/counters/").accept(MediaType.APPLICATION_JSON)
				.content(toAddCounterList).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"message\":\"Counter Named 'Test3' created successfully\",\"status\":\"CREATED\","
				+ "\"statusCode\":201,\"data\":{\"id\":3,\"value\":1,\"name\":\"Test3\",\"incrementOrder\":1}}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}

	@Test
	public void incrementCounterValue() throws Exception {
		CounterVO mockCounter = mockCounters.get(0);
		int incrementedVal = mockCounter.getValue() + mockCounter.getIncrementOrder();
		mockCounter.setValue(incrementedVal);
		Mockito.when(counterService.incrementCounter("Test1")).thenReturn(mockCounter);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/counters/incrementCounterValue/Test1")
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{\"message\":\"The value of the counter Test1 is incremented to 6.\",\"status\":\"OK\","
				+ "\"statusCode\":200,\"data\":{\"id\":1,\"value\":6,\"name\":\"Test1\",\"incrementOrder\":1}}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}
}
