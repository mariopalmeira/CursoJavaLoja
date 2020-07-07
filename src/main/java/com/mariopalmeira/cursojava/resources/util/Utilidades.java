package com.mariopalmeira.cursojava.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Utilidades {
	
	public static String semEspaco(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Integer> stringEmList(String s){
		//separa em pequenas strings e salva em um vetor
		String[] numeros = s.split(",");
		List<Integer> ids = new ArrayList<>();
		for(int i=0; i<numeros.length; i++) {
			//convertendo pra int, salva cada indice do vetor em uma lista de integer
			ids.add(Integer.parseInt(numeros[i]));
		}
		return ids;
	}

}
