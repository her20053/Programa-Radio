public class Controlador implements Radio{

    private static boolean estadoRadio = false;

    public static void main(String[] args) {
        
        ModuloVista mv = new ModuloVista();
        switch (mv.seleccionarOpcionMI(estadoRadio)) {
            case 1:
                if(estadoRadio){ mv.msgEstadoRadio(true); }
                else{ mv.msgEstadoRadio(false); estadoRadio = true;}
                break;
        }

    }

    @override 
    public void comer(){

    }


}