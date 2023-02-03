package pack;

import java.util.Date;

// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import model.T_Utilisateur;
import service.TS_Utilisateur;
import util.Utilitaire;

// import org.json.simple.*;

@CrossOrigin
@RestController
public class TC_Utilisateur {
    @PostMapping("/utilisateurs")
    public static int login(@RequestParam(value="email") String email,@RequestParam(value="password") String password){
        int retour = 0;
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
        @RequestParam(value="naissance") String naissance,
        @RequestParam(value="email") String email,
        @RequestParam(value="password") String password,
        @RequestParam(value="passwordConfirm") String passwordConfirm
        // @RequestParam(value="inscription") Date inscription
        ){
        try {
            if (password.equals(passwordConfirm)) {
                Date date = new Date(Utilitaire.castDateTimeHTML(naissance));
                return TS_Utilisateur.createUser(new T_Utilisateur(nom,prenom,date,email,password));
            }
        } catch (Exception e) {
            System.out.println("Error C_Utilisateur.createUser(String,String,Date,String,String) : " + e);
            e.printStackTrace();
        }
        return false;
    }
}
