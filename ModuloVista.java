import java.util.Scanner;

public class ModuloVista{
    // Se inicializa la clase Scanner con el nombre sc (System.in) para retraer informacion del usuario:
    private Scanner sc = new Scanner(System.in);

    /**
     * Metodo encargado de controlar el menu principal del usuario
     * @param rEncendido  Radio encendida o apagada, dependiendo de esto muestra iformacion distinta:
     * @param aMfM        Para ubicar al usuario si su frencuancia es AM o FM
     * @param emisora     Para ubicar al usuario en que emisora se encuentra actualmente
     * @return opcion ( 1 - 6 )
     */
    public int seleccionarOpcionMI(boolean rEncendido, boolean aMfM, String emisora){
        System.out.println("------------------------------------------\n");
        System.out.println("[•]| Bienvenido a tu radio personalizado! ");
        System.out.print("[•]| Tu radio esta actualmente " + ((rEncendido == true) ? "encendido." : "apagado." + "\n"));
        if(rEncendido){
            System.out.print(" [ " + ((aMfM == true) ? "AM" : "FM") + " : " + emisora + " ]\n");
        } else { System.out.print("\n");}
        System.out.println("[1]| Encender radio");
        System.out.println("[2]| Alternar AM - FM");
        System.out.println("[3]| Avanzar de emisora");
        System.out.println("[4]| Guardar emisora actual");
        System.out.println("[5]| Seleccionar emisora guardada");
        System.out.println("[6]| Apagar radio");
        System.out.print("\n[-]| Porfavor elige una opcion: ");
        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    /**
     * Notifica si el usuario ya tiene el radio encendido u apagado.
     * @param error (Reconoce si el radio es true o false siendo on/off)
     */
    public void msgEstadoRadio(boolean error){
        System.out.println("\n------------------------------------------");
        if(error){ System.out.println(" ! | Tu radio ya esta encendido!");}
        else { System.out.println(" ! | Se ha encendido la radio!");}
    }
    /**
     * Notifica al usuario que la radio debe de estar encendida
     */
    public void msgERROREncenderRadio(){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | Enciende tu radio para usar esta funcion!");
    }

    /**
     * Notifica al usuario el cambio de frecuencia de su emisora
     * @param emisora siendo true-am false-fm
     */
    public void msgEmisoraRadio(boolean emisora){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | Tu radio ha sido cambiado a " + (emisora ? "AM." : "FM."));
    }

    /**
     * Notifica al usuario la emisora actual registrada en el programa
     * @param emisora String con la emisora
     */
    public void msgCambioEmisora(String emisora){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | La emisora ha sido cambiada a: " + emisora);
    }

    /**
     * Al guardar la emisora, se le informa al usuario que emisora guardo y en que posicion del HashMap
     * @param posicion numero en el que se ubica
     * @param emisora  Nombre de la emisora (numero)
     */
    public void msgEmisoraGuardada(int posicion, String emisora){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | La emisora [" + emisora + "] se ha guardado en el boton " + posicion + ".");
    }

    /**
     * Al no tener espacio, se notifica al usuario que se ha acabado su almacenamiento:
     */
    public void msgERRORSinEspacio(){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | Ya no queda espacio para guardar mas emisoras!");
    }

    /**
     * Utilizada para ubicar que emisora necesita retraer el usuario:
     * @return opcion (posicion electa)
     */
    public int ingresarBotonEmisoraGuardada(){
        System.out.println("\n------------------------------------------");
        System.out.print(" ! | Porfavor ingresa el boton de la emisora: ");
        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    /**
     * Se notifica al usuario que la emisora cambio su frecuencia
     * @param emisora La emisora actual String
     * @param am      Siendo true-am false-fm
     */
    public void cambioExitosoEmisora(String emisora, boolean am){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | Se ha cambiado exitosamente a: " + (am ? "AM" : "FM") + " [" + emisora + "] ");
    }

}
