import java.text.DecimalFormat;

public class Produtos {
	
	private String nome;
	private double preco;
	private int quantidade;
	
	public Produtos(String nome, double preco){
		this.nome = nome;
		this.preco = preco;
		this.quantidade = 0;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public int getQtd() {
		return quantidade;
	}
	
	public void setQtd(int qtde) {
		this.quantidade = qtde;
	}

	@Override
	public String toString() {
		return this.nome;
	}
	
	public String infoLista() {
		DecimalFormat df = new DecimalFormat("#.00");
		return "("+this.nome+")x"+this.quantidade+" - R$"+df.format(this.quantidade*this.preco);
	}
	
}
