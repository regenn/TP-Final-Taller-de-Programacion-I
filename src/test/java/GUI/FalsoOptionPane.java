package GUI;
import vista.IOptionPane;

public class FalsoOptionPane implements IOptionPane {
    private String mensaje = null;

    public FalsoOptionPane() {
        super();
    }
    
    public String getMensaje() {
        return mensaje;
    }

	@Override
	public void ShowMessage(String mensaje) {
        this.mensaje = mensaje;
	}
}