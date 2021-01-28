import java.util.HashMap;
import java.util.Map;

/**
 * Clase Controlador utilizada para el funcionamiento de la radio
 * Es utilizada
 * @author (Jose Hernandez, Javier Alvarez)
 */
public class Controlador{

    /**
     * Metodo main que actua como controlador del programa
     * @param args Argumento utilizado por Java
     */
    public static void main(String[] args) {
        // Se crea la clase mv utilizada como sistema de prints para el programa:
        ModuloVista mv = new ModuloVista();
        // Se crea la clase mr que actua como el objeto de radio para la ejecucion:
        ModuloRadio mr = new ModuloRadio();
        // Se entra en un loop infinito para controlar el programa:
        while(true){
            // Cada loop se hace un switch para saber que opcion desea realizar el usuario:
            switch (mv.seleccionarOpcionMI(mr.getestadoRadio(), mr.getemisoraRadio(), (mr.getemisoraRadio() ? mr.emisorasAM[mr.getEmisoraActual(true)] : mr.emisorasFM[mr.getEmisoraActual(false)]))) {
                case 1:
                    /**
                     * Case 1
                     * Encaragado de encender la radio
                     * en caso de ya estar encendido enviara notificacion de su estado
                     */
                    if(mr.getestadoRadio()){ mv.msgEstadoRadio(true); }
                    else{ mv.msgEstadoRadio(false); mr.setestadoRadio(true);;}
                    break;
                case 2:
                    /**
                     * Case 2
                     * Se revisa que la radio este encendida, si lo esta,
                     * este enviara a la clase radio la informacion de que se
                     * desea alterar entre AM o FM sin requerir parametro
                     */
                    if(!mr.getestadoRadio()){ mv.msgERROREncenderRadio(); }
                    else{ mr.setemisoraRadio(); mv.msgEmisoraRadio(mr.getemisoraRadio()); }
                    break;
                case 3:
                    /**
                     * Case 3
                     * Se revisa que la radio este encendida, si lo esta,
                     * este tomara la frecuencia en la que se encuentra el
                     * radio actualmente y agregara una posicion a su emisora
                     */
                    if(!mr.getestadoRadio()){ mv.msgERROREncenderRadio(); }
                    else{
                        mr.aumentarEmisoraActual(mr.getemisoraRadio());
                        mv.msgCambioEmisora(mr.getemisoraRadio() ? mr.emisorasAM[mr.getEmisoraActual(true)] : mr.emisorasFM[mr.getEmisoraActual(false)]);
                    }
                    break;
                case 4:
                    /**
                     * Case 4
                     * Se revisa que la radio este encendida, si lo esta,
                     * revisara que aun hayan espacios disponibles (< 12)
                     * verificado esto tomara la posicion que corresponde y
                     * la emisora actual y seran guardadas en el HashMap de la
                     * clase Radio "emisorasGuardadas"
                     */
                    if(!mr.getestadoRadio()){ mv.msgERROREncenderRadio(); }
                    else{
                        if(mr.emisorasGuardadas.size() == 12){
                            mv.msgERRORSinEspacio();
                        } else{
                            mr.emisorasGuardadas.put((mr.emisorasGuardadas.size() + 1), mr.getemisoraRadio() ? mr.emisorasAM[mr.getEmisoraActual(true)] : mr.emisorasFM[mr.getEmisoraActual(false)]);
                            mv.msgEmisoraGuardada(mr.emisorasGuardadas.size(), mr.emisorasGuardadas.get(mr.emisorasGuardadas.size()));
                        }
                    }
                    break;
                case 5:
                    /**
                     * Case 5
                     * Este requiere que el usuario ingrese el boton que desea retraer del HashMap
                     * una vez retraida la info. del boton siendo su posicion y emisora el programa
                     * reconoce si es una frecuancia AM o FM y se encarga de alternar la frecuencia
                     * en caso de no ser la misma y colocar la posicion de la emisora en la correspondiente.
                     */
                    try {
                        int botonElecto = mv.ingresarBotonEmisoraGuardada();
                        String emisoraTEMP = mr.emisorasGuardadas.get(botonElecto);
                        boolean esEmisoraAM = (emisoraTEMP.charAt(0) == '9') ? true : false;
                        mr.setEmisoraRadioCambio(esEmisoraAM);
                        if(esEmisoraAM){
                            for(int i = 0; i < mr.emisorasAM.length; i++){
                                if(emisoraTEMP.equals(mr.emisorasAM[i])){
                                    mr.establecerPuntoAM(i);
                                    mv.cambioExitosoEmisora(emisoraTEMP, true);
                                }
                            }
                        }
                        else{
                            for(int i = 0; i < mr.emisorasFM.length; i++){
                                if(emisoraTEMP.equals(mr.emisorasFM[i])){
                                    mr.establecerPuntoFM(i);
                                    mv.cambioExitosoEmisora(emisoraTEMP, false);
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(" ! | Hubo un error al retraer tu emisora, porfavor intenta de nuevo..");
                    }
                    break;
                /**
                 * Caso 6
                 * Apaga la radio
                 */
                case 6:
                    mr.setestadoRadio(false);
                    System.out.println(" ! | Radio apagado.");
                    break;    
            }
        }
    }
}

/**
 * Interfaz con funcionalidades generales de una radio
 * Es utilizada
 * @author (Jose Hernandez, Javier Alvarez)
 */
interface RadioInterface {

    public int continuar();
    public int retroceder();
    public void aumentarEmisoraActual(boolean AM);

}
/**
 * Esta clase implementa la interfaz del funcionamiento de una radio
 * Es utilizada
 * @author (Jose Hernandez, Javier Alvarez)
 */
class ModuloRadio implements RadioInterface{

    // Notifica si la radio esta encendida o apagada:
    private boolean estadoRadio = false;
    // Atributo encargado de controlar la frecuencia de la emisora:
    private boolean emisoraRadio = true; // True - AM ; False - FM
    // Posiciones en las que se encuentran las emisoras
    // Estas van avanzando, al llegar al limite (11) se regresan a 0:
    private Integer emisoraAMActual = 0;
    private Integer emisoraFMActual = 0;
    // Registro d emisorar guardadas, toman el numero en el que se guardan y la emisora como String:
    public Map< Integer , String > emisorasGuardadas = new HashMap<>();
    // Listado de emisoras AM - FM:
    public String[] emisorasAM = new String[]{"94.3","94.9","95.7","95.8","96.7","96.9","97.4","97.7","98.5","98.9"};
    public String[] emisorasFM = new String[]{"80.5","80.6","82.2","82.7","83.6","83.7","85.1","85.6","89.3","89.5"};

    /**
     * Getter para si el radio esta apagado o encendido
     * @return boolean
     */
    boolean getestadoRadio(){
        return estadoRadio;
    }
    /**
     * Establece si el radio se enciende o apaga
     * @param x
     * @return void
     */
    void setestadoRadio(boolean x){
        estadoRadio = x;
    }

    /**
     * Getter para saber si la emisora esta AM o FM
     * @return boolean
     */
    boolean getemisoraRadio(){
        return emisoraRadio;
    }

    /**
     * Establece true-false la emisora: AM o FM
     */
    void setemisoraRadio(){
        emisoraRadio = !emisoraRadio;
    }
    /**
     * Establece la emisora segun se le pase parametro
     * @param x
     */
    void setEmisoraRadioCambio(boolean x){
        this.emisoraRadio = x;
    }

    /**
     * Utilizado para agarrar la emisora que esta siendo escuchada actualmente
     * @param AM (Sirve para reconocer si se quiere retraer la emisora AM o FM)
     * @return
     */
    Integer getEmisoraActual(boolean AM){
        return AM ? this.emisoraAMActual : this.emisoraFMActual; 
    }
    /**
     * Una vez guardada una emisora, para elegirla se retrae la posicion del HashMap
     * y se le asigna ese numero a la radio actual
     * @param pos
     */
    void establecerPuntoAM(int pos){
        this.emisoraAMActual = pos;
    }
    /**
     * Una vez guardada una emisora, para elegirla se retrae la posicion del HashMap
     * y se le asigna ese numero a la radio actual
     * @param pos
     */
    void establecerPuntoFM(int pos){
        this.emisoraFMActual = pos;
    }

    /**
     * Aumenta la emisora que se escucha por una posicion, al llegar a la ultima posicion,
     * este se volvera 0 de nuevo haciendo un loop entre la Array
     * @param AM (Utilizado para ubicar que posicion de AM o FM se desea aumentar)
     */
    public void aumentarEmisoraActual(boolean AM){

        if(AM){
            if(emisoraAMActual == 9){
                emisoraAMActual = 0;
            } else { emisoraAMActual++; }
        }
        else{
            if(emisoraFMActual == 9){
                emisoraFMActual = 0;
            } else { emisoraFMActual++; }
        }

    }
    // Metodo muerto utilizado por la interface
    // Este metodo fue re escrito y se ubica en @aumentarEmisoraActual
    @Override
    public int continuar() {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int retroceder() {
        // TODO Auto-generated method stub
        return 0;
    }

}
