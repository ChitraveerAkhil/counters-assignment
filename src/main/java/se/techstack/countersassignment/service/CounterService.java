package se.techstack.countersassignment.service;

import java.util.List;

import se.techstack.countersassignment.vo.CounterVO;
import se.techstack.countersassignment.vo.ResponseVO;

public interface CounterService {
	ResponseVO<CounterVO> add(CounterVO counter);

	CounterVO getCounterByName(String name);

	List<CounterVO> getCounterList();

	CounterVO incrementCounter(String name);

	void delete(String name);
}
