package Model.Dao;

import Model.Entities.Productos;
import Model.Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Miguel
 */
public class ProductosDao {
    
    public Productos ObtenerProductos(int ProductoId){
        Productos oProducto = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
        String hql = "FROM Productos WHERE productoId = "+ProductoId;
        try{
            oProducto = (Productos) session.createQuery(hql).list().get(0);
            tran.commit();
            session.close();
        }catch(Exception ex){
            tran.rollback();
        }
        
        return oProducto;
    }
    
    public List<Productos> ListaProductos(){
        List<Productos> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
        String hql = "FROM Productos";
        try{
            lista = session.createQuery(hql).list();
            tran.commit();
            session.close();
        }catch(Exception ex){
            tran.rollback();
        }
        
        return lista;
    }
    
    public List<Productos> ListaProductosExistentes(){
        List<Productos> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
        String hql = "FROM Productos WHERE existencias > 0";
        try{
            lista = session.createQuery(hql).list();
            tran.commit();
            session.close();
        }catch(Exception ex){
            tran.rollback();
        }
        
        return lista;
    }
    
    public int CuentaProductosExistentes(int esExistencia){
        int cuenta = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
        String hql = "FROM Productos";
        
        switch (esExistencia) {
            case 1:
                hql += " WHERE existencias > 0";
                break;
            case 0:
                hql += " WHERE existencias = 0";
                break;
            default:
                break;
        }
        try{
            cuenta = session.createQuery(hql).list().size();
            tran.commit();
            session.close();
        }catch(Exception ex){
            tran.rollback();
        }
        return cuenta;
    }
    
    public void agregar(Productos producto) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(producto);
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
    
    public void modificar(Productos producto) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(producto);
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
    
    public void eliminar(Productos producto) {
        Session sesion = null;
        try {
            sesion = HibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(producto);
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
