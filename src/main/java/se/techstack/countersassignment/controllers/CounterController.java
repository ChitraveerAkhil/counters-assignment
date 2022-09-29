package se.techstack.countersassignment.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.techstack.countersassignment.service.CounterService;
import se.techstack.countersassignment.vo.CounterVO;
import se.techstack.countersassignment.vo.ResponseVO;

@RestController
@RequestMapping("counters/")
public class CounterController {

	@Autowired
	CounterService counterService;

	@PostMapping
	public List<ResponseVO<CounterVO>> add(@RequestBody List<CounterVO> counterList) {
		List<ResponseVO<CounterVO>> response = new ArrayList<>();
		for (CounterVO counter : counterList) {
			ResponseVO<CounterVO> responseVO = counterService.add(counter);
			response.add(responseVO);
		}
		return response;
	}

	@GetMapping("{name}")
	public ResponseVO<CounterVO> getByName(@PathVariable String name) {
		ResponseVO<CounterVO> response = new ResponseVO<>();
		CounterVO counter = counterService.getCounterByName(name);
		response.setData(counter);
		return response;
	}

	@GetMapping()
	public ResponseVO<List<CounterVO>> getCounters() {
		ResponseVO<List<CounterVO>> response = new ResponseVO<>();
		List<CounterVO> counters = counterService.getCounterList();
		response.setData(counters);
		return response;
	}

	@RequestMapping(value = "incrementCounterValue/{name}", method = RequestMethod.PATCH)
	public ResponseVO<CounterVO> incrementCounterValue(@PathVariable String name) {
		ResponseVO<CounterVO> response = new ResponseVO<>();
		CounterVO counter = counterService.incrementCounter(name);
		response.setData(counter);
		response.setMessage(
				String.format("The value of the counter %s is incremented to %d.", name, counter.getValue()));
		return response;
	}

	@DeleteMapping("{name}")
	public ResponseVO<CounterVO> delete(@PathVariable String name) {
		counterService.delete(name);
		ResponseVO<CounterVO> response = new ResponseVO<>(String.format("Counter named '%s' deleted!", name),
				HttpStatus.NO_CONTENT);
		return response;
	}
}
