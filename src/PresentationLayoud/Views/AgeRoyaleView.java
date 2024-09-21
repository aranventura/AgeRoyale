package PresentationLayoud.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Frame principal que contiene las vistas para realizar cambio de cartas.
 */
public class AgeRoyaleView extends JFrame {

    /**
     * Última vista mostrada.
     */
    private String previousCardShowed;
    /**
     * Gestionador de cartas a mostrar.
     */
    private final CardLayout cardManager;

    private static final String CARD_LOGIN = "CARD_LOGIN";
    private static final String CARD_REGISTER = "CARD_REGISTER";
    private static final String CARD_MENU_PRINCIPAL = "CARD_MENU_PRINCIPAL";
    private static final String CARD_CONFIG = "CARD_CONFIG";
    private static final String CARD_PARTIDA = "CARD_PARTIDA";
    private static final String CARD_PARTIDA_GUARDADA = "CARD_PARTIDA_GUARDADA";
    private static final String CARD_RANKING = "CARD_RANKING";
    private static final String CARD_GUARDAR_PARTIDA = "CARD_GUARDAR_PARTIDA";
    private static final String CARD_PARTIDA_REPETIDA = "CARD_PARTIDA_REPETIDA";

    /**
     * Constructor del frame principal.
     * @param loginView             Vista de login.
     * @param registerView          Vista de register.
     * @param menuPrincipalView     Vista de menu principal.
     * @param configView            Vista de config.
     * @param partidaView           Vista de partida.
     * @param savedGamesView        Vista de partidas guardadas.
     * @param rankingView           Vista de ranking.
     * @param guardarPartidaView    Vista de guardar partida.
     */
    public AgeRoyaleView(LoginView loginView, RegisterView registerView, MenuPrincipalView menuPrincipalView, ConfigView configView, PartidaView partidaView, SavedGamesView savedGamesView, RankingView rankingView, GuardarPartidaView guardarPartidaView, PartidaRepetidaView partidaRepetidaView) {

        cardManager = new CardLayout();
        // Init vistas
        getContentPane().setLayout(cardManager);
        getContentPane().add(loginView,CARD_LOGIN);
        getContentPane().add(registerView,CARD_REGISTER);
        getContentPane().add(menuPrincipalView, CARD_MENU_PRINCIPAL);
        getContentPane().add(configView, CARD_CONFIG);
        getContentPane().add(partidaView, CARD_PARTIDA);
        getContentPane().add(savedGamesView, CARD_PARTIDA_GUARDADA);
        getContentPane().add(rankingView, CARD_RANKING);
        getContentPane().add(guardarPartidaView, CARD_GUARDAR_PARTIDA);
        getContentPane().add(partidaRepetidaView, CARD_PARTIDA_REPETIDA);
        configureWindow();
    }

    /**
     * Método encargado de configurar la ventana.
     */
    private void configureWindow() {
        setTitle("Age Royale");                                     // Título del Frame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             // Definir cierre de la aplicación al clickar sobre la cruz.
        setSize(Toolkit.getDefaultToolkit().getScreenSize());       // Ajustar la dimensión de la pantalla según la pantalla del ordenador.
        System.out.println(getSize());
        setLocationRelativeTo(null);
        cargarIconoApp();

    }

    /**
     * Método encargado de mostrar la vista de Register.
     */
    public void showRegister(){
        cardManager.show(getContentPane(), CARD_REGISTER);
    }

    /**
     * Método encargado de mostrar la vista de Login.
     */
    public void showLogin() {
        cardManager.show(getContentPane(), CARD_LOGIN);
    }

    /**
     * Método encargado de mostrar la vista de Menu Principal.
     */
    public void showMenuPrincipal() {
        cardManager.show(getContentPane(), CARD_MENU_PRINCIPAL);
        previousCardShowed = CARD_MENU_PRINCIPAL;
    }

    /**
     * Método encargado de mostrar la vista de Config.
     */
    public void showConfig() {
        cardManager.show(getContentPane(),CARD_CONFIG);
    }

    /**
     * Método encargado de mostrar la vista de mostrar la vista anterior.
     */
    public void showPrevious() {
        cardManager.show(getContentPane(), previousCardShowed);
    }

    /**
     * Método encargado de mostrar la vista de Partida.
     */
    public void showPartida() {
        cardManager.show(getContentPane(),CARD_PARTIDA);
        previousCardShowed = CARD_PARTIDA;
    }

    public void showPartidaRepetida() {
        cardManager.show(getContentPane(),CARD_PARTIDA_REPETIDA);
        previousCardShowed = CARD_PARTIDA_REPETIDA;
    }

    /**
     * Método encargado de mostrar la vista de Partidas Guardadas.
     */
    public void showPartidasGuardadas() {
        cardManager.show(getContentPane(), CARD_PARTIDA_GUARDADA);
        previousCardShowed = CARD_PARTIDA_GUARDADA;
    }

    /**
     * Método encargado de mostrar la vista de Ranking.
     */
    public void showRanking() {
        cardManager.show(getContentPane(), CARD_RANKING);
        previousCardShowed = CARD_RANKING;
    }

    /**
     * Método encargado de mostrar la vista de Guardar partida.
     */
    public void showGuardarPartida() {
        cardManager.show(getContentPane(),CARD_GUARDAR_PARTIDA);
        previousCardShowed = CARD_GUARDAR_PARTIDA;
    }

    /**
     * Método encargado de cargar el icono de la App en el DOCK de mac.
     */
    public void cargarIconoApp() {
        // Cargar icono aplicación.
        ImageIcon frameIcon = new ImageIcon(new ImageIcon("src/Icons/crown_Img.png").getImage().getScaledInstance(269,321,Image.SCALE_DEFAULT));
        // Windows
        setIconImage(frameIcon.getImage());
        /*
        // MacOs
        Application application = Application.getApplication();
        application.setDockIconImage(frameIcon.getImage());

         */
    }

    /**
     * Método encargado de mostrar el frame principal.
     */
    public void start() {
        setVisible(true);
    }

    /**
     * Getter de última vista mostrada.
     * @return
     */
    public String getPreviousCardShowed() {
        return previousCardShowed;
    }

    /**
     * Setter de última vista mostrada.
     * @param card
     */
    public void setPreviousCardShowed(String card) {
        previousCardShowed = card;
    }
}
