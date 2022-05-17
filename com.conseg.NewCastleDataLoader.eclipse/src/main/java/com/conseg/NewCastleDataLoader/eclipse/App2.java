package com.conseg.NewCastleDataLoader.eclipse;

import java.io.*;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App2 {
	public static void main(String[] args) {
		Workbook wb = lerExcel(); //lê o excel da pasta data
		String log = "";
		String salvar = "";
		
		//Lista de zonas de cada sala
		Dictionary<String, String[]> zonas = new Hashtable<String, String[]>();
		String[] tmp = {"&zone=1"};
		zonas.put("1.006", tmp); //chute 20x15 = 300 m2 (lembrar que é pé duplo)
		tmp = new String[]{"&zone=1","&zone=2","&zone=3"}; 
		zonas.put("1.042", tmp); //85 m2
		tmp = new String[]{"&zone=1","&zone=2"}; 
		zonas.put("2.022", tmp); //101 m2
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4"};
		zonas.put("3.005", tmp); //202 m2
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4","&zone=5","&zone=6","&zone=7","&zone=8","&zone=9"};
		zonas.put("3.015", tmp); //355 m2
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4"};
		zonas.put("3.018", tmp); //174 m2
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4","&zone=5","&zone=6"};
		zonas.put("4.005", tmp); //166 m2
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4"};
		zonas.put("4.015", tmp); //158 m2
		tmp = new String[]{};
		zonas.put("4.020", tmp); //20 m2
		tmp = new String[]{};
		zonas.put("4.018", tmp); //62 m2
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4"};
		zonas.put("4.022", tmp); //247 m2
		tmp = new String[]{"&zone=1","&zone=2"};
		zonas.put("5.023", tmp); //85 m2
		tmp = new String[]{};
		zonas.put("G.003", tmp); //201 m2		
		
		
		for(int i = 0; i < wb.getNumberOfSheets(); i++) { //percorre todas as folhas do excel

			Sheet sh = wb.getSheetAt(i);
			String sala = numeroSala(sh.getSheetName());
			
			if(sala == null)continue;
			
			//log += sala + "\n";
			System.out.println(sala+"\n");
			
			
			ObjectMapper objectMapper = new ObjectMapper(); //funções para converter java em json e vice versa
			JsonNode jsonNode = null;
			String urlString = null;
				
			//dados sobre CO2
			boolean erro = false;
				
			if(zonas.get(sala).length > 0) {
				for(int j = 0; j < zonas.get(sala).length; j++) {
					String s = zonas.get(sala)[j];
					double co2;
					double occupancy;
					double humidity;
					double temperature;
					double brightness;
					erro = false;
					
					//co2
					try {
						urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2.0a/sensors/entity?meta:roomNumber=" + sala + s + "&metric=co2";
						jsonNode = objectMapper.readTree(new URL(urlString));
						co2 = Double.parseDouble(jsonNode.get("items").get(j).findValue("feed").findValue("timeseries").findValue("latest").findValue("value").toString());
											
					} catch (Exception e) {
						co2 = 0;
					}	
					
					//occupancy
					try {
						urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2.0a/sensors/entity?meta:roomNumber=" + sala + s + "&metric=occupancy+sensor";
						jsonNode = objectMapper.readTree(new URL(urlString));
						occupancy = Double.parseDouble(jsonNode.get("items").get(j).findValue("feed").findValue("timeseries").findValue("latest").findValue("value").toString());
											
					} catch (Exception e) {
						occupancy = 0;
					}	
					
					//humidity
					try {
						urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2.0a/sensors/entity?meta:roomNumber=" + sala + s + "&metric=relative+humidity";
						jsonNode = objectMapper.readTree(new URL(urlString));
						humidity = Double.parseDouble(jsonNode.get("items").get(j).findValue("feed").findValue("timeseries").findValue("latest").findValue("value").toString());
											
					} catch (Exception e) {
						humidity = 0;
					}	
					
					//temperature
					try {
						urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2.0a/sensors/entity?meta:roomNumber=" + sala + s + "&metric=room+temperature";
						jsonNode = objectMapper.readTree(new URL(urlString));
						temperature = Double.parseDouble(jsonNode.get("items").get(j).findValue("feed").findValue("timeseries").findValue("latest").findValue("value").toString());
											
					} catch (Exception e) {
						temperature = 0;
					}	
					
					//brightness
					try {
						urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2.0a/sensors/entity?meta:roomNumber=" + sala + s + "&metric=room+brightness";
						jsonNode = objectMapper.readTree(new URL(urlString));
						brightness = Double.parseDouble(jsonNode.get("items").get(j).findValue("feed").findValue("timeseries").findValue("latest").findValue("value").toString());
										
					} catch (Exception e) {
						brightness = 0;
					}	
					
					
					if((co2 == 0) && (occupancy == 0) && (humidity == 0) && (temperature == 0) && (brightness == 0)){
						erro = true;
					}

					if(!erro) {
						salvar += sala + "; "  + co2 + "; " + occupancy + "; " + humidity + "; " + temperature + "; " + brightness + "; " + "\n";
					}

				}
			} else {
				double co2;
				double occupancy;
				double humidity;
				double temperature;
				double brightness;
				erro = false;
				
				//co2
				try {
					urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2.0a/sensors/entity?meta:roomNumber=" + sala + "&metric=co2";
					jsonNode = objectMapper.readTree(new URL(urlString));
					co2 = Double.parseDouble(jsonNode.get("items").findValue("feed").findValue("timeseries").findValue("latest").findValue("value").toString());

				} catch (Exception e) {
					co2 = 0;
				}
				
				//occupancy
				try {
					urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2.0a/sensors/entity?meta:roomNumber=" + sala + "&metric=occupancy+sensor";
					jsonNode = objectMapper.readTree(new URL(urlString));
					occupancy = Double.parseDouble(jsonNode.get("items").findValue("feed").findValue("timeseries").findValue("latest").findValue("value").toString());

				} catch (Exception e) {
					occupancy = 0;
				}
				
				//humidity
				try {
					urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2.0a/sensors/entity?meta:roomNumber=" + sala + "&metric=relative+humidity";
					jsonNode = objectMapper.readTree(new URL(urlString));
					humidity = Double.parseDouble(jsonNode.get("items").findValue("feed").findValue("timeseries").findValue("latest").findValue("value").toString());

				} catch (Exception e) {
					humidity = 0;
				}
				
				//temperature
				try {
					urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2.0a/sensors/entity?meta:roomNumber=" + sala + "&metric=room+temperature";
					jsonNode = objectMapper.readTree(new URL(urlString));
					temperature = Double.parseDouble(jsonNode.get("items").findValue("feed").findValue("timeseries").findValue("latest").findValue("value").toString());

				} catch (Exception e) {
					temperature = 0;
				}
				
				//brightness
				try {
					urlString = "https://api.usb.urbanobservatory.ac.uk/api/v2.0a/sensors/entity?meta:roomNumber=" + sala + "&metric=room+brightness";
					jsonNode = objectMapper.readTree(new URL(urlString));
					brightness = Double.parseDouble(jsonNode.get("items").findValue("feed").findValue("timeseries").findValue("latest").findValue("value").toString());

				} catch (Exception e) {
					brightness = 0;
				}
				
				
				if((co2 == 0) && (occupancy == 0) && (humidity == 0) && (temperature == 0) && (brightness == 0)){
					erro = true;
				}
				
				if(!erro) {
					salvar += sala + "; "  + co2 + "; " + occupancy + "; " + humidity + "; " + temperature + "; " + brightness + "; " + "\n";
				}

			}
				
					
		}				 
		
		try {
			FileWriter myWriter = new FileWriter("log2.txt");
			myWriter.write(log);
			myWriter.close();
			FileWriter myWriter2 = new FileWriter("consolidado2.csv");
			myWriter2.write(salvar);
			myWriter2.close();
			
		} catch (Exception e) {
			System.out.println("Erro salvando");
		}
		
		System.out.println("fim");
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
