import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class KnapsackGreedy {
	
	private double peso;
	private double valor;
	private double costo;
	private static int elementos=0;
	private static double capMax;
	private static double valorFinal = 0;
	
	static int[] pesos;
	static int[] valores;
	
	public KnapsackGreedy(double peso, double valor) {
		this.peso = peso;
		this.valor = valor;
		this.costo = new Double(valor / peso);
	}


	public double getPeso() {
		return peso;
	}


	public double getValor() {
		return valor;
	}


	public Double getCosto() {
		return costo;
	}
	
	
	public static void knapsackGreAl(int pesos[], int valores[], int capMax, int n) {
		//inicializa cada item con su respectivo valor y peso
		KnapsackGreedy[] items = new KnapsackGreedy[n];
		for(int i = 0; i < n; i++) {
			items[i] = new KnapsackGreedy(pesos[i], valores[i]);
		}
		
		//ordena el arreglo de items de forma descediente segun su costo
		Arrays.sort(items, new Comparator<KnapsackGreedy>() {

			@Override
			public int compare(KnapsackGreedy itemA, KnapsackGreedy itemB) {
				// TODO Auto-generated method stub
				return itemB.getCosto().compareTo(itemA.getCosto());
			}
		});
		
		
		int capRestante = capMax;
		
		
		int i = 0;
		boolean continuar = true;
		
		while(continuar) {
			//agrega items a la mochila mientras exista espacio
			if(items[i].getPeso() <= capRestante) {
				capRestante -= items[i].getPeso();
				valorFinal += items[i].getValor();
				System.out.println("Item: "+ i + " -> Peso: " + items[i].getPeso()
						+ " -> Valor: " + items[i].getValor());
			}
			
			//si el item no se puede agregar, cambia al siguiente item
			if(items[i].getPeso() > capRestante) {
				i++;
			}
			
			//detiene busqueda cuando todos fueron probados
			if(i == n) {
				continuar = false;
			}
		}
		System.out.println("Valor maximo --> "+ valorFinal);
	}
	
	public  void lecturaArchivo(String archivo) throws IOException, FileNotFoundException {
		try {
			BufferedReader lectura = new BufferedReader(new FileReader(archivo));
			String linea= lectura.readLine();
			elementos=Integer.parseInt(linea);
			linea=lectura.readLine();
			capMax=Integer.parseInt(linea);
			int cont=1;
			pesos= new int[elementos+1];
			valores= new int[elementos+1];	
			pesos[0]=0;
			valores[0]=0;
			StringTokenizer st;
			while((linea=lectura.readLine())!=null) {
				st=new StringTokenizer(linea);
				valores[cont]=Integer.parseInt(st.nextToken());
				pesos[cont]=Integer.parseInt(st.nextToken());
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
		pw.println("Resultado es: "+ String.valueOf(valorFinal));
		pw.close();
	}
	
	
	public static void main(String[] args) {
		
		int pesos[] = new int[] {15, 10, 2, 4};
		int valores[] = new int[] {30, 25, 2, 6};
		int capMax = 37;
		int n = pesos.length;
		
		knapsackGreAl(pesos, valores, capMax, n);
	}
}
