
package ReportesUtil;

import java.util.Date;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean
@ViewScoped
public class ReportesController {

    public ReportesController() {
    }
    
        private Date desde, hasta;

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }
    
    public void verReporteEmpleados() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        ReportesDao rd = new ReportesDao();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/empleados.jasper");
       
        rd.getReporte(ruta);        
        FacesContext.getCurrentInstance().responseComplete();               
    }
    
    public void verReporteExistencias() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        ReportesDao rd = new ReportesDao();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/existensias.jasper");
       
        rd.getReporte(ruta);        
        FacesContext.getCurrentInstance().responseComplete();               
    }
    
    
    public void verReporteVentas() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        ReportesDao rd = new ReportesDao();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String ruta = servletContext.getRealPath("/reportes/ventas.jasper");
       
        rd.getReporteVentas(ruta, this.desde, this.hasta);
        FacesContext.getCurrentInstance().responseComplete();               
    }
    
}
