package co.uniquindio.pr2.agenda.test;

import co.uniquindio.pr2.agenda.exceptions.ContactoException;
import co.uniquindio.pr2.agenda.model.Agenda;
import co.uniquindio.pr2.agenda.model.Contacto;

public class Main {


		private Agenda agenda;


	public static void main(String[] args) throws ContactoException {


		Contacto contacto = new Contacto("Juan", "Chucho", "As", "321", "sas", 0,0);

		Agenda agenda = new Agenda("Agenda de Juan", 5,2,3);

		agenda.aniadirContacto(contacto);

		Contacto[] contact = agenda.getListaContactos();
		Contacto contacto2 = contact[0];
		 System.out.println(contacto2);









	}


	public Agenda getAgenda() {
		return agenda;
	}


	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}


}


