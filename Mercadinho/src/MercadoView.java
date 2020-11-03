import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;

public class MercadoView extends JFrame {
	
	private ArrayList<Produtos> produtosAdicionados = new ArrayList<Produtos>();
	private JButton btComprar, btAdicionar, btRemove;
	private JComboBox<Produtos> boxProdutos= new JComboBox<Produtos>();
	private JList<String> lista;
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JTextField quantidade;	//private JSpinner qtdItens;	
	private JLabel labList,labTotal,labProdutos, labAviso,labImg,labTitulo;
	private JLabel prodNome,prodValor,prodLabel, prodQtd;
	private JPanel painelOpcoes, painelResult,painelTitulo;
	private ImageIcon img;
	
	
	public MercadoView(){
		
		this.setTitle("Mercadinho - Carrinho de compras");
		this.setSize(600, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		painelTitulo = new JPanel();
		painelTitulo.setBackground(Color.BLACK);
		painelTitulo.setSize(600,50);
		painelTitulo.setLayout(null);
		
		painelOpcoes = new JPanel();
		painelOpcoes.setBackground(Color.DARK_GRAY);
		painelOpcoes.setBounds(0,50, 600, 300);
		painelOpcoes.setLayout(null);
		
		painelResult = new JPanel();
		painelResult.setBackground(Color.GRAY);
		painelResult.setSize(600,100);
		painelResult.setBounds(0,350, 600, 100);
		painelResult.setLayout(null);
		
		this.add(painelTitulo);
		this.add(painelOpcoes);
		this.add(painelResult);
		
		//Painel titulo;
		img = new ImageIcon(getClass().getResource("/Images/logo.png"));
		labImg = new JLabel();
		labImg.setBounds(50, 8, 36, 36);
		labImg.setIcon(img);
		painelTitulo.add(labImg);
		
		labTitulo = new JLabel("UNIP Market");
		labTitulo.setFont(new Font("Verdana",Font.BOLD,24));
		labTitulo.setForeground(Color.WHITE);
		labTitulo.setBounds(110, 8, 400, 36);
		painelTitulo.add(labTitulo);
		
		//Painel de produtos - Painel Opcoes
		labProdutos = new JLabel("Adicione os produtos desejados: ");
		labProdutos.setFont(new Font("Verdana",Font.PLAIN,12));
		labProdutos.setForeground(Color.WHITE);
		labProdutos.setBounds(50,30,220,20);
		painelOpcoes.add(labProdutos);
		
		boxProdutos.setBounds(50,50,220,25);
		boxProdutos.setActionCommand("boxprodutos");
		boxProdutos.addItem(new Produtos("Selecione um produto",0));
		painelOpcoes.add(boxProdutos);
		
		prodLabel = new JLabel("Informaçoes do produto: ");
		prodLabel.setFont(new Font("Verdana",Font.PLAIN,12));
		prodLabel.setForeground(Color.WHITE);
		prodLabel.setBounds(50,80,220,20);
		painelOpcoes.add(prodLabel);
		
		prodNome = new JLabel("Nome: ");
		prodNome.setFont(new Font("Verdana",Font.PLAIN,12));
		prodNome.setForeground(Color.WHITE);
		prodNome.setBounds(50,100,220,20);
		painelOpcoes.add(prodNome);
		
		prodValor = new JLabel("Valor: ");
		prodValor.setFont(new Font("Verdana",Font.PLAIN,12));
		prodValor.setForeground(Color.WHITE);
		prodValor.setBounds(50,120,220,20);
		painelOpcoes.add(prodValor);
		
		prodQtd = new JLabel("Quantidade: ");
		prodQtd.setFont(new Font("Verdana",Font.PLAIN,12));
		prodQtd.setForeground(Color.WHITE);
		prodQtd.setBounds(50,140,90,20);
		painelOpcoes.add(prodQtd);
		
		quantidade = new JTextField();
		quantidade.setBounds(140,140,30,20);
		painelOpcoes.add(quantidade);
		
		btAdicionar = new JButton("Adicionar produto");
		btAdicionar.setActionCommand("adicionar");
		btAdicionar.setBounds(50, 180, 220, 25);
		painelOpcoes.add(btAdicionar);
		
		labAviso = new JLabel("");
		labAviso.setFont(new Font("Verdana",Font.BOLD,12));
		labAviso.setForeground(Color.RED);
		labAviso.setBounds(50,200,220,60);
		painelOpcoes.add(labAviso);
		
		//Painel de lista - Painel Opcoes
		labList = new JLabel("Lista de produtos adicionados: ");
		labList.setFont(new Font("Verdana",Font.PLAIN,12));
		labList.setForeground(Color.WHITE);
		labList.setBounds(330,30,220,20);
		painelOpcoes.add(labList);
		
		lista = new JList<>(model);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(lista);
		scroll.setBounds(330,50, 220, 200);
		lista.setLayoutOrientation(JList.VERTICAL);
		painelOpcoes.add(scroll);
		
		btRemove = new JButton("Remover Item Selecionado");
		btRemove.setActionCommand("remover");
		btRemove.setBounds(330,260,220,25);
		painelOpcoes.add(btRemove);
		
		//Painel de Resultado
		btComprar = new JButton("Finalizar compra");
		btComprar.setActionCommand("comprar");
		btComprar.setBounds(50,10,200,40);
		painelResult.add(btComprar);
		
		labTotal = new JLabel("R$ 0,00");
		labTotal.setForeground(Color.WHITE);
		labTotal.setFont(new Font("Verdana",Font.PLAIN,32));
		labTotal.setBounds(330,10,200,40);
		painelResult.add(labTotal);
		
	}
	
	public int getQuantidade() {
		return Integer.parseInt(quantidade.getText());
	}
	
	public boolean camposCorretos() {
		int qtd = Integer.parseInt(quantidade.getText());
		
		if(getBoxSelect()==0){
			setAviso("Aviso: Selecione um produto");
			return false;
		}else if(prodNome.getText()=="Nome: "){
			setAviso("Aviso: Selecione o produto");
			return false;
		}else if(qtd<=0){
			setAviso("Aviso: Qtde negativa ou zero");
			return false;
		}else {
			setAviso("");
			return true;
		}
	}
	
	public void setAviso(String texto) {
		labAviso.setText(texto);
	}
	
	public void setComboBox(ArrayList<Produtos> products) {
		
		for(int i=0;i<products.size();i++) {
			boxProdutos.addItem(products.get(i));
		}
	}
	
	public String getProduto() {
		return boxProdutos.getItemAt(getBoxSelect()).infoLista();
	}
	
	public void addProdutos() {
		Produtos prod = boxProdutos.getItemAt(this.getBoxSelect());
		Produtos novo = new Produtos(prod.getNome(),prod.getPreco());
		novo.setQtd(getQuantidade());
		produtosAdicionados.add(novo);
		atualizaLista(produtosAdicionados);
	}
	
	
	public void removeProdutos() {
		produtosAdicionados.remove(getListSelect());
		atualizaLista(produtosAdicionados);
	}
	
	public ArrayList<Produtos> getArrayProdutos(){
		return produtosAdicionados;
	}
	
	public void atualizaLista(ArrayList<Produtos> prod) {
		model.removeAllElements();
		
		for(int i=0;i<prod.size();i++) {
			model.addElement(prod.get(i).infoLista());
		}
		
	}
	
	public int getBoxSelect() {
		return boxProdutos.getSelectedIndex();
	}
	
	public int getListSelect() {
		return lista.getSelectedIndex();
	}
	public void setInfoProduto() {
		if(getBoxSelect()!=0) {
			int index = boxProdutos.getSelectedIndex();
			String nome = boxProdutos.getItemAt(index).getNome();;
			double valor = boxProdutos.getItemAt(index).getPreco();
			prodNome.setText("Nome:  "+nome);
			prodValor.setText("Valor:  R$"+String.valueOf(valor));
		}
		
	}
	
	public void setValorTotal(double valor) {
		DecimalFormat df = new DecimalFormat("#.00");
		if(produtosAdicionados.size()==0) {
			labTotal.setText("R$ 0,00");
		}else {
			labTotal.setText("R$ "+String.valueOf(df.format(valor)));
		}
		
	}
	
	public void limpaCampos(){
		prodNome.setText("Nome: ");
		prodValor.setText("Valor: ");
		quantidade.setText("");
		boxProdutos.setSelectedItem(boxProdutos.getItemAt(0));
	}
	
	public void zeraDados() {
		produtosAdicionados.removeAll(produtosAdicionados);
		atualizaLista(produtosAdicionados);
		labTotal.setText("R$ 0,00");
		limpaCampos();
	}
	public void addMercadoListener(ActionListener listenActions) {
		boxProdutos.addActionListener(listenActions);
		btComprar.addActionListener(listenActions);
		btAdicionar.addActionListener(listenActions);
		btRemove.addActionListener(listenActions);
	}
}
