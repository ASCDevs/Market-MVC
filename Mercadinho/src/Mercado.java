public class Mercado {

	public static void main(String[] args) {
		MercadoView view = new MercadoView();
		MercadoModel model = new MercadoModel();
		MercadoController controller = new MercadoController(view,model);
		view.setVisible(true);
	}

}
