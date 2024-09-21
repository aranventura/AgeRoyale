package BusinessLayer.Entities;

import PersistanceLayoud.UsuariDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Partida {
    private String nomPartida;
    private String nomJugador;
    private String data;
    private int guanyador;

    /**
     * Constructor per Partida per quan es crea una partida nova
     * @param nomPartida nom de la partida
     * @param nomJugador nom del jugador
     * @param guanyador indica si el jugador que guarda la partida ha guanyat o no
     */
    public Partida(String nomPartida, String nomJugador, boolean guanyador) {
        this.nomPartida = nomPartida;
        this.nomJugador = nomJugador;
        this.guanyador = boolToInt(guanyador);

        Calendar rightNow = Calendar.getInstance();
        String dia = Integer.toString(rightNow.get(Calendar.DATE));
        String mes = Integer.toString(rightNow.get(Calendar.MONTH) + 1);
        String any = Integer.toString(rightNow.get(Calendar.YEAR));
        data = any+"-"+mes+"-"+dia;
    }

    /**
     * Constructor per Partida per quan es llegeix una partida de la base de dades
     * @param nomPartida nom de la partida
     * @param nomJugador nom del jugador
     * @param guanyador indica si el jugador que guarda la partida ha guanyat o no
     * @param data data de quan es va jugar la partida
     */
    public Partida(String nomPartida, String nomJugador, boolean guanyador, String data) {
        this.nomPartida = nomPartida;
        this.nomJugador = nomJugador;
        this.guanyador = boolToInt(guanyador);
        this.data = data;
    }

    /**
     * Getter del nom de la Partida
     *
     * @return Un string que representa el nom de la Partida
     */
    public String getNomPartida() {
        return nomPartida;
    }

    /**
     * Setter del nom de la partida
     * @param nomPartida String que es guardarà com a nou nom de la partida
     */
    public void setNomPartida(String nomPartida) {
        this.nomPartida = nomPartida;
    }

    /**
     * Getter del nom del Jugador
     *
     * @return Un string que representa el nom del jugador de la partida
     */
    public String getNomJugador() {
        return nomJugador;
    }

    /**
     * Setter del nom del Jugador
     * @param nomJugador String que es guardarà com a nou nom del Jugador
     */
    public void setNomJugador(String nomJugador) {
        this.nomJugador = nomJugador;
    }

    /**
     * Getter del nom de la data en que va ser jugada la partida
     *
     * @return Un string que representa el any, mes i dia en que es va jugar la partida
     */
    public String getData() {
        return data;
    }

    /**
     * Setter de la data de la partida
     * @param data String que es guardarà com a nova data de la partida
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Getter de si el jugador es el guanyador retornat com a boolea
     *
     * @return Un boolea que representa si el jugador ha guanyat o no
     */
    public boolean isGuanyador() {
        return intToBool(guanyador);
    }

    /**
     * Getter de si el jugador es el guanyador retornat com a int
     *
     * @return Un eint que representa si el jugador ha guanyat o no
     */
    public int getIntGuanyador() {
        return guanyador;
    }

    /**
     * Setter de si el jugador es el guanyador
     * @param guanyador boolea que indica si el jugador ha guanyat la partida
     */
    public void setGuanyador(boolean guanyador) {
        this.guanyador = boolToInt(guanyador);
    }
    /**
     * Mètode privat que passa un boolea a enter
     * @param bool el boolea que es vol passar a in
     * @return L'enter que representa el boolea entrat
     */
    private int boolToInt(boolean bool){
        int integer = 0;
        if(bool) {
            integer = 1;
        }
        return integer;
    }

    /**
     * Mètode privat que passa un enter a boolea
     * @param integer el enter que es vol passar a boolea
     * @return El boolea que representa l'enter entrat
     */
    private boolean intToBool(int integer) {
        boolean bool = false;
        if(integer == 1) {
            bool = true;
        }
        return bool;
    }

    /**
     * Getter de la fecha en formato Date
     * @return fecha en Date
     */
    public Date getDate(){
        Date date = new Date();
        try {
            System.out.println("Fecha: " + getData());
            return new SimpleDateFormat("yyyy-MM-dd").parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
