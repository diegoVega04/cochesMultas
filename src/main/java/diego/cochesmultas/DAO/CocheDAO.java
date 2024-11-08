package diego.cochesmultas.DAO;

import diego.cochesmultas.Clases.Coche;
import javafx.collections.ObservableList;


public interface CocheDAO {

    ObservableList<Coche> cargarCoches();
    boolean guardarCoche (Coche a);
    boolean eliminarCoche (Coche a);
    boolean actualizarCoche (Coche a);
}
