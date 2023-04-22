package co.uniquindio.pr2.agenda.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import co.uniquindio.pr2.agenda.exceptions.ContactoException;

public class Agenda implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *
	 */
	//private static final long serialVersionUID = 1L;
	private String nombre;
	private Contacto[] listaContactos;
	private Grupo[] listaGrupos;
	private Reunion[] listaReuniones;


	public Agenda(String nombre, int numeroContactos,int numeroGrupos,int numeroReuniones) {
		super();
		this.nombre = nombre;
		this.listaContactos = new Contacto[numeroContactos];
		this.listaGrupos = new Grupo[numeroGrupos];
		this.listaReuniones = new Reunion[numeroReuniones];

	}


	public Agenda() {
		super();
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Contacto[] getListaContactos() {
		return listaContactos;
	}


	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}


	public Grupo[] getListaGrupos() {
		return listaGrupos;
	}


	public void setListaGrupos(Grupo[] listaGrupos) {
		this.listaGrupos = listaGrupos;
	}


	public Reunion[] getListaReuniones() {
		return listaReuniones;
	}


	public void setListaReuniones(Reunion[] listaReuniones) {
		this.listaReuniones = listaReuniones;
	}


	@Override
	public String toString() {
		return "Agenda [nombre=" + nombre + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agenda other = (Agenda) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}


	public String aniadirContacto(Contacto newContacto) {
		String aux = "";
		Contacto contacto = buscarContacto(newContacto.getNombre(), newContacto.getTelefono());
		int posDisponible = 0;

		if(contacto != null){
			aux = "0";
			return aux;
		}
		posDisponible = obtenerPosicion();

		if(posDisponible == -1){
			aux = "-1";
			return aux;
		}
		listaContactos[posDisponible] = newContacto;
		aux = "1";
		return aux;
	}


	public Contacto buscarContacto(String nombre , String telefono) {

		Contacto [] contacto = getListaContactos();
		for (int i = 0; i < contacto.length; i++) {
			Contacto contacto2 = contacto[i];
			if(contacto2!=null){
				if(nombre.equals(contacto2.getNombre()) && telefono.equals(contacto2.getTelefono())){
					return contacto2;
				}
			}
		}

		return null;
	}

	public boolean existeContacto(Contacto newContacto) {
		boolean flag = false;
		Contacto [] contacto = getListaContactos();
		for (int i = 0; i < contacto.length; i++) {
			Contacto contacto2 = contacto[i];
			if(newContacto.getTelefono().equals(contacto2.getTelefono())){
				flag = true;
			}
		}
		return flag;
	}

	public int obtenerPosicion(){
		int pos = -1;
		Contacto[] contactos = listaContactos;
		for (int i = 0; i < contactos.length; i++) {
			Contacto contacto = contactos[i];
			if(contacto == null){
				pos = i;
				return pos;
			}
		}
		return pos;

	}

	public String listarContactos(){
		String info = "";
		Contacto [] contacto = getListaContactos();
		for (int i = 0; i < contacto.length; i++) {
			Contacto contacto2 = contacto[i];
			if(existeContacto(contacto2)){
				info += contacto2.toString()+ "\n";
			}
		}
		return info;
	}
	public boolean eliminarContacto(Contacto c){
		Contacto [] contacto = getListaContactos();

		for (int i = 0; i < contacto.length; i++) {
			Contacto contacto2 = contacto[i];
			if(c.equals(contacto2)){
				contacto[i] = null;
				return true;
			}
		}
		return false;
	}

	public String crearGrupo(Grupo grupo){
		String verificador = "0";
		if(!existeGrupo(grupo)){
			int aux = obtenerPosicionGrupo();
			if(aux==-1){
				verificador = "-1";
				return verificador;
			}
			listaGrupos[aux] = grupo;
			verificador = "1";
		}

		return verificador;
	}

	public int obtenerPosicionGrupo(){
		int pos = -1;
		Grupo[] grupos = listaGrupos;
		for (int i = 0; i < grupos.length; i++) {
			Grupo grupoaux = grupos[i];
			if(grupoaux == null){
				pos = i;
				return pos;
			}
		}
		return pos;

	}

	public boolean existeGrupo(Grupo grupo){
		Grupo [] grupos = listaGrupos;

		for (int i = 0; i < grupos.length; i++) {
			Grupo grupo2 = grupos[i];
			if(grupo.equals(grupo2)){
				return true;
			}
		}

		return false;
	}


	public boolean eliminarGrupo(Grupo grupoSeleccion) {
		Grupo [] grupos = getListaGrupos();

		for (int i = 0; i < grupos.length; i++) {
			Grupo grupo2 = grupos[i];
			if(grupoSeleccion.equals(grupo2)){
				grupos[i] = null;
				return true;
			}
		}
		return false;
	}






	public String crearReunion(Reunion newreunion){
		String aux = "";

		int posDisponible = 0;
		Reunion reunion = buscarReunion(newreunion);


		if(reunion != null){
			aux = "0";
			return aux;
		}
		posDisponible = obtenerPosicionReunion();

		if(posDisponible == -1){
			aux = "-1";
			return aux;
		}
		listaReuniones[posDisponible] = newreunion;
		aux = "1";
		return aux;
	}


	public Reunion buscarReunion(Reunion newreunion) {

		Reunion [] reuniones = listaReuniones;
		for (int i = 0; i < reuniones.length; i++) {
			Reunion reunion = reuniones[i];
			if(reunion!=null){
				if(newreunion.equals(reunion)){
					return reunion;
				}
			}
		}

		return null;
	}


	public int obtenerPosicionReunion(){
		int pos = -1;
		Reunion[] reuniones = listaReuniones;
		for (int i = 0; i < reuniones.length; i++) {
			Reunion reunion = reuniones[i];
			if(reunion == null){
				pos = i;
				return pos;
			}
		}
		return pos;

	}


}


