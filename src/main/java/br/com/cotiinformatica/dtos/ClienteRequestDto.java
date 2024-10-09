package br.com.cotiinformatica.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteRequestDto {
	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "Email é obrigatório")
	private String email;
	
	@NotBlank(message = "Telefone é obrigatório")
	private String telefone;
	
	@NotBlank(message = "Data e hora são obrigatórios")
	private LocalDateTime dataHoraCadastro;
	
}
