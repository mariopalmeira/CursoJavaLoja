package com.mariopalmeira.cursojava.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mariopalmeira.cursojava.services.exception.FileException;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucket;

//	private Logger Log = LoggerFactory.getLogger(S3Service.class);

//	//Modo de fazer pegando o arquivo direto da máquina
//	public void enviarArquivo(String caminhoArquivoLocal) {
//		try {
//			File arquivo = new File(caminhoArquivoLocal);
//			//No PutObjectRequest são 3 parâmetros: o nome do bucket(salvo no properties), um nome para o arquivo ser salvo na amazon e o arquivo
//			s3Client.putObject(new PutObjectRequest(bucket, "nometeste", arquivo));
//		}catch(AmazonServiceException e) {
//			Log.info(e.getErrorCode() + " - " + e.getErrorMessage());
//		}catch(AmazonClientException ew) {
//			Log.info(ew.getMessage() + " - " + ew.getCause());
//		}
//	}

//	public URI enviarArquivo(MultipartFile arquivo) throws IOException{
//		try {
//			//Nome do arquivo
//			String nome = arquivo.getOriginalFilename();
//			//Tipo de arquivo 
//			String tipo = arquivo.getContentType();
//			//Os dados
//			InputStream inputStream = arquivo.getInputStream();
//			
//			ObjectMetadata metaData = new ObjectMetadata();
//			metaData.setContentType(tipo);
//			//s3Client.putObject(bucketName, key, input, metadata)
//			s3Client.putObject(bucket, nome, inputStream, metaData);
//			return s3Client.getUrl(bucket, nome).toURI();
//		} catch (URISyntaxException e) {
//			throw new FileException("Amazon Falhou!"); 
//		}
//	}
	
	
	public URI enviarArquivo(InputStream inputStream, String nomeArquivo, String contentType) throws IOException{
	try {
		ObjectMetadata metaData = new ObjectMetadata();
		metaData.setContentType(contentType);
		//s3Client.putObject(bucketName, key, input, metadata)
		s3Client.putObject(bucket, nomeArquivo, inputStream, metaData);
		return s3Client.getUrl(bucket, nomeArquivo).toURI();
	} catch (URISyntaxException e) {
		throw new FileException("Amazon Falhou!"); 
	}
}	
}
