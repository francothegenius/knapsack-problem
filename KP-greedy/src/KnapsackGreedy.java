import java.util.Arrays;
import java.util.Comparator;

public class KnapsackGreedy {
	
	private double peso;
	private double valor;
	private double costo;
	
	
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
		KnapsackGreedy[] items = new KnapsackGreedy[n];
		
		for(int i = 0; i < n; i++) {
			items[i] = new KnapsackGreedy(pesos[i], valores[i]);
		}
		
		Arrays.sort(items, new Comparator<KnapsackGreedy>() {

			@Override
			public int compare(KnapsackGreedy itemA, KnapsackGreedy itemB) {
				// TODO Auto-generated method stub
				return itemB.getCosto().compareTo(itemA.getCosto());
			}
		});
		int capRestante = capMax;
		double valorFinal = 0;
		
		int i = 0;
		boolean continuar = true;
		
		while(continuar) {
			if(items[i].getPeso() <= capRestante) {
				capRestante -= items[i].getPeso();
				valorFinal += items[i].getValor();
				System.out.println("Item: "+ i + " -> Peso: " + items[i].getPeso()
						+ " -> Valor: " + items[i].getValor());
			}
			
			if(items[i].getPeso() > capRestante) {
				i++;
			}
			
			if(i == n) {
				continuar = false;
			}
		}
		System.out.println("Valor maximo --> "+ valorFinal);
	}
	
	
	public static void main(String[] args) {
		
		int pesos[] = new int[] {15, 10, 2, 4};
		int valores[] = new int[] {30, 25, 2, 6};
		int capMax = 37;
		int n = pesos.length;
		
		knapsackGreAl(pesos, valores, capMax, n);
	}
}
