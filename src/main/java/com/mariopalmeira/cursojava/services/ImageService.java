package com.mariopalmeira.cursojava.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mariopalmeira.cursojava.services.exception.FileException;

@Service
public class ImageService {
	
	public BufferedImage getJpgImageFromFile(MultipartFile arquivo) {
		try {
			//Para pegar a extensão do arquivo, é necessário converter seu nome pra String
			String extensao = FilenameUtils.getExtension(arquivo.getOriginalFilename());
			if(!extensao.equals("png") && !extensao.equals("jpg")) {
				throw new FileException("Apenas arquivos .JPG ou .PNG");
			}		
			
			//Necessário pegar o inputStream do arquivo
			BufferedImage imagem = ImageIO.read(arquivo.getInputStream());
			if(extensao.equals("png")) {
				//Conversão
				imagem = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
				//Remoção do fundo transparente se for png
				imagem.createGraphics().drawImage(imagem, 0, 0, Color.WHITE, null);
			}
			return imagem;
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo");
		}
	}
	
	public InputStream bufferedEmInputStream(BufferedImage imagem, String extensao) {
		try {
			//Instancia-se um objeto ByteArrayOutputStream
			ByteArrayOutputStream baostream = new ByteArrayOutputStream();
			//Escreve essa imagem, com essa extensao e salva nesse objeto
			ImageIO.write(imagem, extensao, baostream);
			//Pegue o objeto com a imagem escrita, converte em array de bytes e salva com InputStream
			InputStream inputStream = new ByteArrayInputStream(baostream.toByteArray());
			return inputStream;
		} catch (IOException e) {
			throw new FileException("Erro ao ler o arquivo");
		}
	}
}
