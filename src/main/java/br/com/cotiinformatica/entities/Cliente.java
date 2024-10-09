package br.com.cotiinformatica.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor




public class Cliente {
	
	private UUID id;
	private String nome;
	private String email;
	private String telefone;
	private LocalDateTime dataHoraCadastro;

}
