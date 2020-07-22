package com.mariopalmeira.cursojava.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;
	
	@Value("${s3.bucket}")
	private String bucket;
	
	private Logger Log = LoggerFactory.getLogger(S3Service.class);
	
	//Modo de fazer pegando o arquivo direto da máquina
	public void enviarArquivo(String caminhoArquivoLocal) {
		try {
			File arquivo = new File(caminhoArquivoLocal);
			//No PutObjectRequest são 3 parâmetros: o nome do bucket(salvo no properties), um nome para o arquivo ser salvo na amazon e o arquivo
			s3Client.putObject(new PutObjectRequest(bucket, "nometeste", arquivo));
		}catch(AmazonServiceException e) {
			Log.info(e.getErrorCode() + " - " + e.getErrorMessage());
		}catch(AmazonClientException ew) {
			Log.info(ew.getMessage() + " - " + ew.getCause());
		}
	}
}
