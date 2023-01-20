package pack;

import java.util.Date;

// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import model.T_Utilisateur;
import service.TS_Utilisateur;

// import org.json.simple.*;

@CrossOrigin
@RestController
public class TC_Utilisateur {
    @PostMapping("/utilisateurs")
    public static boolean login(@RequestParam(value="email") String email,@RequestParam(value="password") String password){
        boolean retour = false;
        try {
            retour = TS_Utilisateur.login(new T_Utilisateur(email,password));
        } catch (Exception e) {
            System.out.println("Error C_Utilisateur.login(Utilisateur) : "+e);
            e.printStackTrace();
        }
        return retour;
    }
    
    @PostMapping("/creationUtilisateur")
    public static boolean createUser(
        @RequestParam(value="nom") String nom,
        @RequestParam(value="prenom") String prenom,
        @RequestParam(value="naissance") Date naissance,
        @RequestParam(value="email") String email,
        @RequestParam(value="password") String password
        // @RequestParam(value="inscription") Date inscription
        ){
        try {
            return TS_Utilisateur.createUser(new T_Utilisateur(nom,prenom,naissance,email,password));
        } catch (Exception e) {
            System.out.println("Error C_Utilisateur.createUser(String,String,Date,String,String) : " + e);
            e.printStackTrace();
        }
        return false;
    }
}
