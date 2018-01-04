package Controller;

import Model.Dao.ClientesDao;
import Model.Entities.Clientes;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
public class ClientesController implements Serializable {
    public ClientesController() {
    }
    
    private List<Clientes> listaClientes;
    private Clientes cliente;

    public int CuentaClientes(){
        ClientesDao clientesDao = new ClientesDao();
        return clientesDao.CuentaClientes();
    }
    
    public int CuentaClientes(String pGenero){
        ClientesDao clientesDao = new ClientesDao();
        return clientesDao.CuentaClientes(pGenero);
    }

    public List<Clientes> getListaClientes() {
        
        ClientesDao clientesDao = new ClientesDao();
        this.listaClientes = clientesDao.listarClientes();
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }
    
    public void limpiarCliente(){
        cliente = new Clientes();
        cliente.setFechaIngreso(new Date());
    }
    
    public void agregarCliente(){
        ClientesDao cd = new ClientesDao();
        cd.agregar(cliente);
    }
    
    public void modificarCliente(){
        ClientesDao cd = new ClientesDao();
        cd.modificar(cliente);
        limpiarCliente();
    }
    
    public void eliminarCliente(){
         ClientesDao cd = new ClientesDao();
         cd.eliminar(cliente);
         limpiarCliente();
    }
}
