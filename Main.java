package application;

import java.io.IOException;

import co.uniquindio.pr2.agenda.controllers.ContactoController;
import co.uniquindio.pr2.agenda.model.Agenda;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


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
}
