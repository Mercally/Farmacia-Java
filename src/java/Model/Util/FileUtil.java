/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Josué Mercally
 */
public class FileUtil {

    public static String guardarArchivo(byte[] BytesArray, String FileName){
        String UbicacionImagen = null;
        
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("") + File.separatorChar
                + "resources" + File.separatorChar +
                "imgs" + File.separatorChar + "products" + File.separatorChar + FileName;
        File f = null;
        InputStream in = null;
        try{
        f = new File(path);

        in = new ByteArrayInputStream(BytesArray);
        FileOutputStream out = new FileOutputStream(f.getAbsolutePath());
        int c = 0;
        
        while((c = in.read()) >= 0){
            out.write(c);
        }
        out.flush();
        out.close();
        UbicacionImagen = "../../resources/imgs/products/" + FileName;
        }catch(Exception ex){
            System.out.println("No se pudo cargar la imagen" + ex.getMessage());
        }
        return UbicacionImagen;
    }    
}
