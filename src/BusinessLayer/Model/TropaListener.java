package BusinessLayer.Model;

import BusinessLayer.Entities.Fitxa;

import java.awt.*;

/**
 * Interficie que conecta las diferentes fichas y sus estados con el modelo prinicpal y sus diferentes canvios de modalidad
 *
 */
public interface TropaListener {

    /**
     * Método que que notifica el modelo el movimiento que ha realizado una ficha
     * @param old_casella Casilla anigua
     * @param new_casella Casilla nueva
     */
    void moveFitxa(Point old_casella, Point new_casella);

    /**
     * Método que notifica al modelo sobre la muerte de una ficha
     * @param fitxa La ficha que ha muerto(this.vida<=0)
     */
    void enemicMort(Fitxa fitxa);

    /**
     * Método que notifica al modelo cuando una ficha está atacando a una base
     * @param damage El daño inflingido a la base
     * @param propietariFitxa Miramos si la base está siendo atacada por el usuario o la maquina
     */
    void baseAtacada(int damage, boolean propietariFitxa);

    /**
     * Informamos al modelo que una ficha está atacando a otra
     * @param fitxa
     */
    void rivalAtacat(Fitxa fitxa);

}
