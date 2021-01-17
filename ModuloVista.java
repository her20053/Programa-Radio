import java.util.Scanner;

public class ModuloVista{
    
    private Scanner sc = new Scanner(System.in);

    public int seleccionarOpcionMI(boolean rEncendido){
        System.out.println("[•]| Bienvenido a tu radio personalizado! ");
        System.out.println("[•]| Tu radio esta actualmente " + ((rEncendido == true) ? "encendido." : "apagado." + "\n"));
        System.out.println("[1]| Encender radio");
        System.out.println("[2]| Alternar AM - FM");
        System.out.println("[3]| Avanzar de emisora");
        System.out.println("[4]| Guardar emisora actual");
        System.out.println("[5]| Seleccionar emisora guardada");
        System.out.println("[6]| Apagar radio");
        System.out.print("\n[-]| Porfavor elige una opcion: ");
        return sc.nextInt();
    }
    public void msgEstadoRadio(boolean error){
        if(error){ System.out.println(" ! | Tu radio ya esta encendido!");}
        else { System.out.println(" ! | Se ha encendido la radio!");}
    }
}
