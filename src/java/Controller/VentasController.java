/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Dao.DetalleVentasDao;
import Model.Dao.EmpleadosDao;
import Model.Dao.VentasDao;
import Model.Entities.Detalleventas;
import Model.Entities.Empleados;
import Model.Entities.Productos;
import Model.Entities.Ventas;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Josué Mercally
 */
//@Named(value = "ventasController")
@ManagedBean
@ViewScoped
public class VentasController {   

    public VentasController() {
        
    }
    
    private List<Detalleventas> listaDetalleventas;
    private List<Ventas> listaVentas;
    private Ventas venta;
    
    public void setListaVentas(List<Ventas> listaVentas) {
        this.listaVentas = listaVentas;
    }   
    
    public List<Ventas> getListaVentas(){
        VentasDao Dao = new VentasDao();
        this.listaVentas = Dao.ListaVentas();
        return this.listaVentas;
    }
    
    public int CuentaVentas(){
        VentasDao Dao = new VentasDao();
        return Dao.CuentasVentas();
    }
    
    public BigDecimal SumaTotalVentas(){
        VentasDao Dao = new VentasDao();
        return Dao.SumaTotalVentas();
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas empleado) {
        this.venta = empleado;
    }
    
    public void limpiarVenta() {
        venta = new Ventas();
    }

    public void agregarVenta() {
        VentasDao ed = new VentasDao();
        ed.agregar(venta);
    }

    public void modificarVenta() {
        VentasDao ud = new VentasDao();
        ud.modificar(venta);
        limpiarVenta();
    }

    public void eliminarVenta() {
        VentasDao ud = new VentasDao();
        ud.eliminar(venta);
        limpiarVenta();
    }
    
    public void redirectCompra() throws IOException{
        String Url = "/Farmacia/faces/views/admin/compras.xhtml";
        FacesContext.getCurrentInstance().getExternalContext().redirect(Url);
    }
    
    public Ventas consultaVenta(int VentaId){
        //int VentaId = 0;
        Ventas _venta = null;
        try{
            //HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            //VentaId = Integer.parseInt(rq.getAttribute("VentaId").toString());
            
            if(VentaId > 0 ){
                VentasDao dao = new VentasDao();
                this.venta = dao.obtenerVenta(VentaId);
            }
        }catch(Exception ex){
            
        }
        return _venta;
    }
    
    public List<Detalleventas> getListaDetalleventas() {
        DetalleVentasDao dao = new DetalleVentasDao();
        this.listaDetalleventas = dao.ListaDetalleVentas(venta.getVentaId());
        return listaDetalleventas;
    }

    public void setListaDetalleventas(List<Detalleventas> listaDetalleventas) {
        this.listaDetalleventas = listaDetalleventas;
    }
}
