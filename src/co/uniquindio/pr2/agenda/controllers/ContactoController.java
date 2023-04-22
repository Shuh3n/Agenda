package co.uniquindio.pr2.agenda.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import application.Main;
import co.uniquindio.pr2.agenda.model.Agenda;
import co.uniquindio.pr2.agenda.model.Categoria;
import co.uniquindio.pr2.agenda.model.Contacto;
import co.uniquindio.pr2.agenda.model.Fecha;
import co.uniquindio.pr2.agenda.model.Grupo;
import co.uniquindio.pr2.agenda.model.Hora;
import co.uniquindio.pr2.agenda.model.Reunion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ContactoController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombreGrupo;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnLimpiarInfoGrupo;

    @FXML
    private Button btnCrearGRupo;

    @FXML
    private ComboBox<Categoria> comboCategoria;
    @FXML
    private ComboBox<Fecha> comboBoxFechaMes;
    @FXML
    private ComboBox<Hora> comboBoxHora;

    @FXML
    private TextField txtFechaDia;

    @FXML
    private TextField txtEmail;

    @FXML
    private TableColumn<Contacto, String> columnNombre;

    @FXML
    private TableColumn<Contacto, String> columnEmail;
    @FXML
    private TableColumn<Reunion, String> columnDescripcion;
    @FXML
    private TableColumn<Reunion, String> columnFecha;
    @FXML
    private TableColumn<Reunion, String> columnHora;


    @FXML
    private TextField txtAlias;

    @FXML
    private TableColumn<Contacto, String> columnAlias;

    @FXML
    private TableColumn<Contacto, String> columnDireccion;

    @FXML
    private TableColumn<Contacto, String> columnTelefono;

    @FXML
    private TableColumn<Grupo, String> columnNombre1;

    @FXML
    private TableColumn<Grupo, Categoria> columnCategoria;

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiarReunion;
    @FXML
    private Button btnCrearReunion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtHora;

    @FXML
    private Button btnAnadir;

    @FXML
    private Button btnEliminarGrupo;

    @FXML
    private TableView<Contacto> tableViewContactos;

    @FXML
    private TableView<Grupo> tableViewGrupos;
    @FXML
    private TableView<Reunion> tableViewReuniones;

    private Contacto contactoSeleccion;

    private Grupo grupoSeleccion;

	private Stage stage;

	private Agenda agenda;

	private Main main;

	ObservableList<Contacto> listaContactos = FXCollections.observableArrayList();

	ObservableList<Grupo> listaGrupos= FXCollections.observableArrayList();

	ObservableList<Reunion> listaReuniones = FXCollections.observableArrayList();


    @FXML
    void agregarContacto(ActionEvent event) {
    	String nombre = txtNombre.getText();
    	String alias = txtAlias.getText();
    	String direccion = txtDireccion.getText();
    	String telefono = txtTelefono.getText();
    	String email = txtEmail.getText();

    	if(validarDatos(nombre, alias, direccion, telefono, email)){

    		crearContacto(nombre, alias, direccion, telefono, email);
    		txtNombre.clear();
    		txtAlias.clear();
    		txtDireccion.clear();
    		txtTelefono.clear();
    		txtEmail.clear();
    	}
    }

    private void crearContacto(String nombre, String alias, String direccion, String telefono, String email) {
    	String aux = main.crearContacto(nombre, alias, direccion, telefono, email);

    	if(aux.equals("1")){
    		mostrarMensaje("Notificación", "Notificiación de contacto", "El contacto ha sido creado con éxito", AlertType.INFORMATION);
    		tableViewContactos.getItems().clear();
    		tableViewContactos.setItems(getContacto());



    	}
    	else{
    		if(aux.equals("0")){
    			mostrarMensaje("Notificación", "Notificiación de contacto", "El contacto ya existe", AlertType.INFORMATION);
    		}
    		else{
    			mostrarMensaje("Notificación", "Notificiación de contacto", "La agenda está llena", AlertType.INFORMATION);
    		}

    	}

    }

	private ObservableList<Contacto> getContacto(){
		listaContactos.addAll(main.getAgenda().getListaContactos());
		return listaContactos;
	}

	private ObservableList<Grupo> getGrupo(){
		listaGrupos.addAll(main.getAgenda().getListaGrupos());
		return listaGrupos;
	}

	private ObservableList<Reunion> getReunion(){
		listaReuniones.addAll(main.getAgenda().getListaReuniones());
		return listaReuniones;
	}



	@FXML
    void limpiarCampos(ActionEvent event) {
		txtNombre.clear();
		txtAlias.clear();
		txtDireccion.clear();
		txtTelefono.clear();
		txtEmail.clear();


    }


    @FXML
    void eliminarContacto(ActionEvent event) {

    	if(contactoSeleccion!=null){
    		int confirmacion= JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar este contacto?");

    		if(confirmacion==0){

	    		if(main.eliminarContacto(contactoSeleccion)){
	    			listaContactos.remove(contactoSeleccion);
	    			mostrarMensaje("Contacto eliminado", "Eliminacion de contacto", "Se ha eliminado el contacto con exito", AlertType.INFORMATION);
	    		}
	    		else{
	    			mostrarMensaje("Contacto eliminado", "Eliminacion de contacto", "No se ha podido eliminar el contacto", AlertType.WARNING);
	    		}
    		}
    	}
    	else{
    		mostrarMensaje("Contacto seleccion", "Contacto Seleccion", "No se ha seleccionado ningun contacto", AlertType.WARNING);
    	}
    }




    @FXML
    void crearGrupo(ActionEvent event) {

    	String nombre1 = txtNombreGrupo.getText();
    	Categoria cat = comboCategoria.getValue();

    	if(validarDatos(nombre1, cat)){

    		crearGrupo(nombre1, cat);

    	}

    }

    private void crearGrupo(String nombre1, Categoria cat) {
    	String aux = main.crearGrupo(nombre1, cat);

    	if(aux.equals("1")){
    		mostrarMensaje("Notificación", "Notificiación de Grupo", "El grupo ha sido creado con éxito", AlertType.INFORMATION);
    		tableViewGrupos.getItems().clear();
    		tableViewGrupos.setItems(getGrupo());
    		txtNombreGrupo.clear();
    		comboCategoria.getSelectionModel().clearSelection();



    	}
    	else{
    		if(aux.equals("0")){
    			mostrarMensaje("Notificación", "Notificiación de Grupo", "El grupo ya existe", AlertType.INFORMATION);
    		}
    		else{
    			mostrarMensaje("Notificación", "Notificiación de Grupo", "La agenda está llena", AlertType.INFORMATION);
    		}

    	}


	}

	@FXML
    void limpiarInfoGrupo(ActionEvent event) {
		txtNombreGrupo.clear();
		comboCategoria.getSelectionModel().clearSelection();

    }
    @FXML
    void eliminarGrupo(ActionEvent event) {
    	if(grupoSeleccion!=null){
    		int confirmacion= JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar este contacto?");

    		if(confirmacion==0){

	    		if(main.eliminarGrupo(grupoSeleccion)){
	    			listaGrupos.remove(grupoSeleccion);
	    			mostrarMensaje("Grupo eliminado", "Eliminacion de grupo", "Se ha eliminado el contacto con grupo", AlertType.INFORMATION);
	    		}
	    		else{
	    			mostrarMensaje("Grupo eliminado", "Eliminacion de grupo", "No se ha podido eliminar el grupo", AlertType.WARNING);
	    		}
    		}
    	}
    	else{
    		mostrarMensaje("Grupo seleccion", "grupo Seleccion", "No se ha seleccionado ningun grupo", AlertType.WARNING);
    	}
    }

    @FXML
    void crearReunion(ActionEvent event) {
    	String descr = txtDescripcion.getText();
    	Fecha fecha = comboBoxFechaMes.getValue();
    	String diaFecha = txtFechaDia.getText();
    	String hora = txtHora.getText();
    	Hora horario = comboBoxHora.getValue();

    	if(validarDatos(descr, fecha, diaFecha, hora, horario)){
    		String fechaFinal = ""+diaFecha + "/" + fecha;
    		String horaFinal = "" + hora + horario;

    		crearReunion(descr,fechaFinal,horaFinal);
    	}





    }

    private void crearReunion(String descr, String fechaFinal, String horaFinal) {
    	String resultado = main.crearReunion(descr,fechaFinal,horaFinal);

    	if(resultado.equals("1")){
    		mostrarMensaje("Notificación", "Notificiación de Reunion", "La Reunion ha sido creado con éxito", AlertType.INFORMATION);
    		tableViewReuniones.getItems().clear();
    		tableViewReuniones.setItems(getReunion());
        	comboBoxFechaMes.getSelectionModel().clearSelection();
        	comboBoxHora.getSelectionModel().clearSelection();
        	txtDescripcion.clear();
        	txtFechaDia.clear();
        	txtHora.clear();



    	}
    	else{
    		if(resultado.equals("0")){
    			mostrarMensaje("Notificación", "Notificiación de Reunion", "La Reunion ya existe", AlertType.INFORMATION);
    		}
    		else{
    			mostrarMensaje("Notificación", "Notificiación de Reunion", "La agenda está llena", AlertType.INFORMATION);
    		}

    	}

	}

	@FXML
    void limpiarReunion(ActionEvent event) {
    	comboBoxFechaMes.getSelectionModel().clearSelection();
    	comboBoxHora.getSelectionModel().clearSelection();
    	txtDescripcion.clear();
    	txtFechaDia.clear();
    	txtHora.clear();

    }





    @FXML
    void initialize() {

    }

	public void initialize(URL location, ResourceBundle resources) {
		comboCategoria.getItems().addAll(Categoria.AMIGOS,Categoria.FAMILIA, Categoria.FIESTA, Categoria.OFICINA);
		comboBoxFechaMes.getItems().addAll(Fecha.values());
		comboBoxHora.getItems().addAll(Hora.values());

		this.columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnAlias.setCellValueFactory(new PropertyValueFactory<>("alias"));
		this.columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		this.columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		this.columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

		this.columnNombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		this.columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

		this.columnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		this.columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		this.columnHora.setCellValueFactory(new PropertyValueFactory<>("hora"));

		tableViewContactos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null){
				contactoSeleccion= newSelection;
			}
		});

		tableViewGrupos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if(newSelection != null){
				grupoSeleccion= newSelection;
			}
		});



	}

    public void setStage(Stage primaryStage) {
		stage = primaryStage;

	}

	public void setAplicacion(Main main) {
		this.main= main;
		this.agenda = main.getAgenda();

	}


	private boolean validarDatos(String nombre, String alias, String direccion, String telefono, String email) {
		String notificacion = "";

		if (txtNombre == null || txtNombre.equals("")) {
			notificacion += "Número de la cuenta inválido\n";
		}

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


		if (nombre == null || nombre.equals("")) {
			notificacion += "Nombre ingresado es inválido\n";
		}

		if (alias == null || alias.equals("")) {
			notificacion += "Alias ingresado inválido\n";
		}

		if (direccion == null || direccion.equals("")) {
			notificacion += "Dirección ingresada inválida\n";
		}
		if (telefono == null || telefono.equals("")) {
			notificacion += "Teléfono inválido\n";
		} else if(!esNumero(telefono)){
			notificacion += "El teléfono ingresado debe ser numérico\n";
		}

		if (email == null || email.equals("")) {
			notificacion += "Email ingresado inválido\n";
		}

		/*Se valida que la tasa anual ingresada no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Contacto no creado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}

	private boolean validarDatos(String descripcion, Fecha fecha, String fecha2, String hora, Hora horario) {
		String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


		if (descripcion == null || descripcion.equals("")) {
			notificacion += "Descripción ingresada es inválida\n";
		}

		if (fecha == null) {
			notificacion += "Debe seleccionar una fecha\n";
		}
		if (fecha2 == null || fecha2.equals("")) {
			notificacion += "Fecha ingresada inválida\n";
		}
		else {
			if(!esNumero(fecha2)){
				notificacion += "El día ingresado debe ser numérico\n";
			}
			Float aux2 = Float.parseFloat(fecha2);
			if(aux2 < 1 || aux2>31 ){
				notificacion += "El día debe ser de 1 a 31\n";
			}

		}

		if (hora == null || hora.equals("")) {
			notificacion += "Descripción ingresada es inválida\n";
		}
		else {
			if(!esNumero(hora)){
				notificacion += "La hora ingresada debe ser numérica\n";
			}
			Float aux3 = Float.parseFloat(hora);
			if(aux3 < 0 || aux3>24 ){
				notificacion += "La hora debe ser de 0-24horas\n";
			}


		}


		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Reunión no creada", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}

	private boolean validarDatos(String nombre, Categoria cat) {
		String notificacion = "";

		/*Se valida que el saldo ingresado no sea null ni sea cadena vacía,
		además se valida que sea numérico para su correcta conversión */


		if (nombre == null || nombre.equals("")) {
			notificacion += "Nombre ingresado es inválido\n";
		}

		if (cat == null) {
			notificacion += "Debe selecciones una categoría\n";
		}

		if (!notificacion.equals("")) {
			mostrarMensaje("Notificación", "Grupo no creado", notificacion, AlertType.WARNING);
			return false;
		}

		return true;
	}

	private boolean esNumero(String string) {
		try {

			Float.parseFloat(string);

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public void mostrarMensaje(String titulo, String header, String contenido, AlertType alertype) {
		Alert alert = new Alert(alertype);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.showAndWait();
	}

	}

