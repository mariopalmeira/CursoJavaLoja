package com.mariopalmeira.cursojava.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.mariopalmeira.cursojava.domain.enums.TipoCliente;
import com.mariopalmeira.cursojava.dto.ClienteInsereDTO;
import com.mariopalmeira.cursojava.resources.exception.FieldError;

public class ClienteValidator implements ConstraintValidator<ClienteValidation, ClienteInsereDTO> {
	@Override
	public void initialize(ClienteValidation ann) {
	}

	@Override
	public boolean isValid(ClienteInsereDTO clienteInsereDTO, ConstraintValidatorContext context) {
		List<FieldError> list = new ArrayList<>();

		if(clienteInsereDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getId()) && !DocumentosValidos.isValidCPF(clienteInsereDTO.getCpfOuCnpj())) {
			list.add(new FieldError("cpfOuCnpj", "CPF inválido"));
		}
		
		if(clienteInsereDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getId()) && !DocumentosValidos.isValidCNPJ(clienteInsereDTO.getCpfOuCnpj())) {
			list.add(new FieldError("cpfOuCnpj", "CNPJ inválido"));
		}
		
		for (FieldError e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessagem()).addPropertyNode(e.getNomeCampo())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}