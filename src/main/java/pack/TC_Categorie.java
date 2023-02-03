package pack;

// import org.json.simple.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import model.T_DetailsEnchere;
import model.TV_Compte;
import model.T_Categorie;
import service.TS_Categorie;
import service.TS_Compte;
import service.TS_DetailsEnchere;
import util.Connexion;


import org.json.simple.*;

@CrossOrigin
@RestController
public class TC_Categorie {
    @GetMapping("/Categories")
    public static ResponseEntity getAll(){
        JSONArray liste = new JSONArray();
        try {
            T_Categorie[] stock = TS_Categorie.getAll();
            for(int i=0;i<stock.length;i++){
                JSONObject obj=new JSONObject();    
                obj.put("idCategorie",stock[i].getIdCategorie());    
                obj.put("categorie",stock[i].getCategorie());    
                liste.add(obj);
            }
            return ResponseEntity.accepted().body(liste);
        } catch (Exception e) {
            System.out.println("Error C_DetailsEnchere.insert(int,int,double) : " + e);
            e.printStackTrace();
        }
        return null;
    }
}
