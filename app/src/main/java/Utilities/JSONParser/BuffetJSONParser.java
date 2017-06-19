package Utilities.JSONParser;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Model.Usuario;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.CategoriaProducto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Utilities.InputValidator.InputValidator;

/**
 * Created by Hernan on 09/06/2017.
 */

public class BuffetJSONParser {

    public List<Producto> parsearProductos(String json) throws JSONException{

        List<Producto> productos = new ArrayList<>();
        JSONArray array = new JSONArray(json);
        for(int i = 0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            Producto p = new Producto();
            p.setId(obj.getString("_id"));
            String cat = obj.getString("tipoMenu");
            if(cat.equals("Principal")){
                p.setCategoría(CategoriaProducto.Menu);
            } else {
                try {
                    p.setCategoría(InputValidator.getEnumFromString(CategoriaProducto.class, cat));
                } catch(IllegalArgumentException e){
                    continue;
                }
            }
            p.setNombre(obj.getString("nombre"));
            p.setPrecio(obj.getDouble("precio"));
            p.setUrlImagen(obj.getString("imagen"));
            productos.add(p);
        }
        return productos;

    }

    public List<Usuario> parsearUsuarios(String json) throws JSONException {

        List<Usuario> usuarios = new ArrayList<>();
        JSONArray array = new JSONArray(json);
        for(int i = 0; i < array.length(); i++){
            JSONObject obj = array.getJSONObject(i);
            Usuario u = new Usuario();
            u.setId(obj.getString("_id"));
            u.setNombre(obj.getString("nombre"));
            u.setApellido(obj.getString("apellido"));
            u.setDNI(obj.getString("dni"));
            u.setEmail(obj.getString("mail"));
            u.setPassword(obj.getString("clave"));
            usuarios.add(u);
        }
        return usuarios;

    }

    public Integer obtenerCodigoRetorno(String json) throws JSONException{
        JSONObject obj = new JSONObject(json);
        return obj.getInt("codigo");
    }

}
