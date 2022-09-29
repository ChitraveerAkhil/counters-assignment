package se.techstack.countersassignment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import se.techstack.countersassignment.components.CounterServiceComponent;
import se.techstack.countersassignment.vo.CounterVO;

@SpringBootTest
class CountersAssignmentApplicationTests {

	@Autowired
	CounterServiceComponent counterService;

	private final static String TEST_COUNTER_1 = "Test1";
	private final static String TEST_COUNTER_2 = "Test2";
	private final static String TEST_COUNTER_3 = "Test3";
	private final static String TEST_COUNTER_4 = "Test4";

	@Order(1)
	@Test
	void createCounter() {
		counterService.createCounter(new CounterVO(TEST_COUNTER_1));
		counterService.createCounter(new CounterVO(TEST_COUNTER_2));
		counterService.createCounter(new CounterVO(TEST_COUNTER_3));
		counterService.createCounter(new CounterVO(TEST_COUNTER_4));

		assertEquals(counterService.getCounter(TEST_COUNTER_1).getName(), TEST_COUNTER_1);
		assertEquals(counterService.getCounter(TEST_COUNTER_2).getName(), TEST_COUNTER_2);
		assertEquals(counterService.getCounter(TEST_COUNTER_3).getIncrementOrder(), 1);
		assertEquals(counterService.getCounter(TEST_COUNTER_4).getValue(), 1);
	}

	@Order(2)
	@Test
	void incrementCounter() {
		counterService.incrementCounter(TEST_COUNTER_1);
		counterService.incrementCounter(TEST_COUNTER_1);
		counterService.incrementCounter(TEST_COUNTER_1);

		counterService.incrementCounter(TEST_COUNTER_2);

		counterService.incrementCounter(TEST_COUNTER_3);

		counterService.incrementCounter(TEST_COUNTER_4);
		counterService.incrementCounter(TEST_COUNTER_4);

		assertEquals(counterService.getCounter(TEST_COUNTER_1).getValue(), 4);
		assertEquals(counterService.getCounter(TEST_COUNTER_2).getValue(), 2);
		assertEquals(counterService.getCounter(TEST_COUNTER_3).getValue(), 2);
		assertEquals(counterService.getCounter(TEST_COUNTER_4).getValue(), 3);

	}

	@Order(3)
	@Test
	void getCounter() {
		assertEquals(counterService.getCounter(TEST_COUNTER_1).getName(), TEST_COUNTER_1);
		assertEquals(counterService.getCounter(TEST_COUNTER_2).getName(), TEST_COUNTER_2);
		assertEquals(counterService.getCounter(TEST_COUNTER_3).getValue(), 2);
		assertEquals(counterService.getCounter(TEST_COUNTER_4).getIncrementOrder(), 1);
	}

	@Order(4)
	@Test
	void getAllcounters() {

		List<CounterVO> counters = counterService.getAllcounters();
		counters.forEach(counter -> {
			assertTrue(!counter.getName().isEmpty());
			assertFalse(counter.getIncrementOrder() != 1);
		});
	}

}