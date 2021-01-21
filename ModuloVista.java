import java.util.Scanner;

public class ModuloVista{
    
    private Scanner sc = new Scanner(System.in);

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
    public void msgEstadoRadio(boolean error){
        System.out.println("\n------------------------------------------");
        if(error){ System.out.println(" ! | Tu radio ya esta encendido!");}
        else { System.out.println(" ! | Se ha encendido la radio!");}
    }
    public void msgERROREncenderRadio(){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | Enciende tu radio para usar esta funcion!");
    }
    public void msgEmisoraRadio(boolean emisora){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | Tu radio ha sido cambiado a " + (emisora ? "AM." : "FM."));
    }
    public void msgCambioEmisora(String emisora){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | La emisora ha sido cambiada a: " + emisora);
    }
    public void msgEmisoraGuardada(int posicion, String emisora){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | La emisora [" + emisora + "] se ha guardado en el boton " + posicion + ".");
    }
    public void msgERRORSinEspacio(){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | Ya no queda espacio para guardar mas emisoras!");
    }
    public int ingresarBotonEmisoraGuardada(){
        System.out.println("\n------------------------------------------");
        System.out.print(" ! | Porfavor ingresa el boton de la emisora: ");
        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }
    public void cambioExitosoEmisora(String emisora, boolean am){
        System.out.println("\n------------------------------------------");
        System.out.println(" ! | Se ha cambiado exitosamente a: " + (am ? "AM" : "FM") + " [" + emisora + "] ");
    }

}
