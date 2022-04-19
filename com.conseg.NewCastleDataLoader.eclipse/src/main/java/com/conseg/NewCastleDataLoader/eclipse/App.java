package com.conseg.NewCastleDataLoader.eclipse;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class App {

	public static void main(String[] args) {
		Workbook wb = lerExcel();
		String log = "";
		
		for(int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sh = wb.getSheetAt(i);
			String sala = numeroSala(sh.getSheetName());
			
			if(sala == null)continue;
			
			log += sala + "\n";
			System.out.println(sala);
			
			//Itera em cada linha
			for(Iterator<Row> itr = sh.rowIterator(); itr.hasNext();){
				Row row = itr.next();
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = null;
				String urlString = null;
				try {
					log += "\t" + celulaComDataParaString(row.getCell(0)) + " " + celulaComHoraParaString(row.getCell(1)) + ' ' + celulaComHoraParaStringMaisUm(row.getCell(1)) + " " + celularComPessoasParaInt(row.getCell(3)) + "\n";
					System.out.println("\t" + celulaComDataParaString(row.getCell(0)) + " " + celulaComHoraParaString(row.getCell(1)) + ' ' + celulaComHoraParaStringMaisUm(row.getCell(1)) + " " + celularComPessoasParaInt(row.getCell(3)));	
					urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2/sensors/timeseries/room-" + sala + "-zone-2"
							+ "/co2/raw/historic?startTime=" + celulaComDataParaString(row.getCell(0)) + "T" + celulaComHoraParaString(row.getCell(1)) + "Z&endTime=" + celulaComDataParaString(row.getCell(0)) + "T" + celulaComHoraParaStringMaisUm(row.getCell(1));
					jsonNode = objectMapper.readTree(new URL(urlString));
					log += "\t" + celulaComDataParaString(row.getCell(0)) + " " + celulaComHoraParaString(row.getCell(1)) + ' ' + celulaComHoraParaStringMaisUm(row.getCell(1)) + " " + celularComPessoasParaInt(row.getCell(3)) + " " + averageSensor(jsonNode) + "\n";
					System.out.println("\t" + celulaComDataParaString(row.getCell(0)) + " " + celulaComHoraParaString(row.getCell(1)) + ' ' + celulaComHoraParaStringMaisUm(row.getCell(1)) + " " + celularComPessoasParaInt(row.getCell(3)) + " " + averageSensor(jsonNode));	
				} catch (Exception e) {
					log += "\tERRO: " + urlString + "\n";
					System.out.println("\tERRO: " + urlString);
				}
								
			}
			/*Melhor usar o Jackson JsonNode
			 * ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> map = null;
			tWry {
				map = objectMapper.readValue(new URL("https://api.usb.urbanobservatory.ac.uk/api/v2.0a/sensors/entity?meta:roomNumber=1.042&zone=1/co2/raw/historic?startTime=2019-11-28T00:00:00Z&endTime=2019-11-28T23:59:59"), new TypeReference<Map<String,Object>>(){});
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(map.get("items").toString());*/
			

			
			
			 
		}
		try {
			FileWriter myWriter = new FileWriter("resumo.txt");
			myWriter.write(log);
			myWriter.close();
			
		}
		catch (Exception e) {
			System.out.println("Erro salvando");
		}
		
		System.out.println("fim");
	}
	
	public static double averageSensor(JsonNode nodos) {
		double resposta = 0.0;
		int quantidade = 0;
		if(nodos.size() == 0)return resposta;
		
		nodos = nodos.get("historic");
		
		for(JsonNode n : nodos) {
			Iterator<JsonNode> it = n.elements();
			while(it.hasNext()) {
				JsonNode node = it.next();
				quantidade++;
				
				resposta = resposta + node.get("value").asDouble();
				//resposta = resposta + Double.parseDouble(n.get("timeseries").findValue("value").toString());
			}				
			
		}
		return resposta/quantidade;
	}
	
	
	/**public static List<JsonNode> procuraSensoresCO2(JsonNode node, List<JsonNode> res){
		if(node.has("historic")) {
			//System.out.println("metric");
			res.add(node);
		}
		
		Iterator<JsonNode> it = node.iterator();
		while(it.hasNext()){
			procuraSensoresCO2(it.next(), res);
		}
		
		return res;
	}**/
	public static String celulaComHoraParaStringMaisUm(Cell c) {
		String retorno = null;
		
		if(c.getCellType() == CellType.STRING) {
			retorno = c.getStringCellValue();
		} else {
			Date itemDate = c.getDateCellValue();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(itemDate);
			calendar.add(Calendar.MINUTE, 59);
			calendar.add(Calendar.SECOND, 59);
			retorno = new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
		}
		
		return retorno;
	}	
	
	public static String celulaComHoraParaString(Cell c) {
		String retorno = null;
		
		if(c.getCellType() == CellType.STRING) {
			retorno = c.getStringCellValue();
		} else {
			Date itemDate = c.getDateCellValue();
			retorno = new SimpleDateFormat("HH:mm:ss").format(itemDate);
		}
		
		return retorno;
	}
	public static String celularComPessoasParaInt(Cell c) {
		String retorno = null;
		
		if(c.getCellType() == CellType.STRING) 
			retorno = c.getStringCellValue();
		else {
			int temp = (int) c.getNumericCellValue();
			retorno = Integer.toString(temp);
		}
			


		return retorno;
		
	}
	
	
	public static String celulaComDataParaString(Cell c) {
		String retorno = null;
		
		if(c.getCellType() == CellType.STRING) {
			DateFormat dfOriginal = new SimpleDateFormat("dd.MM.yyyy");
			Date dateOriginal = null;
			String temp = "";
			try {
				temp = c.getStringCellValue();
				dateOriginal = dfOriginal.parse(temp);
			} catch (Exception e) {
				//e.printStackTrace();
				return temp;
			}
			
			DateFormat dfNovo = new SimpleDateFormat("yyyy-MM-dd");
			retorno = dfNovo.format(dateOriginal);
		}
		if(c.getCellType() == CellType.NUMERIC) {
			Date itemDate = DateUtil.getJavaDate(c.getNumericCellValue());
			retorno = new SimpleDateFormat("yyyy-MM-dd").format(itemDate);
		}
		
		return retorno;
	}
	
	
	
	public static XSSFWorkbook lerExcel() {
		String path = "data/Historical attendance by room-anon.xlsx";
		File file  = new File(path);
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fs = new FileInputStream(file);
			workbook = new XSSFWorkbook(fs);
		}catch(Exception e) {
			System.out.println(e);
		}
		return workbook;
	}
	
	public static String numeroSala(String s){
		String retorno = null;
		if(s.contains("USB "))retorno = s.substring(4);

		return retorno;
		
	}
}
