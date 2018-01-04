package Model.Entities;
// Generated 11-29-2017 08:34:07 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Empleados generated by hbm2java
 */
public class Empleados  implements java.io.Serializable {


     private Integer empleadoId;
     private Empleados empleados;
     private String codigo;
     private String nombre;
     private String apellido;
     private Character genero;
     private String usuario;
     private String clave;
     private String ciudad;
     private Date fechaIngreso;
     private String domicilio;
     private Boolean activo;
     private Set<Ventas> ventases = new HashSet<Ventas>(0);
     private Set<Empleados> empleadoses = new HashSet<Empleados>(0);

    public Empleados() {
    }

    public Empleados(Empleados empleados, String codigo, String nombre, String apellido, Character genero, String usuario, String clave, String ciudad, Date fechaIngreso, String domicilio, Boolean activo, Set<Ventas> ventases, Set<Empleados> empleadoses) {
       this.empleados = empleados;
       this.codigo = codigo;
       this.nombre = nombre;
       this.apellido = apellido;
       this.genero = genero;
       this.usuario = usuario;
       this.clave = clave;
       this.ciudad = ciudad;
       this.fechaIngreso = fechaIngreso;
       this.domicilio = domicilio;
       this.activo = activo;
       this.ventases = ventases;
       this.empleadoses = empleadoses;
    }
   
    public Integer getEmpleadoId() {
        return this.empleadoId;
    }
    
    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }
    public Empleados getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Character getGenero() {
        return this.genero;
    }
    
    public void setGenero(Character genero) {
        this.genero = genero;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public Date getFechaIngreso() {
        return this.fechaIngreso;
    }
    
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
    public String getDomicilio() {
        return this.domicilio;
    }
    
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    public Boolean getActivo() {
        return this.activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public Set<Ventas> getVentases() {
        return this.ventases;
    }
    
    public void setVentases(Set<Ventas> ventases) {
        this.ventases = ventases;
    }
    public Set<Empleados> getEmpleadoses() {
        return this.empleadoses;
    }
    
    public void setEmpleadoses(Set<Empleados> empleadoses) {
        this.empleadoses = empleadoses;
    }




}


