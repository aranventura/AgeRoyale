import BusinessLayer.Model.GestioUser;
import BusinessLayer.Model.MagatzemPartida;
import PresentationLayoud.Controllers.*;
import PresentationLayoud.Views.*;

public class Main {

    public static void main(String[] args) {

        // Vistas
        LoginView loginView = new LoginView();
        RegisterView registerView = new RegisterView();
        MenuPrincipalView menuPrincipalView = new MenuPrincipalView();
        ConfigView configView = new ConfigView();
        PartidaView partidaView = new PartidaView();
        RankingView rankingView = new RankingView();
        SavedGamesView partidasGuardadasView = new SavedGamesView();
        GuardarPartidaView guardarPartidaView = new GuardarPartidaView();
        PartidaRepetidaView partidaRepetidaView = new PartidaRepetidaView();
        // Modelos
        GestioUser gestioUser = new GestioUser();
        MagatzemPartida magatzemPartida = new MagatzemPartida();

        // Main Frame
        AgeRoyaleView frame = new AgeRoyaleView(loginView,registerView,menuPrincipalView,configView,partidaView,partidasGuardadasView, rankingView, guardarPartidaView, partidaRepetidaView);

        // Controladores
        CtrLogIn ctrlLogin = new CtrLogIn(loginView,gestioUser,frame);
        CtrlRegister ctrlRegister = new CtrlRegister(registerView,gestioUser,frame);
        CtrlConfig ctrlConfigView = new CtrlConfig(configView,gestioUser,frame);
        CtrlReproducirPartida ctrlReproducirPartida = new CtrlReproducirPartida(partidaRepetidaView, frame);
        CtrlPartidasGuardadas savedGamesController = new CtrlPartidasGuardadas(partidasGuardadasView, frame,gestioUser,magatzemPartida, ctrlReproducirPartida);
        CtrlRanking ctrlRanking = new CtrlRanking(rankingView, frame,savedGamesController,magatzemPartida);
        CtrlGuardarPartida ctrlGuardarPartida = new CtrlGuardarPartida(guardarPartidaView, frame, magatzemPartida);
        CtrlPartida ctrlPartida = new CtrlPartida(partidaView,frame,gestioUser, ctrlGuardarPartida);
        CtrlMenuPrincipal ctrlMenuPrincipal = new CtrlMenuPrincipal(menuPrincipalView,frame,ctrlGuardarPartida,ctrlPartida,savedGamesController,ctrlRanking,gestioUser, magatzemPartida);



        // Registrar controladores a las vistas
        registerView.registerController(ctrlRegister);
        loginView.registerController(ctrlLogin);
        menuPrincipalView.registerController(ctrlMenuPrincipal);
        configView.registerController(ctrlConfigView);
        partidaView.registerController(ctrlPartida);
        partidasGuardadasView.registerController(savedGamesController);
        rankingView.registerController(ctrlRanking);
        guardarPartidaView.registerController(ctrlGuardarPartida);

        frame.start();
    }
}
