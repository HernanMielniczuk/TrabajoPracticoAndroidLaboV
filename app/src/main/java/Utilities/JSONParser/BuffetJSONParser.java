package Utilities.JSONParser;

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
}
