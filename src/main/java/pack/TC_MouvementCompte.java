package pack;

// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import model.T_MouvementCompte;
import service.TS_MouvementCompte;


// import org.json.simple.*;

@CrossOrigin
@RestController
public class TC_MouvementCompte {
    @PostMapping("/recharge")
    public static boolean demandeRechargeCompte(
        @RequestParam(value="idUtilisateur") int idUtilisateur,
        @RequestParam(value="somme") double somme){
        try {
            return TS_MouvementCompte.demandeRechargeCompte(new T_MouvementCompte(idUtilisateur,somme));
        } catch (Exception e) {
            System.out.println("Error C_MouvementCompte.demandeRechargeCompte(int,double) : " + e);
            e.printStackTrace();
        }
        return false;
    }
}
