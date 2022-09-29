package se.techstack.countersassignment.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import se.techstack.countersassignment.exceptionHandling.CounterNotFoundException;
import se.techstack.countersassignment.vo.CounterVO;
import se.techstack.countersassignment.vo.ResponseVO;

@Component
public class CounterServiceComponent {

	Logger logger = LoggerFactory.getLogger(CounterServiceComponent.class);

	private Map<String, CounterVO> counters = new HashMap<>();
	private int lastStoredId = 0;

	public ResponseVO<CounterVO> createCounter(CounterVO counter) {
		ResponseVO<CounterVO> response = new ResponseVO<>();
		String counterName = counter.getName();
		if (counters.isEmpty() || !counters.containsKey(counterName)) {
			int id = lastStoredId + 1;
			counter.setId(id);
			counters.put(counter.getName(), counter);
			lastStoredId = id;

			logger.info("Counter Created: " + counter.toString());
			response = new ResponseVO<>(String.format("Counter Named '%s' created successfully", counterName),
					HttpStatus.CREATED, counter);

		} else {
			logger.warn("Error while creating counter.");
			response = new ResponseVO<>(String.format(
					"Error! counter Named '%s' already presents. So can't create new one with the same name.",
					counterName), HttpStatus.CONFLICT);
		}

		return response;
	}

	public CounterVO getCounter(String name) {
		CounterVO counter = null;
		if (!counters.isEmpty() && counters.containsKey(name)) {
			counter = counters.get(name);
		} else {
			logger.info("Counter not found");
			throw new CounterNotFoundException(name);
		}
		return counter;
	}

	public CounterVO incrementCounter(String name) {
		CounterVO counter = getCounter(name);
		logger.info("Counter's value before incrementing: " + counter.getValue());
		int increment = counter.getValue() + counter.getIncrementOrder();
		counter.setValue(increment);
		logger.info("Counter's value after incrementing: " + counter.getValue());
		counters.put(name, counter);
		return counter;
	}

	public List<CounterVO> getAllcounters() {
		List<CounterVO> counterList = counters.values().stream().collect(Collectors.toList());
		return counterList;
	}

	public void deleteCounter(String name) {
		if (!counters.isEmpty() && counters.containsKey(name)) {
			counters.remove(name);
		} else {
			logger.info("The counter to delete not found.");
			throw new CounterNotFoundException(name);
		}
	}

}
