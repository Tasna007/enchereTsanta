package pack;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.*;
import model.Utilisateur;
import service.S_Utilisateur;
import model.*;
import service.*;

// import org.json.simple.*;

@CrossOrigin
@RestController
public class C_Utilisateur {
    
     @GetMapping("/utilisateur_encheres/{id}/{etat}")
     public ResponseEntity get_enchere(@PathVariable int id,@PathVariable int etat)
     {
        Enchere[] enchere=null;
        Categorie categorie=null;
        JSONArray liste=null;
        try {
            enchere=S_Utilisateur.get_enchere(new S_Utilisateur().get(id),etat);
            liste=new JSONArray();
            for(int i=0;i<enchere.length;i++){
                categorie=new S_Categorie().get(enchere[i].getidcategorie());
                JSONObject obj=new JSONObject();    
                obj.put("idenchere",enchere[i].getidenchere());    
                obj.put("produit",enchere[i].getproduit());    
                obj.put("prix_planche",enchere[i].getprix_planche());    
                obj.put("duree",enchere[i].getduree());    
                obj.put("ajout",enchere[i].getajout());    
                obj.put("description",enchere[i].getdescription());    
                obj.put("etat",enchere[i].getetat());
                obj.put("categorie",categorie.getcategorie());
                liste.add(obj);
            }
            return ResponseEntity.accepted().body(liste);
            
        } catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage();
        }
        return null;
     }

     @GetMapping("/utilisateur_encheres_gagner/{id}")
     public ResponseEntity get_enchere_gagner(@PathVariable int id)
     {
        Gagnant[] gagnant=null;
        Enchere enchere=null;
        Categorie categorie=null;
        Detailsenchere details=null;
        JSONArray liste=null;
        try {
            gagnant=S_Utilisateur.get_enchere_gagner(new S_Utilisateur().get(id));
            liste=new JSONArray();
            for(int i=0;i<gagnant.length;i++){
                enchere=new S_Enchere().get(gagnant[i].getidenchere());
                categorie=new S_Categorie().get(enchere.getidcategorie());
                details=new S_Detailsenchere().get(gagnant[i].getiddetailsenchere());
                JSONObject obj=new JSONObject();    
                obj.put("idenchere",enchere.getidenchere());    
                obj.put("idgagnant",gagnant[i].getidgagnant());    
                obj.put("prix",gagnant[i].getprix());    
                obj.put("produit",enchere.getproduit());    
                obj.put("prix_planche",enchere.getprix_planche());    
                obj.put("duree",enchere.getduree());    
                obj.put("ajout",enchere.getajout());    
                obj.put("description",enchere.getdescription());    
                obj.put("etat",enchere.getetat());
                obj.put("categorie",categorie.getcategorie());
                obj.put("mise",details.getmise());
                obj.put("datemise",details.getdatemise());
                liste.add(obj);
            }
            return ResponseEntity.accepted().body(liste);
            
        } catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage();
        }
        return null;
     }

     @GetMapping("/utilisateur_historique/{id}")
     public ResponseEntity get_historique(@PathVariable int id)
     {
        Detailsenchere[] details=null;
        Enchere enchere=null;
        Categorie categorie=null;
        JSONArray liste=null;
        try {
            details=S_Utilisateur.get_historique(new S_Utilisateur().get(id));
            liste=new JSONArray();
            for(int i=0;i<details.length;i++){
                enchere=new S_Enchere().get(details[i].getidenchere());
                categorie=new S_Categorie().get(enchere.getidcategorie());
                JSONObject obj=new JSONObject();    
                obj.put("idenchere",enchere.getidenchere());       
                obj.put("produit",enchere.getproduit());    
                obj.put("prix_planche",enchere.getprix_planche());    
                obj.put("duree",enchere.getduree());    
                obj.put("ajout",enchere.getajout());    
                obj.put("description",enchere.getdescription());    
                obj.put("etat",enchere.getetat());
                obj.put("categorie",categorie.getcategorie());
                obj.put("mise",details[i].getmise());
                obj.put("datemise",details[i].getdatemise());
                liste.add(obj);
            }
            return ResponseEntity.accepted().body(liste);
            
        } catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage();
        }
        return null;
     }
}
