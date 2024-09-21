package PresentationLayoud.Controllers;

import BusinessLayer.Model.GestioUser;
import PresentationLayoud.Views.AgeRoyaleView;
import PresentationLayoud.Views.LoginView;
import PresentationLayoud.Views.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase encargada de controlar el registro de la partida.
 */
public class CtrlRegister implements ActionListener {

    // Atributos que almacenan la vista y los modelos necesarios para que ocurran eventos.
    private final RegisterView view;
    private final GestioUser model;
    private final AgeRoyaleView frame;

    /**
     * Constructor base del controlador que gestiona la partida.
     * @param view  Vista de register.
     * @param model Modelo que gestiona los usarios.
     * @param frame Modelo que gestiona el Frame principal para cambiar de carta.
     */
    public CtrlRegister(RegisterView view, GestioUser model, AgeRoyaleView frame){
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

            case RegisterView.BTN_REGISTER:         // Si se clicka el botón de Register.

                // Recogemos información introducida por el usuario.
                String user = view.getUsernameTxtFTxtF().getText();
                String email =  view.getEmailTxtF().getText();
                String pass = view.getPasswordTxtF().getText();
                String pass_re = view.getRepeatPasswordTxtF().getText();

                // Comprobamos SignIn
                if(model.signIn(user,email,pass,pass_re)) {
                    frame.showMenuPrincipal();                  // SignIn correcto.
                } else {
                    JOptionPane frame = new JOptionPane();
                    JOptionPane.showMessageDialog(frame, model.getDataError(), "Error.", JOptionPane.INFORMATION_MESSAGE);  // Mensaje de error, SignIn insatisfactorio.
                }
                break;

            case RegisterView.BTN_ATRAS:    // Botón de ir hacia atrás.

                view.resetTextFields();     // Reiniciamos JTextFields.
                frame.showLogin();          // Vamos a vista de login.¡
                break;

            default:
                System.err.println("Unknown action command " + e.getActionCommand());
        }
    }
}

