package diego.cochesmultas.DAO;

import diego.cochesmultas.Clases.Coche;
import diego.cochesmultas.Util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CocheDAOimpl implements CocheDAO {


    public ObservableList<Coche> cargarCoches() {
        ObservableList<Coche> coches = FXCollections.observableArrayList();
        Transaction t = null;

        try (Session sesion = HibernateUtil.getSession()) {
            t = sesion.beginTransaction();
            List<Coche> ListCoches = sesion.createQuery("FROM Coche", Coche.class).list();
            coches.addAll(ListCoches);
            t.commit();

        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return coches;
    }

    @Override
    public boolean guardarCoche(Coche a) {
        Transaction t = null;
        try (Session sesion = HibernateUtil.getSession()) {
            t = sesion.beginTransaction();
            sesion.save(a);
            t.commit();

        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminarCoche(Coche a) {
        Transaction t = null;
        try (Session sesion = HibernateUtil.getSession()){
            t = sesion.beginTransaction();
            sesion.delete(a);
            t.commit();

        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean actualizarCoche(Coche a) {
        Transaction t = null;
        try (Session sesion = HibernateUtil.getSession()) {
            t = sesion.beginTransaction();
            sesion.update(a);
            t.commit();

        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
