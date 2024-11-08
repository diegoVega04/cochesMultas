package diego.cochesmultas.DAO;

import diego.cochesmultas.Clases.Multa;
import diego.cochesmultas.Util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class MultaDAOimpl implements MultaDAO {

    @Override
    public boolean nuevaMulta(Multa a) {
        Transaction t = null;

        try (Session sesion = HibernateUtil.getSession()) {
            t = sesion.beginTransaction();
            sesion.save(a);
            t.commit();
            return true;
        }catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminarMulta(Multa a) {
        Transaction t = null;

        try (Session sesion = HibernateUtil.getSession()) {
            t = sesion.beginTransaction();
            sesion.remove(a);
            t.commit();
            return true;
        }catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizarMulta(Multa a) {
        Transaction t = null;

        try (Session sesion = HibernateUtil.getSession()) {
            t = sesion.beginTransaction();
            sesion.update(a);
            t.commit();
            return true;
        }catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ObservableList<Multa> cargarMultas(String matricula) {
        ObservableList<Multa> multas = FXCollections.observableArrayList();
        Transaction t = null;

        try (Session sesion = HibernateUtil.getSession()) {
            t = sesion.beginTransaction();
            List<Multa> m = sesion.createQuery("FROM Multa m WHERE m.coche.matricula = :matricula", Multa.class) //m es la instancia de la sesion, Multa es referencia a la entidad aqui no a la tabla
                    .setParameter("matricula", matricula)
                    .list();
            multas.addAll(m);
            t.commit();
        }catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }

        return multas;
    }
}
