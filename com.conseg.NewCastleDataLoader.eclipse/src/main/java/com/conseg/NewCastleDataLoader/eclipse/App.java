package com.conseg.NewCastleDataLoader.eclipse;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
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
		String salvar = "";
		
		//Lista de zonas
		Dictionary<String, String[]> zonas = new Hashtable<String, String[]>();
		String[] tmp = {"-zone-1"};
		zonas.put("1.006", tmp); //chute 20x15 = 300 m2 (lembrar que é pé duplo)
		tmp = new String[]{"-zone-1","-zone-2","-zone-3"}; 
		zonas.put("1.042", tmp); //85 m2
		tmp = new String[]{"-zone-1","-zone-2"}; 
		zonas.put("2.022", tmp); //101 m2
		tmp = new String[]{"-zone-1","-zone-2","-zone-3","-zone-4"};
		zonas.put("3.005", tmp); //202 m2
		tmp = new String[]{"-zone-1","-zone-2","-zone-3","-zone-4","-zone-5","-zone-6","-zone-7","-zone-8","-zone-9"};
		zonas.put("3.015", tmp); //355 m2
		tmp = new String[]{"-zone-1","-zone-2","-zone-3","-zone-4"};
		zonas.put("3.018", tmp); //174 m2
		tmp = new String[]{"-zone-1", "-zone-2", "-zone-3", "-zone-4", "-zone-5", "-zone-6"};
		zonas.put("4.005", tmp); //166 m2
		tmp = new String[]{"-zone-1", "-zone-2", "-zone-3", "-zone-4"};
		zonas.put("4.015", tmp); //158 m2
		tmp = new String[]{};
		zonas.put("4.020", tmp); //20 m2
		tmp = new String[]{};
		zonas.put("4.018", tmp); //62 m2
		tmp = new String[]{"-zone-1", "-zone-2", "-zone-3", "-zone-4"};
		zonas.put("4.022", tmp); //247 m2
		tmp = new String[]{"-zone-1", "-zone-2"};
		zonas.put("5.023", tmp); //85 m2
		tmp = new String[]{};
		zonas.put("G.003", tmp); //201 m2
		
		
		
		for(int i = 0; i < wb.getNumberOfSheets(); i++) {
			Sheet sh = wb.getSheetAt(i);
			String sala = numeroSala(sh.getSheetName());
			
			if(sala == null)continue;
			
			//log += sala + "\n";
			System.out.println(sala);
			
			//Itera em cada linha
			for(Iterator<Row> itr = sh.rowIterator(); itr.hasNext();){
				Row row = itr.next();
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = null;
				String urlString = null;
				
				double total = 0.0; 
				boolean erro = false;
				
					//log += "\t" + celulaComDataParaString(row.getCell(0)) + " " + celulaComHoraParaString(row.getCell(1)) + ' ' + celulaComHoraParaStringMaisUm(row.getCell(1)) + " " + celularComPessoasParaInt(row.getCell(3)) + "\n";
					//System.out.println("\t" + celulaComDataParaString(row.getCell(0)) + " " + celulaComHoraParaString(row.getCell(1)) + ' ' + celulaComHoraParaStringMaisUm(row.getCell(1)) + " " + celularComPessoasParaInt(row.getCell(3)));	
					
					if(zonas.get(sala).length > 0) {
						boolean linhaSalva = true;
						for(String s : zonas.get(sala)) {
							try {
								urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2/sensors/timeseries/room-" + sala + s
										+ "/co2/raw/historic?startTime=" + celulaComDataParaString(row.getCell(0)) + "T" + celulaComHoraParaString(row.getCell(1)) + "Z&endTime=" + celulaComDataParaString(row.getCell(0)) + "T" + celulaComHoraParaStringMaisUm(row.getCell(1));
								jsonNode = objectMapper.readTree(new URL(urlString));
								log += sala + "; "  + celulaComDataParaString(row.getCell(0)) + "; " + celulaComHoraParaString(row.getCell(1)) + "; " + celulaComHoraParaStringMaisUm(row.getCell(1)) + "; " + celularComPessoasParaInt(row.getCell(3)) + "; " + averageSensor(jsonNode) + ";\n";
								total += averageSensor(jsonNode);
								if(linhaSalva) {
									linhaSalva = false;
									salvar += sala + "; "  + celulaComDataParaString(row.getCell(0)) + "; " + celulaComHoraParaString(row.getCell(1)) + "; " + celulaComHoraParaStringMaisUm(row.getCell(1)) + "; " + celularComPessoasParaInt(row.getCell(3)) + "; ";
								}
								total += averageSensor(jsonNode);
								System.out.println("\t" + celulaComDataParaString(row.getCell(0)) + " " + celulaComHoraParaString(row.getCell(1)) + ' ' + celulaComHoraParaStringMaisUm(row.getCell(1)) + " " + celularComPessoasParaInt(row.getCell(3)) + " " + s + " " + averageSensor(jsonNode));
							} catch (Exception e) {
								log += "\tERRO: " + urlString + "\n";
								System.out.println("\tERRO: " + urlString);
								erro= true;
							}	
						}
					} else {
						try {
							urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2/sensors/timeseries/room-" + sala
									+ "/co2/raw/historic?startTime=" + celulaComDataParaString(row.getCell(0)) + "T" + celulaComHoraParaString(row.getCell(1)) + "Z&endTime=" + celulaComDataParaString(row.getCell(0)) + "T" + celulaComHoraParaStringMaisUm(row.getCell(1));
							jsonNode = objectMapper.readTree(new URL(urlString));
							log += sala + "; "+ celulaComDataParaString(row.getCell(0)) + "; " + celulaComHoraParaString(row.getCell(1)) + "; " + celulaComHoraParaStringMaisUm(row.getCell(1)) + "; " + celularComPessoasParaInt(row.getCell(3)) + "; " + averageSensor(jsonNode) + ";\n";
							System.out.println("\t" + celulaComDataParaString(row.getCell(0)) + " " + celulaComHoraParaString(row.getCell(1)) + ' ' + celulaComHoraParaStringMaisUm(row.getCell(1)) + " " + celularComPessoasParaInt(row.getCell(3)) + " " + averageSensor(jsonNode));
							salvar += sala + "; "  + celulaComDataParaString(row.getCell(0)) + "; " + celulaComHoraParaString(row.getCell(1)) + "; " + celulaComHoraParaStringMaisUm(row.getCell(1)) + "; " + celularComPessoasParaInt(row.getCell(3)) + "; ";
							total += averageSensor(jsonNode);
						} catch (Exception e) {
							log += "\tERRO: " + urlString + "\n";
							System.out.println("\tERRO: " + urlString);
							erro= true;
						}
					}
					if(!erro) salvar += total + ";\n";
					
					
			}				 
		}
		try {
			FileWriter myWriter = new FileWriter("log.txt");
			myWriter.write(log);
			myWriter.close();
			FileWriter myWriter2 = new FileWriter("consolidado.csv");
			myWriter2.write(salvar);
			myWriter2.close();
			
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
