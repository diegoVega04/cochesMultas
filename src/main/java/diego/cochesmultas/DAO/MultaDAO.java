package diego.cochesmultas.DAO;

import diego.cochesmultas.Clases.Multa;
import javafx.collections.ObservableList;

public interface MultaDAO {
    boolean nuevaMulta(Multa a);
    boolean eliminarMulta(Multa a);
    boolean actualizarMulta(Multa a);
    ObservableList<Multa> cargarMultas(String matricula);
}
