import java.text.DecimalFormat;
import java.util.ArrayList;

public class MercadoModel {
	ArrayList<Produtos> produtos;
	
	public ArrayList<Produtos> criaProdutos() {
		
		produtos = new ArrayList<Produtos>();
		produtos.add(new Produtos("Caixa de Leite",36.98));
		produtos.add(new Produtos("Caixa de Ovos",10.50));
		produtos.add(new Produtos("Pacote de Arroz 5kg",18.99));
		produtos.add(new Produtos("Cacau em Pó 1kg",22.30));
		produtos.add(new Produtos("Suco de Laranja 2L",13.68));
		produtos.add(new Produtos("Energético 800ml",16.99));
		produtos.add(new Produtos("Pacote de Feijão 2kg",12.88));
		produtos.add(new Produtos("Lata de Leite Condensado",5.78));
		
		return produtos;
	}
	
	
	public double somaProdutos(ArrayList<Produtos> products) {
		double total=0;
		for(int i=0;i<products.size();i++) {
			total+=products.get(i).getPreco()*products.get(i).getQtd();
		}
		
		return total;
	}
	
	public String geraRecibo(ArrayList<Produtos> products) {
		DecimalFormat df = new DecimalFormat("#.00");
		String msg="";
		
		msg+="RECIBO\n---------------------------------------------\n";
		for(int i=0;i<products.size();i++){
			msg+=products.get(i).infoLista()+"\n";
		}
		msg+="---------------------------------------------\n";
		msg+="TOTAL R$"+df.format(somaProdutos(products));
		return msg;
	}
	
	
	
}
