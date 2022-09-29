package se.techstack.countersassignment.vo;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T> {

	private String message = "Response returned successfully";
	private HttpStatus status = HttpStatus.OK;
	private int statusCode = HttpStatus.OK.value();
	private T data = null;

	public ResponseVO(T data) {
		this.data = data;
	}

	public ResponseVO(String msg) {
		this.message = msg;
	}

	public ResponseVO(T data, String msg) {
		this.message = msg;
		this.data = data;
	}

	public ResponseVO(String msg, HttpStatus status) {
		this.message = msg;
		this.status = status;
		this.statusCode = status.value();
	}

	public ResponseVO(String msg, HttpStatus status, T data) {
		this.message = msg;
		this.status = status;
		this.statusCode = status.value();
		this.data = data;
	}
}
