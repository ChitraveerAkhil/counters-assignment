package se.techstack.countersassignment.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CounterVO {

	private Integer id;
	// initial counter value is 1
	private int value = 1;
	private String name;

	// A variable to increment the counter's value in certain order. For the
	// time being, it's default value is '1' as mentioned in assignment PDF.
	private int incrementOrder = 1;

	public CounterVO(String name) {
		this.name = name;
	}
}
