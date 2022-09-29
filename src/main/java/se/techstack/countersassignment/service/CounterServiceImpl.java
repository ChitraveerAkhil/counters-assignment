package se.techstack.countersassignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.techstack.countersassignment.components.CounterServiceComponent;
import se.techstack.countersassignment.vo.CounterVO;
import se.techstack.countersassignment.vo.ResponseVO;

@Service
public class CounterServiceImpl implements CounterService {

	@Autowired
	private CounterServiceComponent counterServiceComponent;

	@Override
	public ResponseVO<CounterVO> add(CounterVO counter) {
		return counterServiceComponent.createCounter(counter);
	}

	@Override
	public CounterVO getCounterByName(String name) {
		return counterServiceComponent.getCounter(name);
	}

	@Override
	public CounterVO incrementCounter(String name) {
		return counterServiceComponent.incrementCounter(name);
	}

	@Override
	public List<CounterVO> getCounterList() {
		return counterServiceComponent.getAllcounters();
	}

	@Override
	public void delete(String name) {
		counterServiceComponent.deleteCounter(name);
	}

}
