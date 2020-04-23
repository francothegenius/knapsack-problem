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
		double valorFinal = 0;
		
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
	
	
	public static void main(String[] args) {
		
		int pesos[] = new int[] {15, 10, 2, 4};
		int valores[] = new int[] {30, 25, 2, 6};
		int capMax = 37;
		int n = pesos.length;
		
		knapsackGreAl(pesos, valores, capMax, n);
	}
}
