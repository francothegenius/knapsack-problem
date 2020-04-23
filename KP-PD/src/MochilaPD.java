

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class MochilaPD {
	
	static int elementos=0;
	static int pesoMax=0;
	//int[] pesoElementos= {0,2,3,4,5};
	//int[] valores= {0,3,4,5,6};
	static int[] pesoElementos;
	static int[] valores;
	static int[][] matriz;
	
	public int[][] algoritmo(int elementos, int[] valores, int[] pesoElementos, int pesoMax) {
		matriz=new int[elementos+1][pesoMax+1];
		//Rellenar de cero la primera fila
		for (int i = 0; i < matriz[0].length; i++) {
			matriz[0][i]=0;
		}
		//Rellenar de cero la primer columna
		for (int i = 0; i < matriz.length; i++) {
			matriz[i][0]=0;
		}
		//Comparaciones
		for (int i = 1; i < elementos+1; i++) {
			for (int j = 1; j < pesoMax+1; j++) {
				if (pesoElementos[i]<=j) {
					if (valores[i]+matriz[i-1][j-pesoElementos[i]]>matriz[i-1][j]) {
						matriz[i][j]=valores[i]+matriz[i-1][j-pesoElementos[i]];
					} else {
						matriz[i][j]=matriz[i-1][j];
					}
				} else {
					matriz[i][j]=matriz[i-1][j];
				}
			}
		}
		return matriz;
	}
	
	public  int resultado(int[][] matriz, int elementos, int pesoMax) {
		return matriz[elementos][pesoMax];
	}
	
	/*public static void elementosMochila(int[][] matriz, int elementos, int pesoMax, int[] pesoElementos) {
		int i=elementos,
				j=pesoMax;
		ArrayList<Integer> arr=new ArrayList<>();
		while (i>0 && j>0) {
			if (matriz[i][j]!=matriz[i-1][j]) {
				arr.add(i);
				i-=1;
				j-=-pesoElementos[i];
			} else {
				i-=1;
			}
		
		}
		for (int elemento : arr) {
			System.out.println(elemento);
		}
	}*/
	
	public void imprimirMatriz(int[][] matriz) {
		for (int x=1; x < matriz.length; x++){
	        for (int y=1; y < matriz[x].length; y++)
	              System.out.print(" | " + matriz[x][y]+ " | ");   
	        System.out.println("\n----------------------------------------");

		}
	}
	
	public  void lecturaArchivo(String archivo) throws IOException, FileNotFoundException {
		try {
			BufferedReader lectura = new BufferedReader(new FileReader(archivo));
			String linea= lectura.readLine();
			elementos=Integer.parseInt(linea);
			linea=lectura.readLine();
			pesoMax=Integer.parseInt(linea);
			int cont=1;
			pesoElementos= new int[elementos+1];
			valores= new int[elementos+1];	
			pesoElementos[0]=0;
			valores[0]=0;
			StringTokenizer st;
			while((linea=lectura.readLine())!=null) {
				st=new StringTokenizer(linea);
				valores[cont]=Integer.parseInt(st.nextToken());
				pesoElementos[cont]=Integer.parseInt(st.nextToken());
				cont++;
			}	
			
		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo");
		} catch (IOException e) {
			System.out.println("Hubo un problema de IO");
		}
	}
	
	public void exportarResultados(String nombre, int[][] matriz) throws IOException {
		PrintWriter pw= new PrintWriter(new FileWriter(nombre, true));
		for (int x=1; x < matriz.length; x++){
	        for (int y=1; y < matriz[x].length; y++)
	              pw.print(" | " + matriz[x][y]+ " | ");   
	        pw.println("\n----------------------------------------");
		}
		pw.println("Resultado es: "+ String.valueOf(resultado(matriz, elementos, pesoMax)));
		pw.close();
	}

	public static void main(String[] args) throws IOException, FileNotFoundException {
		int opcion=1;
		MochilaPD mochila=new MochilaPD();
		//int [][] matriz=new int[0][0];
		do {
			System.out.println("SELECCIONA ALGUNA DE LAS OPCIONES: ");
			System.out.println("INTRODUCE 1 PARA INGRESAR DATOS");
			System.out.println("INTRODUCE 2 PARA EXPORTAR DATOS");
			System.out.println("INTRODUCE 3 PARA SALIR");
			Scanner entrada=new Scanner(System.in);
			Scanner scanner=new Scanner(System.in);
			opcion=entrada.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("INGRESE EL NOMBRE DEL ARCHIVO A LEER JUNTO CON .TXT (ASEGURESE DE ESCRIBIRLO BIEN)");
				String nArchivo=scanner.next(); 
				File archivo = new File(nArchivo);
				FileReader archivo1 = new FileReader(archivo);
				mochila.lecturaArchivo(nArchivo);
				Stopwatch timer = new Stopwatch();
				matriz=mochila.algoritmo(elementos, valores, pesoElementos, pesoMax);
				System.out.println("tiempo:"+timer.elapsedTime());
				mochila.imprimirMatriz(matriz);
				System.out.println(mochila.resultado(matriz, elementos, pesoMax));
				break;
			case 2:
				System.out.println("INGRESE EL NOMBRE DEL ARCHIVO JUNTO CON .TXT");
				String eArchivo=scanner.next();
				//FileReader archivo2 = new FileReader(eArchivo);
				mochila.exportarResultados(eArchivo, matriz);
				System.out.println("\nExportado");
				break;
			case 3:
				opcion=3;
				System.out.println("SALIENDO...");
				
			}
		} while (opcion!=3);
		//elementosMochila(matriz, elementos, pesoMax, pesoElementos);		
	}

}
