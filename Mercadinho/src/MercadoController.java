import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MercadoController {
	
	private MercadoView mercadoView;
	private MercadoModel mercadoModel;
	
	public MercadoController(MercadoView view, MercadoModel model) {
		this.mercadoView = view;
		this.mercadoModel = model;
	
		this.mercadoView.setComboBox(mercadoModel.criaProdutos());
		this.mercadoView.addMercadoListener(new MercadoListener());
	}
	
	class MercadoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				
				if(e.getActionCommand()=="adicionar") {
					
					//Verifica o campo Quantidade
					if(mercadoView.camposCorretos()) {
						//Adiciona no Array de produtos adicionados
						//Insere na List
						mercadoView.addProdutos();
						//Calcula valor e mostra na tela o valor total
						mercadoView.setValorTotal(mercadoModel.somaProdutos(mercadoView.getArrayProdutos()));
					}
					mercadoView.limpaCampos();
				}else if(e.getActionCommand()=="remover") {
					//Verifica se tem algum item selecionada
					if(mercadoView.getListSelect()!=-1) {
						mercadoView.removeProdutos();
						mercadoView.setValorTotal(mercadoModel.somaProdutos(mercadoView.getArrayProdutos()));
					}else if (mercadoView.getArrayProdutos().size()==0){
						JOptionPane.showMessageDialog(null,"Adicione um produto na lista!");
					}else {
						JOptionPane.showMessageDialog(null, "Selecione um produto na lista!");
					}
				}else if(e.getActionCommand()=="comprar") {
					//Verifica se tem algum item adicionada
					if(mercadoView.getArrayProdutos().size()!=0) {
						JOptionPane.showMessageDialog(null,mercadoModel.geraRecibo(mercadoView.getArrayProdutos()));
						mercadoView.zeraDados();
					}else {
						JOptionPane.showMessageDialog(null,"Adicione produtos na lista!");
					}
				}else if(e.getActionCommand()=="boxprodutos") {
					mercadoView.setInfoProduto();
				}
				
			}catch(NumberFormatException erro){
				JOptionPane.showMessageDialog(null, "Preencha as informações corretamente");;
			}
			
		}
		
	}
	

}
