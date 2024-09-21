package PresentationLayoud.Controllers;


import BusinessLayer.Model.GestioUser;
import PresentationLayoud.Views.AgeRoyaleView;
import PresentationLayoud.Views.ConfigView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase encargada de controlar la configuración del usuario. Permite gestionar es delogueo y la eliminación de usuario de la base de datos.
 */
public class CtrlConfig implements ActionListener {


    // Atributos que almacenan la vista y los modelos necesarios para que ocurran eventos.
    private final ConfigView view;
    private final AgeRoyaleView frame;
    private final GestioUser gestioUser;

    /**
     * Constructor con parámetros.
     * @param view          Vista de config.
     * @param gestioUser    Modelo que permite la gestion de usuarios.
     * @param frame         Frame principal para cambio de vista.
     */
    public CtrlConfig(ConfigView view, GestioUser gestioUser, AgeRoyaleView frame){
        this.view = view;
        this.gestioUser = gestioUser;
        this.frame = frame;
    }


    /**
     * Se ha recibido un evento de la vista de Config.
     * @param e ActionEvent que ha ocurrido.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case ConfigView.BTN_DELETE_ACCOUNT:
                gestioUser.deleteUser();            // Borramos usuario de la base de datos.
                frame.showLogin();                  // Mostramos login.
                break;

            case ConfigView.BTN_LOGOUT:

                frame.showLogin();                  // Mostramos Login.
                gestioUser.setUsuari(null);         // Eliminamos rastro del usuario en RAM.
                break;

            case ConfigView.BTN_CONFIG:
                frame.showPrevious();               // Mostramos carta anterior dohnde se ha presionado Config.
                break;

            default:
                System.err.println("Unknown action command " + e.getActionCommand());
        }
    }
}

