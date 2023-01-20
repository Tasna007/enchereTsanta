package pack;

// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import model.T_Enchere;
import service.TS_Enchere;

// import org.json.simple.*;

@CrossOrigin
@RestController
public class TC_Enchere {
    @PostMapping("/insertEnchere")
    public static boolean insert(
        @RequestParam(value="produit") String produit,
        @RequestParam(value="prix_planche") double prix_planche,
        @RequestParam(value="duree") int duree,
        @RequestParam(value="description") String description,
        @RequestParam(value="idUtilisateur") int idUtilisateur,
        @RequestParam(value="idCategorie") int idCategorie
        // @RequestParam(value="etat") int etat
        ){
        try {
            return TS_Enchere.insert(new T_Enchere(produit,prix_planche,duree,description,idUtilisateur,idCategorie));
        } catch (Exception e) {
            System.out.println("Error C_Enchere.createUser(String,String,Date,String,String) : " + e);
            e.printStackTrace();
        }
        return false;
    }
}
