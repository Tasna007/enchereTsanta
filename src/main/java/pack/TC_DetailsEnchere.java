package pack;

// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import model.T_DetailsEnchere;
import model.TV_Compte;
import service.TS_Compte;
import service.TS_DetailsEnchere;
import util.Connexion;


// import org.json.simple.*;

@CrossOrigin
@RestController
public class TC_DetailsEnchere {
    @PostMapping("/insertDetailsEnchere")
    public static boolean insert(
        @RequestParam(value="idEnchere") int idEnchere,
        @RequestParam(value="idUtilisateur") int idUtilisateur,
        @RequestParam(value="mise") double mise){
        boolean retour = false;
        try {
            TV_Compte compte = TS_Compte.getVCompte(idUtilisateur);
            if (mise <= compte.getArgent()) {
                TS_DetailsEnchere.insert(new T_DetailsEnchere(idEnchere,idUtilisateur,mise));
                TV_Compte c = TV_Compte.cast(new TV_Compte(idUtilisateur).select(new Connexion().getConnexion(), "V_Compte"))[0];
                TS_Compte.updateCompteMise(mise, c.getIdCompte());
                retour = true;
            }
        } catch (Exception e) {
            System.out.println("Error C_DetailsEnchere.insert(int,int,double) : " + e);
            e.printStackTrace();
        }
        return retour;
    }
}
