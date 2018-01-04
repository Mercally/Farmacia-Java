
package Model.Dao;

import Model.Entities.Clientes;
import Model.Entities.Empleados;
import Model.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Miguel
 */
public class ClientesDao {
    
    
    /** Obtiene un cliente según su Id */
    public Clientes obtenerCliente(int clienteId){
        Clientes oCliente = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
        String hql = "FROM Clientes WHERE clienteId = " + clienteId;
        try{
            oCliente = (Clientes) session.createQuery(hql).list().get(0);
            tran.commit();
            session.close();
        }catch(Exception ex){
            tran.rollback();
        }
        
        return oCliente;
    }
    
    public List<Clientes> listarClientes() {
        List<Clientes> lista = null;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "FROM Clientes";
        try {
            lista = sesion.createQuery(hql).list();
            t.commit();
            sesion.close();
        } catch (Exception e) {
            t.rollback();
        }
        return lista;
    }

    public int CuentaClientes() {
        int cuenta = 0;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "FROM Clientes";
        try {
            cuenta = sesion.createQuery(hql).list().size();
            t.commit();
            sesion.close();
        } catch (Exception e) {
            t.rollback();
        }
        return cuenta;
    }
    
    public int CuentaClientes(String pGenero) {
        int cuenta = 0;
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "FROM Clientes WHERE genero = '"+pGenero+"'";
        try {
            cuenta = sesion.createQuery(hql).list().size();
            t.commit();
            sesion.close();
        } catch (Exception e) {
            t.rollback();
        }
        return cuenta;
    }
    
    public void agregar(Clientes cliente) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(cliente);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public void modificar(Clientes cliente) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(cliente);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public void eliminar(Clientes cliente) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(cliente);
            sesion.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }
}
