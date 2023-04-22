package application;

import java.io.IOException;

import co.uniquindio.pr2.agenda.controllers.ContactoController;
import co.uniquindio.pr2.agenda.model.Agenda;
import co.uniquindio.pr2.agenda.model.Categoria;
import co.uniquindio.pr2.agenda.model.Contacto;
import co.uniquindio.pr2.agenda.model.Grupo;
import co.uniquindio.pr2.agenda.model.Reunion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	private Agenda agenda;
	private Stage primaryStage;


	@Override
	public void start(Stage primaryStage) throws IOException {
		this.setPrimaryStage(primaryStage);
		this.setAgenda(new Agenda("Agenda", 10 , 2, 2));


		mostrarVentanaPrincipal();
	}

	private void mostrarVentanaPrincipal() throws IOException {
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/ContactoView.fxml"));
		AnchorPane anchorPane= (AnchorPane)loader.load();
		ContactoController contactoController = loader.getController();
		contactoController.setAplicacion(this);

		Scene scene= new Scene(anchorPane);
		primaryStage.setScene(scene);
		primaryStage.show();
		ContactoController controller = loader.getController();
		controller.setStage(primaryStage);

	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public  String crearContacto(String nombre, String alias, String direccion, String telefono, String email) {
		Contacto newContacto = new Contacto(nombre, alias, direccion, telefono, email);
		String aux = agenda.aniadirContacto(newContacto);
		return aux;
	}

	public Contacto consultarCuenta(String nombre, String telefono) {
		Contacto contacto = agenda.buscarContacto(nombre, telefono);
		return contacto;
	}

	public boolean eliminarContacto(Contacto contactoSeleccion) {
		boolean flag = agenda.eliminarContacto(contactoSeleccion);
		return flag;
	}

	public String crearGrupo(String nombre, Categoria cat){
		Grupo grupo = new Grupo(nombre, cat, 10);
		String aux = agenda.crearGrupo(grupo);
		return aux;

	}

	public boolean eliminarGrupo(Grupo grupoSeleccion) {
		boolean flag = agenda.eliminarGrupo(grupoSeleccion);
		return flag;
	}

	public String crearReunion(String descr, String fechaFinal, String horaFinal) {
		Reunion newreunion = new Reunion(descr, fechaFinal, horaFinal);
		String aux = agenda.crearReunion(newreunion);
		return aux;
	}
}
