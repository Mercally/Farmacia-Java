
package Model.Dao;

import Model.Entities.Detalleventas;
import Model.Entities.Ventas;
import Model.Util.HibernateUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Miguel
 */
public class VentasDao {
    
    
    public Ventas obtenerVenta(int VentaId){
        Ventas venta = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
        String hql = "FROM Ventas WHERE ventaId = " + VentaId;
        try{
            venta = (Ventas)session.createQuery(hql).list().get(0);
            tran.commit();
            session.close();
        }catch(Exception ex){
            tran.rollback();
        }

        return venta;
    }
    
    public Ventas obtenerUltimaVenta(){
        Ventas venta = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
        String hql = "FROM Ventas v ORDER BY v.ventaId DESC";
        try{
            venta = (Ventas)session.createQuery(hql).list().get(0);
            tran.commit();
            session.close();
        }catch(Exception ex){
            tran.rollback();
        }

        return venta;
    }
    
    public List<Ventas> ListaVentas(){
        List<Ventas> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
        String hql = "FROM Ventas";
        try{
            lista = session.createQuery(hql).list();
            tran.commit();
            session.close();
        }catch(Exception ex){
            tran.rollback();
        }

        return lista;
    }
    
    public int CuentasVentas(){
        int cuenta = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
        String hql = "FROM Ventas";
        try{
            cuenta = session.createQuery(hql).list().size();
            tran.commit();
            session.close();
        }catch(Exception ex){
            tran.rollback();
        }

        return cuenta;
    }
    
    public BigDecimal SumaTotalVentas(){
        BigDecimal ventas = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = session.beginTransaction();
        String hql = "SELECT SUM(v.totalVenta) FROM Ventas AS v";
        try{
            ventas = (BigDecimal)session.createQuery(hql).list().get(0);
            tran.commit();
            session.close();
        }catch(Exception ex){
            tran.rollback();
        }

        return ventas;
    }
    
    public int agregar(Ventas venta) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = sesion.beginTransaction();
        int vId = 0;
        try {
            vId = (int)sesion.save(venta);
            venta.setVentaId(vId);
            for(Detalleventas detalle : venta.getDetalleventases())
            {
                int ExistenciaRestante = detalle.getProductos().getExistencias() - detalle.getCantidad();
                detalle.getProductos().setExistencias(ExistenciaRestante);
                sesion.update(detalle.getProductos());
                detalle.setVentas(venta);
                sesion.save(detalle);
            }
            tran.commit();
        } catch (Exception e) {
            tran.rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return vId;
    }
    
    public void modificar(Ventas venta) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = sesion.beginTransaction();
        try {    
            sesion.update(venta);
            tran.commit();
        } catch (Exception e) {
            tran.rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }
    
    public void eliminar(Ventas venta) {
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction tran = sesion.beginTransaction();
        String hql = "FROM Detalleventas WHERE ventaId = " + venta.getVentaId();
        try {
            
            List<Detalleventas> ListaDetalle = sesion.createQuery(hql).list();
            for(Detalleventas detalle : ListaDetalle){
                sesion.delete(detalle);
            }
            
            sesion.delete(venta);
            tran.commit();
        } catch (Exception e) {
            tran.rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }
}
