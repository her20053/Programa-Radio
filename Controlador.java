import java.util.HashMap;
import java.util.Map;

public class Controlador{

    public static void main(String[] args) {
        
        ModuloVista mv = new ModuloVista();
        ModuloRadio mr = new ModuloRadio();
        while(true){
            switch (mv.seleccionarOpcionMI(mr.getestadoRadio(), mr.getemisoraRadio(), (mr.getemisoraRadio() ? mr.emisorasAM[mr.getEmisoraActual(true)] : mr.emisorasFM[mr.getEmisoraActual(false)]))) {
                case 1:
                    if(mr.getestadoRadio()){ mv.msgEstadoRadio(true); }
                    else{ mv.msgEstadoRadio(false); mr.setestadoRadio(true);;}
                    break;
                case 2:
                    if(!mr.getestadoRadio()){ mv.msgERROREncenderRadio(); }
                    else{ mr.setemisoraRadio(); mv.msgEmisoraRadio(mr.getemisoraRadio()); }
                    break;
                case 3:
                    if(!mr.getestadoRadio()){ mv.msgERROREncenderRadio(); }
                    else{
                        mr.aumentarEmisoraActual(mr.getemisoraRadio());
                        mv.msgCambioEmisora(mr.getemisoraRadio() ? mr.emisorasAM[mr.getEmisoraActual(true)] : mr.emisorasFM[mr.getEmisoraActual(false)]);
                    }
                    break;
                case 4:
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
                case 6:
                    mr.setestadoRadio(false);
                    System.out.println(" ! | Radio apagado.");
                    break;    
            }
        }
    }
}

interface RadioInterface {

    public int continuar();
    public int retroceder();
    public void aumentarEmisoraActual(boolean AM);

}


class ModuloRadio implements RadioInterface{

    private boolean estadoRadio = false;
    private boolean emisoraRadio = true; // True - AM ; False - FM

    private Integer emisoraAMActual = 0;
    private Integer emisoraFMActual = 0;

    public Map< Integer , String > emisorasGuardadas = new HashMap<>();

    public String[] emisorasAM = new String[]{"94.3","94.9","95.7","95.8","96.7","96.9","97.4","97.7","98.5","98.9"};
    public String[] emisorasFM = new String[]{"80.5","80.6","82.2","82.7","83.6","83.7","85.1","85.6","89.3","89.5"};

    boolean getestadoRadio(){
        return estadoRadio;
    }
    void setestadoRadio(boolean x){
        estadoRadio = x;
    }
    boolean getemisoraRadio(){
        return emisoraRadio;
    }
    void setemisoraRadio(){
        emisoraRadio = !emisoraRadio;
    }
    void setEmisoraRadioCambio(boolean x){
        this.emisoraRadio = x;
    }
    Integer getEmisoraActual(boolean AM){
        return AM ? this.emisoraAMActual : this.emisoraFMActual; 
    }
    void establecerPuntoAM(int pos){
        this.emisoraAMActual = pos;
    }
    void establecerPuntoFM(int pos){
        this.emisoraFMActual = pos;
    }
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
