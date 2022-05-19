package com.conseg.NewCastleDataLoader.eclipse;

import java.io.*;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App2 {
	public static void main(String[] args) {
		String log = "";
		String salvar = "";
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());

        System.out.println(timeStamp);
		
		
		//Lista de zonas de cada sala
		/*Dictionary<String, String[]> zonas = new Hashtable<String, String[]>();
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
		zonas.put("G.003", tmp); //201 m2		*/
		
		//Lista de salas
		ArrayList<String> salas = new ArrayList<String>();
		
		Dictionary<String, String[]> zonas = new Hashtable<String, String[]>();
		//Nível G
		String[] tmp = {};
		zonas.put("G.003", tmp); //201 m2
		salas.add("G.003");
		tmp = new String[]{};
		zonas.put("G.006", tmp);
		salas.add("G.006");
		tmp = new String[]{};
		zonas.put("G.007", tmp);
		salas.add("G.007");
		tmp = new String[]{"&zone=1","&zone=2"};
		zonas.put("G.009", tmp);
		salas.add("G.009");
		tmp = new String[]{};
		zonas.put("G.010", tmp);
		salas.add("G.010");
		tmp = new String[]{};
		zonas.put("G.011", tmp);
		salas.add("G.011");
		tmp = new String[]{};
		zonas.put("G.012", tmp);
		salas.add("G.012");
		tmp = new String[]{};
		zonas.put("G.018", tmp);
		salas.add("G.018");
		tmp = new String[]{};
		zonas.put("G.019", tmp);
		salas.add("G.019");
		tmp = new String[]{};
		zonas.put("G.020", tmp);
		salas.add("G.020");
		tmp = new String[]{};
		zonas.put("G.021", tmp);
		salas.add("G.021");
		tmp = new String[]{};
		zonas.put("G.022", tmp);
		salas.add("G.022");
		tmp = new String[]{};
		zonas.put("G.025", tmp);
		salas.add("G.025");
		tmp = new String[]{};
		zonas.put("G.026", tmp);
		salas.add("G.026");
		tmp = new String[]{};
		zonas.put("G.038", tmp);
		salas.add("G.038");
		tmp = new String[]{};
		zonas.put("G.039", tmp);
		salas.add("G.039");
		tmp = new String[]{};
		zonas.put("G.040", tmp);
		salas.add("G.040");
		tmp = new String[]{};
		zonas.put("G.041", tmp);
		salas.add("G.041");
		tmp = new String[]{};
		zonas.put("G.042", tmp);
		salas.add("G.042");
		tmp = new String[]{};
		zonas.put("G.044", tmp);
		salas.add("G.044");
		tmp = new String[]{};
		zonas.put("G.045", tmp);
		salas.add("G.045");
		tmp = new String[]{};
		zonas.put("G.054", tmp);
		salas.add("G.054");
		tmp = new String[]{};
		zonas.put("G.055", tmp);
		salas.add("G.055");
		tmp = new String[]{};
		zonas.put("G.059", tmp);
		salas.add("G.059");
		tmp = new String[]{};
		zonas.put("G.062", tmp);
		salas.add("G.062");
		tmp = new String[]{};
		zonas.put("G.063", tmp);
		salas.add("G.063");
		tmp = new String[]{};
		zonas.put("G.069", tmp);
		salas.add("G.069");
		tmp = new String[]{"&zone=1", "&zone=2", "&zone=3", "&zone=4", "&zone=5"};
		zonas.put("G.071", tmp);
		salas.add("G.071");
		tmp = new String[]{};
		zonas.put("G.081", tmp);
		salas.add("G.081");
		
		//Nível 1
		tmp = new String[]{};
		zonas.put("1.003", tmp);
		salas.add("1.003");
		tmp = new String[]{};
		zonas.put("1.005", tmp);
		salas.add("1.005");
		tmp = new String[]{"&zone=1"};
		zonas.put("1.006", tmp); //chute 20x15 = 300 m2 (lembrar que é pé duplo)
		salas.add("1.006");
		tmp = new String[]{"&zone=1", "&zone=2"};
		zonas.put("1.009", tmp);
		salas.add("1.009");
		tmp = new String[]{};
		zonas.put("1.010", tmp);
		salas.add("1.010");
		tmp = new String[]{};
		zonas.put("1.012", tmp);
		salas.add("1.012");
		tmp = new String[]{};
		zonas.put("1.013", tmp);
		salas.add("1.013");
		tmp = new String[]{};
		zonas.put("1.014", tmp);
		salas.add("1.014");
		tmp = new String[]{};
		zonas.put("1.017", tmp);
		salas.add("1.017");
		tmp = new String[]{};
		zonas.put("1.018", tmp);
		salas.add("1.018");
		tmp = new String[]{};
		zonas.put("1.019", tmp);
		salas.add("1.019");
		tmp = new String[]{};
		zonas.put("1.020", tmp);
		salas.add("1.020");
		tmp = new String[]{"&zone=1", "&zone=2", "&zone=3", "&zone=4", "&zone=5", "&zone=6", "&zone=7", "&zone=8", "&zone=9", "&zone=10", "&zone=11", "&zone=12", "&zone=13", "&zone=14", "&zone=15", "&zone=16", "&zone=17"};
		zonas.put("1.024", tmp);
		salas.add("1.024");
		tmp = new String[]{};
		zonas.put("1.025", tmp);
		salas.add("1.025");
		tmp = new String[]{};
		zonas.put("1.025A", tmp);
		salas.add("1.025A");
		tmp = new String[]{};
		zonas.put("1.026", tmp);
		salas.add("1.026");
		tmp = new String[]{};
		zonas.put("1.025B", tmp);
		salas.add("1.025B");
		tmp = new String[]{};
		zonas.put("1.031", tmp);
		salas.add("1.031");
		tmp = new String[]{};
		zonas.put("1.032", tmp);
		salas.add("1.032");
		tmp = new String[]{};
		zonas.put("1.038", tmp);
		salas.add("1.038");
		tmp = new String[]{};
		zonas.put("1.040", tmp);
		salas.add("1.040");
		tmp = new String[]{"&zone=1","&zone=2","&zone=3"}; 
		zonas.put("1.042", tmp); //85 m2
		salas.add("1.042");
		tmp = new String[]{};
		zonas.put("1.043", tmp);
		salas.add("1.043");
		
		//Nível 2
		tmp = new String[]{};
		zonas.put("2.003", tmp);
		salas.add("2.003");
		tmp = new String[]{};
		zonas.put("2.005", tmp);
		salas.add("2.005");
		tmp = new String[]{};
		zonas.put("2.007", tmp);
		salas.add("2.007");
		tmp = new String[]{};
		zonas.put("2.008", tmp);
		salas.add("2.008");
		tmp = new String[]{};
		zonas.put("2.010", tmp);
		salas.add("2.010");
		tmp = new String[]{};
		zonas.put("2.014", tmp);
		salas.add("2.014");
		tmp = new String[]{};
		zonas.put("2.015", tmp);
		salas.add("2.015");
		tmp = new String[]{};
		zonas.put("2.017", tmp);
		salas.add("2.017");
		tmp = new String[]{};
		zonas.put("2.018", tmp);
		salas.add("2.018");
		tmp = new String[]{"&zone=1", "&zone=2", "&zone=3"};
		zonas.put("2.019", tmp);
		salas.add("2.019");
		tmp = new String[]{};
		zonas.put("2.020", tmp);
		salas.add("2.020");
		tmp = new String[]{};
		zonas.put("2.021", tmp);
		salas.add("2.021");
		tmp = new String[]{"&zone=1","&zone=2"}; 
		zonas.put("2.022", tmp); //101 m2
		salas.add("2.022");
		tmp = new String[]{};
		zonas.put("2.023", tmp);
		salas.add("2.023");
		tmp = new String[]{};
		zonas.put("2.026", tmp);
		salas.add("2.026");
		tmp = new String[]{};
		zonas.put("2.027", tmp);
		salas.add("2.027");
		tmp = new String[]{};
		zonas.put("2.035", tmp);
		salas.add("2.035");
		tmp = new String[]{"&zone=1", "&zone=2"};
		zonas.put("2.037", tmp);
		salas.add("2.037");
		tmp = new String[]{"&zone=1", "&zone=2"};
		zonas.put("2.038", tmp);
		salas.add("2.038");
		tmp = new String[]{};
		zonas.put("2.039", tmp);
		salas.add("2.039");
		tmp = new String[]{};
		zonas.put("2.040", tmp);
		salas.add("2.040");
		tmp = new String[]{};
		zonas.put("2.041", tmp);
		salas.add("2.041");
		tmp = new String[]{};
		zonas.put("2.042", tmp);
		salas.add("2.042");
		tmp = new String[]{};
		zonas.put("2.043", tmp);
		salas.add("2.043");
		tmp = new String[]{};
		zonas.put("2.044", tmp);
		salas.add("2.044");
		tmp = new String[]{};
		zonas.put("2.045", tmp);
		salas.add("2.045");
		tmp = new String[]{};
		zonas.put("2.046", tmp);
		salas.add("2.046");
		tmp = new String[]{};
		zonas.put("2.047", tmp);
		salas.add("2.047");
		tmp = new String[]{"&zone=1", "&zone=2", "&zone=3", "&zone=4", "&zone=5"};
		zonas.put("2.048", tmp);
		salas.add("2.048");
		tmp = new String[]{};
		zonas.put("2.049", tmp);
		salas.add("2.049");
		tmp = new String[]{};
		zonas.put("2.050", tmp);
		salas.add("2.050");
		tmp = new String[]{};
		zonas.put("2.056", tmp);
		salas.add("2.056");
		tmp = new String[]{"&zone=1", "&zone=2"};
		zonas.put("2.058", tmp);
		salas.add("2.058");
		tmp = new String[]{};
		zonas.put("2.059", tmp);
		salas.add("2.059");
		tmp = new String[]{};
		zonas.put("2.060", tmp);
		salas.add("2.060");

		//Nível 3
		tmp = new String[]{};
		zonas.put("3.003", tmp);
		salas.add("3.003");
		tmp = new String[]{};
		zonas.put("3.004", tmp);
		salas.add("3.004");
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4"};
		zonas.put("3.005", tmp); //202 m2
		salas.add("3.005");
		tmp = new String[]{};
		zonas.put("3.007", tmp);
		salas.add("3.007");
		tmp = new String[]{};
		zonas.put("3.008", tmp);
		salas.add("3.008");
		tmp = new String[]{};
		zonas.put("3.010", tmp);
		salas.add("3.010");
		tmp = new String[]{};
		zonas.put("3.011", tmp);
		salas.add("3.011");
		tmp = new String[]{};
		zonas.put("3.012", tmp);
		salas.add("3.012");
		tmp = new String[]{};
		zonas.put("3.014", tmp);
		salas.add("3.014");
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4","&zone=5","&zone=6","&zone=7","&zone=8","&zone=9"};
		zonas.put("3.015", tmp); //355 m2
		salas.add("3.015");
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4"};
		zonas.put("3.018", tmp); //174 m2
		salas.add("3.018");
		tmp = new String[]{};
		zonas.put("3.022", tmp);
		salas.add("3.022");
		tmp = new String[]{};
		zonas.put("3.027", tmp);
		salas.add("3.027");
		tmp = new String[]{};
		zonas.put("3.029", tmp);
		salas.add("3.029");
		tmp = new String[]{};
		zonas.put("3.030", tmp);
		salas.add("3.030");
		tmp = new String[]{};
		zonas.put("3.031", tmp);
		salas.add("3.031");
		tmp = new String[]{};
		zonas.put("3.032", tmp);
		salas.add("3.032");
		tmp = new String[]{"&zone=1", "&zone=2", "&zone=3", "&zone=4"};
		zonas.put("3.033", tmp);
		salas.add("3.033");
		tmp = new String[]{};
		zonas.put("3.034", tmp);
		salas.add("3.034");
		tmp = new String[]{};
		zonas.put("3.035", tmp);
		salas.add("3.035");
		tmp = new String[]{};
		zonas.put("3.036", tmp);
		salas.add("3.036");
		tmp = new String[]{};
		zonas.put("3.037", tmp);
		salas.add("3.037");
		tmp = new String[]{};
		zonas.put("3.038", tmp);
		salas.add("3.038");
		tmp = new String[]{};
		zonas.put("3.044", tmp);
		salas.add("3.044");
		tmp = new String[]{};
		zonas.put("3.045", tmp);
		salas.add("3.045");
		tmp = new String[]{};
		zonas.put("3.046", tmp);
		salas.add("3.046");
		tmp = new String[]{};
		zonas.put("3.047", tmp);
		salas.add("3.047");
		tmp = new String[]{};
		zonas.put("3.048", tmp);
		salas.add("3.048");
		tmp = new String[]{"&zone=1", "&zone=2", "&zone=3", "&zone=4", "&zone=5"};
		zonas.put("3.049", tmp);
		salas.add("3.049");
		tmp = new String[]{};
		zonas.put("3.050", tmp);
		salas.add("3.050");
		
		//Nível 4
		tmp = new String[]{};
		zonas.put("4.003", tmp);
		salas.add("4.003");
		tmp = new String[]{};
		zonas.put("4.004", tmp);
		salas.add("4.004");
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4","&zone=5","&zone=6"};
		zonas.put("4.005", tmp); //166 m2
		salas.add("4.005");
		tmp = new String[]{};
		zonas.put("4.011", tmp);
		salas.add("4.011");
		tmp = new String[]{};
		zonas.put("4.012", tmp);
		salas.add("4.012");
		tmp = new String[]{};
		zonas.put("4.013", tmp);
		salas.add("4.013");
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4"};
		zonas.put("4.015", tmp); //158 m2
		salas.add("4.015");
		tmp = new String[]{};
		zonas.put("4.018", tmp); //62 m2
		salas.add("4.018");
		tmp = new String[]{};
		zonas.put("4.020", tmp); //20 m2
		salas.add("4.020");
		tmp = new String[]{};
		zonas.put("4.021", tmp);
		salas.add("4.021");
		tmp = new String[]{"&zone=1","&zone=2","&zone=3","&zone=4"};
		zonas.put("4.022", tmp); //247 m2
		salas.add("4.022");
		tmp = new String[]{};
		zonas.put("4.026", tmp);
		salas.add("4.026");
		tmp = new String[]{};
		zonas.put("4.031", tmp);
		salas.add("4.031");
		tmp = new String[]{};
		zonas.put("4.033", tmp);
		salas.add("4.033");
		tmp = new String[]{};
		zonas.put("4.034", tmp);
		salas.add("4.034");
		tmp = new String[]{};
		zonas.put("4.035", tmp);
		salas.add("4.035");
		tmp = new String[]{};
		zonas.put("4.036", tmp);
		salas.add("4.036");
		tmp = new String[]{"&zone=1", "&zone=2", "&zone=3", "&zone=4", "&zone=5"};
		zonas.put("4.037", tmp);
		salas.add("4.037");
		tmp = new String[]{};
		zonas.put("4.038", tmp);
		salas.add("4.038");
		tmp = new String[]{};
		zonas.put("4.039", tmp);
		salas.add("4.039");
		tmp = new String[]{};
		zonas.put("4.040", tmp);
		salas.add("4.040");
		tmp = new String[]{};
		zonas.put("4.041", tmp);
		salas.add("4.041");
		tmp = new String[]{};
		zonas.put("4.042", tmp);
		salas.add("4.042");
		tmp = new String[]{};
		zonas.put("4.050", tmp);
		salas.add("4.050");
		tmp = new String[]{};
		zonas.put("4.051", tmp);
		salas.add("4.051");
		tmp = new String[]{"&zone=1", "&zone=2", "&zone=3"};
		zonas.put("4.052", tmp);
		salas.add("4.052");
		tmp = new String[]{};
		zonas.put("4.053", tmp);
		salas.add("4.053");


		
		//Nível 5
		tmp = new String[]{};
		zonas.put("5.006", tmp);
		salas.add("5.006");
		tmp = new String[]{"&zone=1", "&zone=2", "&zone=3", "&zone=4", "&zone=5", "&zone=6", "&zone=7"};
		zonas.put("5.008", tmp);
		salas.add("5.008");
		tmp = new String[]{};
		zonas.put("5.009", tmp);
		salas.add("5.009");
		tmp = new String[]{};
		zonas.put("5.010", tmp);
		salas.add("5.010");
		tmp = new String[]{};
		zonas.put("5.011", tmp);
		salas.add("5.011");
		tmp = new String[]{};
		zonas.put("5.012", tmp);
		salas.add("5.012");
		tmp = new String[]{};
		zonas.put("5.013", tmp);
		salas.add("5.013");
		tmp = new String[]{};
		zonas.put("5.014", tmp);
		salas.add("5.014");
		tmp = new String[]{};
		zonas.put("5.015", tmp);
		salas.add("5.015");
		tmp = new String[]{};
		zonas.put("5.016", tmp);
		salas.add("5.016");
		tmp = new String[]{};
		zonas.put("5.017", tmp);
		salas.add("5.017");
		tmp = new String[]{};
		zonas.put("5.018", tmp);
		salas.add("5.018");
		tmp = new String[]{};
		zonas.put("5.019", tmp);
		salas.add("5.019");
		tmp = new String[]{};
		zonas.put("5.020", tmp);
		salas.add("5.020");
		tmp = new String[]{};
		zonas.put("5.021", tmp);
		salas.add("5.021");
		tmp = new String[]{};
		zonas.put("5.022", tmp);
		salas.add("5.022");
		tmp = new String[]{"&zone=1","&zone=2"};
		zonas.put("5.023", tmp); //85 m2
		salas.add("5.023");
		tmp = new String[]{"&zone=1", "&zone=2"};
		zonas.put("5.024", tmp);
		salas.add("5.024");
		tmp = new String[]{};
		zonas.put("5.025", tmp);
		salas.add("5.025");
		tmp = new String[]{};
		zonas.put("5.026", tmp);
		salas.add("5.026");
		tmp = new String[]{};
		zonas.put("5.027", tmp);
		salas.add("5.027");
		tmp = new String[]{};
		zonas.put("5.028", tmp);
		salas.add("5.028");
		tmp = new String[]{};
		zonas.put("5.029", tmp);
		salas.add("5.029");
		tmp = new String[]{};
		zonas.put("5.030", tmp);
		salas.add("5.030");
		tmp = new String[]{};
		zonas.put("5.031", tmp);
		salas.add("5.031");
		tmp = new String[]{};
		zonas.put("5.031A", tmp);
		salas.add("5.031A");
		tmp = new String[]{};
		zonas.put("5.032", tmp);
		salas.add("5.032");
		tmp = new String[]{};
		zonas.put("5.042", tmp);
		salas.add("5.042");

		
		//Nível 6
		tmp = new String[]{"&zone=1", "&zone=2"};
		zonas.put("6.007", tmp);
		salas.add("6.007");
		tmp = new String[]{};
		zonas.put("6.008", tmp);
		salas.add("6.008");
		tmp = new String[]{};
		zonas.put("6.009", tmp);
		salas.add("6.009");
		tmp = new String[]{};
		zonas.put("6.010", tmp);
		salas.add("6.010");
		tmp = new String[]{};
		zonas.put("6.011", tmp);
		salas.add("6.011");
		tmp = new String[]{};
		zonas.put("6.012", tmp);
		salas.add("6.012");
		tmp = new String[]{};
		zonas.put("6.013", tmp);
		salas.add("6.013");
		tmp = new String[]{};
		zonas.put("6.014", tmp);
		salas.add("6.014");
		tmp = new String[]{};
		zonas.put("6.015", tmp);
		salas.add("6.015");
		tmp = new String[]{};
		zonas.put("6.016", tmp);
		salas.add("6.016");
		tmp = new String[]{};
		zonas.put("6.017", tmp);
		salas.add("6.017");
		tmp = new String[]{};
		zonas.put("6.018", tmp);
		salas.add("6.018");
		tmp = new String[]{};
		zonas.put("6.019", tmp);
		salas.add("6.019");
		tmp = new String[]{};
		zonas.put("6.020", tmp);
		salas.add("6.020");
		tmp = new String[]{};
		zonas.put("6.021", tmp);
		salas.add("6.021");
		tmp = new String[]{};
		zonas.put("6.022", tmp);
		salas.add("6.022");
		tmp = new String[]{};
		zonas.put("6.023", tmp);
		salas.add("6.023");
		tmp = new String[]{};
		zonas.put("6.024", tmp);
		salas.add("6.024");
		tmp = new String[]{};
		zonas.put("6.025", tmp);
		salas.add("6.025");
		tmp = new String[]{};
		zonas.put("6.026", tmp);
		salas.add("6.026");
		tmp = new String[]{};
		zonas.put("6.027", tmp);
		salas.add("6.027");
		tmp = new String[]{};
		zonas.put("6.028", tmp);
		salas.add("6.028");
		tmp = new String[]{};
		zonas.put("6.030", tmp);
		salas.add("6.030");
		tmp = new String[]{"&zone=1", "&zone=2"};
		zonas.put("6.031", tmp);
		salas.add("6.031");
		tmp = new String[]{};
		zonas.put("6.032", tmp);
		salas.add("6.032");
		tmp = new String[]{};
		zonas.put("6.033", tmp);
		salas.add("6.033");
		tmp = new String[]{};
		zonas.put("6.034", tmp);
		salas.add("6.034");
		tmp = new String[]{};
		zonas.put("6.035", tmp);
		salas.add("6.035");
		tmp = new String[]{};
		zonas.put("6.036", tmp);
		salas.add("6.036");
		tmp = new String[]{};
		zonas.put("6.037", tmp);
		salas.add("6.037");
		tmp = new String[]{};
		zonas.put("6.038", tmp);
		salas.add("6.038");
		tmp = new String[]{};
		zonas.put("6.038A", tmp);
		salas.add("6.038A");
		tmp = new String[]{};
		zonas.put("6.039", tmp);
		salas.add("6.039");
		tmp = new String[]{};
		zonas.put("6.050", tmp);
		salas.add("6.050");
	
		
		for(int i = 0; i < salas.size(); i++) { //percorre todas as folhas do excel

			String sala = salas.get(i);
			
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
						if(occupancy != 0 && occupancy!=1) {
							occupancy = 0;
						}
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
					if(occupancy != 0 && occupancy!=1) {
						occupancy = 0;
					}
					
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
			FileWriter myWriter = new FileWriter(timeStamp+"-log.txt");
			myWriter.write(log);
			myWriter.close();
			FileWriter myWriter2 = new FileWriter(timeStamp+".csv");
			myWriter2.write(salvar);
			myWriter2.close();
			
		} catch (Exception e) {
			System.out.println("Erro salvando");
		}
		
		System.out.println("fim");
	}
}
