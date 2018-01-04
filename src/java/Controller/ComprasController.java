/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Dao.ClientesDao;
import Model.Dao.EmpleadosDao;
import Model.Dao.VentasDao;
import Model.Entities.Detalleventas;
import Model.Entities.Empleados;
import Model.Entities.Productos;
import Model.Entities.Ventas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Josué Mercally
 */
//@Named(value = "ventasController")
@ManagedBean
@ViewScoped
public class ComprasController {   

    public ComprasController() {
        iniciarVenta();
    }
    
    private Set<Detalleventas> listaDetalleVentas;
    private Detalleventas detalleVentas;
    private Ventas venta;
    private Productos producto;
    private int pCantidad;
    private float tVenta;
    private String nVenta;
  
    public float gettVenta() {
        return tVenta;
    }

    public void settVenta(float tVenta) {
        this.tVenta = tVenta;
    }

    public String getnVenta() {
        return nVenta;
    }

    public void setnVenta(String nVenta) {
        this.nVenta = nVenta;
    }
    
    public int getpCantidad() {
        return pCantidad;
    }

    public void setpCantidad(int pCantidad) {
        this.pCantidad = pCantidad;
    }
    
    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Ventas getVenta() {
        return venta;
    }

    public void setVenta(Ventas venta) {
        this.venta = venta;
    }
    
    public Set<Detalleventas> getListaDetalleVentas() {
        return listaDetalleVentas;
    }

    public void setListaDetalleVentas(Set<Detalleventas>listaDetalleVentas) {
        this.listaDetalleVentas = listaDetalleVentas;
    }
    
    public Detalleventas getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(Detalleventas detalleVentas) {
        this.detalleVentas = detalleVentas;
    }
    
    private String getCorrelativoVenta(Ventas uVenta){
        String NuevoCodigo = "V";
        int Correlativo = 0;
        try{
            Correlativo = Integer.parseInt(uVenta.getCodigo().substring(1));
            Correlativo++;
            int cantCeros = (3 - String.valueOf(Correlativo).length());
            for (int i = 0; i < cantCeros; i++) {
                NuevoCodigo += "0";
            }
        }catch(Exception ex){
            
        }
        return NuevoCodigo+String.valueOf(Correlativo);
    }
    
    public void iniciarVenta() {
        if(this.venta == null){
            this.detalleVentas = new Detalleventas();
            this.detalleVentas.setCantidad(0);
            this.listaDetalleVentas = new HashSet<Detalleventas>(0);
            this.pCantidad = 0;
            this.tVenta = 0;
            this.nVenta = "PROVISIONAL";
        }
    }
    
    public void agregarVenta() {
        
        try{
            VentasDao ventasDao = new VentasDao();        
            this.venta = new Ventas(); // Crando nueva venta
            this.venta.setCodigo(getCorrelativoVenta(new VentasDao().obtenerUltimaVenta())); // Codigo de factura
            this.venta.setDetalleventases(this.listaDetalleVentas); // Lista detalle de la venta
            this.venta.setFechaVenta(new Date()); // Fecha de la venta
            this.venta.setEmpleados(new EmpleadosDao().ObtenerEmpleado(LoginController.usuarioActual)); // Obtener el empleado según el Id de la sesión
            this.venta.setClientes(new ClientesDao().obtenerCliente(12)); // Obtener el cliente según el Id de la sesión
            this.venta.setTotalVenta(this.tVenta);
            int VentaId = ventasDao.agregar(this.venta);
            this.pCantidad = 0;
            
            //HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            //rq.setAttribute("VentaId", VentaId);
            
            String Url = "/Farmacia/faces/views/admin/factura.xhtml?ventaId="+VentaId;
            FacesContext.getCurrentInstance().getExternalContext().redirect(Url);
        }catch(Exception ex){
            
        }
    }
    
    public void agregarDetalles(){
        try{
            Detalleventas detalleVentaAdd = new Detalleventas();
            detalleVentaAdd.setProductos(this.producto);
            detalleVentaAdd.setVentas(this.venta);
            detalleVentaAdd.setCantidad(this.pCantidad);
            this.listaDetalleVentas.add(detalleVentaAdd);
            Object[] lista = this.listaDetalleVentas.toArray();
            this.tVenta = 0;
            for (Object lista1 : lista) {
                this.tVenta += ((Detalleventas) lista1).getCantidad() * ((Detalleventas) lista1).getProductos().getPrecio().floatValue();
            }
            this.pCantidad = 0;
        }catch(Exception ex){
        }
    }
}
