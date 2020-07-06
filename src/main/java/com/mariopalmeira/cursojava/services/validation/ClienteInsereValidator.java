package com.mariopalmeira.cursojava.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.mariopalmeira.cursojava.dao.ClienteDAO;
import com.mariopalmeira.cursojava.domain.Cliente;
import com.mariopalmeira.cursojava.domain.enums.TipoCliente;
import com.mariopalmeira.cursojava.dto.ClienteInsereDTO;
import com.mariopalmeira.cursojava.resources.exception.FieldError;

public class ClienteInsereValidator implements ConstraintValidator<ClienteInsereValida, ClienteInsereDTO> {
	
	@Autowired
	private ClienteDAO clienteDao;
	
	@Override
	public void initialize(ClienteInsereValida ann) {
	}

	@Override
	public boolean isValid(ClienteInsereDTO clienteInsereDTO, ConstraintValidatorContext context) {
		List<FieldError> list = new ArrayList<>();

		if(clienteInsereDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getId()) && !DocumentosValidos.isValidCPF(clienteInsereDTO.getCpfOuCnpj())) {
			list.add(new FieldError("cpfOuCnpj", "CPF inválido."));
		}
		
		if(clienteInsereDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getId()) && !DocumentosValidos.isValidCNPJ(clienteInsereDTO.getCpfOuCnpj())) {
			list.add(new FieldError("cpfOuCnpj", "CNPJ inválido."));
		}
		
		Cliente cliente = clienteDao.findByEmail(clienteInsereDTO.getEmail());
		if(cliente != null) {
			list.add(new FieldError("email", "Email já cadastrado."));
		}
		
		for (FieldError e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessagem()).addPropertyNode(e.getNomeCampo())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}