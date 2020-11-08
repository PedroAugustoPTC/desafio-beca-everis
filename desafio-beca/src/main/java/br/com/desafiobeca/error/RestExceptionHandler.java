package br.com.desafiobeca.error;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.desafiobeca.exceptions.AtualizarVagaException;
import br.com.desafiobeca.exceptions.TicketInvalidoException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ClasseError>> getErrorResponse(MethodArgumentNotValidException e) {

		List<ClasseError> errorList = new ArrayList<>();

		e.getBindingResult().getFieldErrors().forEach(er -> {
			ClasseError error = new ClasseError();

			error.setCampo(er.getField());
			error.setMessage(er.getDefaultMessage());

			errorList.add(error);
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ClasseError> getErrorResponse(NoSuchElementException e) {
		ClasseError error = new ClasseError();

		error.setCampo("ID");
		error.setMessage("O elemento não foi encontrado!!");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<String> getErrorResponse(EntityExistsException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> getErrorResponse(ConstraintViolationException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, verifique os dados corretamente.");
	}

	@ExceptionHandler(RollbackException.class)
	public ResponseEntity<String> getErrorResponse(RollbackException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, verifique os dados corretamente.");
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> getErrorResponse(NullPointerException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> getErrorResponse(IllegalArgumentException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Argumento inválido");
	}

	@ExceptionHandler(AtualizarVagaException.class)
	public ResponseEntity<String> getErrorResponse(AtualizarVagaException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> getErrorResponse(HttpMessageNotReadableException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum parâmetro enviado");
	}

	@ExceptionHandler(TicketInvalidoException.class)
	public ResponseEntity<String> getErrorResponse(TicketInvalidoException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
