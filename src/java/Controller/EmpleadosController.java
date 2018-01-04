package Controller;

import Model.Dao.EmpleadosDao;
import Model.Entities.Empleados;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;

//@Named(value = "Empleados")
@ManagedBean
@ViewScoped
public class EmpleadosController implements Serializable {

    public EmpleadosController(){        
    }
    
    private List<Empleados> listaEmpleados;
    private Empleados empleado;
    
    public int CuentaEmpleados(int esActivo){
        EmpleadosDao Dao = new EmpleadosDao();
        return Dao.CuentaEmpleados(esActivo);
    }
    
    public int CuentaEmpleados(){
        EmpleadosDao Dao = new EmpleadosDao();
        return Dao.CuentaEmpleados();
    }
    
    public void setListaEmpleados(List<Empleados> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }   
    
    public List<Empleados> getListaEmpleados(){
        EmpleadosDao Dao = new EmpleadosDao();
        this.listaEmpleados = Dao.ListaEmpleados();
        return this.listaEmpleados;
    } 

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }
    
    public void limpiarEmpleado() {
        empleado = new Empleados();
        empleado.setFechaIngreso(new Date());
    }

    public void agregarEmpleado() {
        EmpleadosDao ed = new EmpleadosDao();
        ed.agregar(empleado);
    }

    public void modificarEmpleado() {
        EmpleadosDao ud = new EmpleadosDao();
        ud.modificar(empleado);
        limpiarEmpleado();
    }

    public void eliminarEmpleado() {
        EmpleadosDao ud = new EmpleadosDao();
        ud.eliminar(empleado);
        limpiarEmpleado();
    }
    
}
