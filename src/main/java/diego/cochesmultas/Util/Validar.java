package diego.cochesmultas.Util;

import diego.cochesmultas.Clases.Coche;

public class Validar {
    public static boolean matricula (Coche coche) {
        return coche.getMatricula().matches("^[0-9]{4}[A-Z]{3}$");
    }

    public static boolean marcaModelo (String marca, String modelo) {
        if (!marca.isEmpty() && !modelo.isEmpty()) {
            return marca.matches("^[a-zA-Z]+$") && modelo.matches("^[a-zA-Z0-9]+$");
        }
        return false;
    }

    public static boolean precio (String precio) {
        try {
            Double.parseDouble(precio);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
