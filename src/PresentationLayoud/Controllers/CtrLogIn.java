package PresentationLayoud.Controllers;

import BusinessLayer.Model.GestioUser;
import BusinessLayer.Model.MagatzemPartida;
import PresentationLayoud.Views.AgeRoyaleView;
import PresentationLayoud.Views.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase encargada de controlar la gestión de login de la partida.
 */
public class CtrLogIn implements ActionListener {

    // Atributos que almacenan la vista y los modelos necesarios para que ocurran eventos.
    private final LoginView view;
    private final GestioUser model;
    private final AgeRoyaleView frame;

    /**
     * Constructor base del controlador que gestiona el login de la partida.
     * @param view  Vista de login.
     * @param model Modelo de gestion de usuarios.
     * @param frame Frame principal para poder cambiar de vista.
     */
    public CtrLogIn(LoginView view, GestioUser model,AgeRoyaleView frame){
        this.view = view;
        this.model = model;
        this.frame = frame;
    }


    /**
     * Se ha recibido un evento de la vista de Register.
     * @param e ActionEvent que ha ocurrido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case LoginView.BTN_LOGIN:

                // Recogemos información de login del usuario.
                String name =  view.getEmailTxtF().getText();
                String pass = view.getPasswordTxTF().getText();

                if(model.logIn(name,pass)) {              // Comprobamos información de loign.

                    view.resetTextFields();             // Reseteamos los TextFields de la vista.
                    frame.showMenuPrincipal();          // Mostramos menu principal.

                } else {                                    // Mensaje de error si el usuario no es válido.
                    JOptionPane frame = new JOptionPane();
                    JOptionPane.showMessageDialog(frame, model.getDataError(), "Error.",JOptionPane.INFORMATION_MESSAGE);
                }
                break;

            case LoginView.BTN_REGISTER:

                view.resetTextFields();     // Reseteamos los JTextFields por si volvemos hacia atrás.
                frame.showRegister();       // Mostramos vista de Register.
            break;

            default:
                System.err.println("Unknown action command " + e.getActionCommand());
        }
    }
}

