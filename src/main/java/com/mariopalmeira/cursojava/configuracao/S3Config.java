package com.mariopalmeira.cursojava.configuracao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3Config {

	@Value("${aws.access_key_id}")
	private String access_key_id;
	
	@Value("${aws.secret_access_key}")
	private String secret_access_key;
	
	@Value("${s3.region}")
	private String region;
	
	@Bean
	public AmazonS3 s3Client() {
		//Preparação das credenciais da amazon
		BasicAWSCredentials amzCredenciais = new BasicAWSCredentials(access_key_id, secret_access_key);
		//Configurando o acesso
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(amzCredenciais)).withRegion(region).build();
		return s3Client;
	}
}
