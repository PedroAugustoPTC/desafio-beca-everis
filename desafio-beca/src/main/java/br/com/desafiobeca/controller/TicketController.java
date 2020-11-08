package br.com.desafiobeca.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiobeca.model.Ticket;
import br.com.desafiobeca.model.dto.TicketDtoSalvar;
import br.com.desafiobeca.service.impl.TicketServiceImpl;

@RestController
@RequestMapping("ticket")
public class TicketController {

	@Autowired
	TicketServiceImpl ticketService;

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public ResponseEntity<Ticket> salvar(@RequestBody @Valid TicketDtoSalvar ticketDtoSalvar) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ticketService.salvar(ticketDtoSalvar.getPlaca(), ticketDtoSalvar.getNumeroVaga()));
	}

	@RequestMapping(value = "fecharTicket", method = RequestMethod.PUT)
	public ResponseEntity<String> fecharTicket(@RequestBody Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.fecharTicket(id));
	}

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public ResponseEntity<List<Ticket>> listarTodosTickets() {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.listarTodosTickets());
	}

	@RequestMapping(value = "listar/id={id}", method = RequestMethod.GET)
	public ResponseEntity<Ticket> listarTicketPorId(@RequestBody @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.listarPorId(id).get());
	}

	@RequestMapping(value = "listar/placa={placa}", method = RequestMethod.GET)
	public ResponseEntity<List<Ticket>> listarTicketPorPlaca(@RequestBody @PathVariable String placa) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.listarPorPlaca(placa));
	}

	@RequestMapping(value = "listar/vaga={vaga}", method = RequestMethod.GET)
	public ResponseEntity<List<Ticket>> listarTicketPorVaga(@RequestBody @PathVariable Integer vaga) {
		return ResponseEntity.status(HttpStatus.OK).body(ticketService.listarPorVaga(vaga));
	}
}
