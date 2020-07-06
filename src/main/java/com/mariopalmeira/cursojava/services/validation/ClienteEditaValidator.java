package com.mariopalmeira.cursojava.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.mariopalmeira.cursojava.dao.ClienteDAO;
import com.mariopalmeira.cursojava.domain.Cliente;
import com.mariopalmeira.cursojava.dto.ClienteDTO;
import com.mariopalmeira.cursojava.dto.ClienteInsereDTO;
import com.mariopalmeira.cursojava.resources.exception.FieldError;

public class ClienteEditaValidator implements ConstraintValidator<ClienteEditaValida, ClienteDTO> {
	
	@Autowired 
	private HttpServletRequest requisicao;
	
	@Autowired
	private ClienteDAO clienteDao;
	
	@Override
	public void initialize(ClienteEditaValida ann) {
	}

	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
		List<FieldError> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) requisicao.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));

		Cliente cliente = clienteDao.findByEmail(clienteDTO.getEmail());
		if(cliente != null && cliente.getId() != uriId) {
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