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

     // @GetMapping("/utilisateur_encheres_actu/{id}")
     // public ResponseEntity get_enchere_actu(@PathVariable int id)
     // {
     //    Enchere[] enchere=null;
     //    Categorie categorie=null;
     //    Gagnant gagnant=null;
     //    Utilisateur utilisateur=null;
     //    JSONArray liste=null;
     //    try {
     //        enchere=S_Utilisateur.get_enchere(new S_Utilisateur().get(id),0);
     //        liste=new JSONArray();
     //        for(int i=0;i<enchere.length;i++){
     //            categorie=new S_Categorie().get(enchere[i].getidcategorie());
     //            gagnant=S_Enchere.get_gagnant(enchere[i]);
     //            utilisateur=S_Enchere.get_utilisateur(enchere[i]);
     //            JSONObject obj=new JSONObject();    
     //            obj.put("idutilisateur",utilisateur.getidutilisateur());    
     //            obj.put("nom",utilisateur.getnom());    
     //            obj.put("prenom",utilisateur.getprenom());    
     //            obj.put("email",utilisateur.getemail());    
     //            obj.put("idgagnant",gagnant.getidgagnant());    
     //            obj.put("prix",gagnant.getprix());
     //            obj.put("idenchere",enchere[i].getidenchere());    
     //            obj.put("produit",enchere[i].getproduit());    
     //            obj.put("prix_planche",enchere[i].getprix_planche());    
     //            obj.put("duree",enchere[i].getduree());    
     //            obj.put("ajout",enchere[i].getajout());    
     //            obj.put("description",enchere[i].getdescription());    
     //            obj.put("etat",enchere[i].getetat());
     //            obj.put("categorie",categorie.getcategorie());
     //            liste.add(obj);
     //        }
     //        return ResponseEntity.accepted().body(liste);
            
     //    } catch (Exception e) {
     //        e.printStackTrace();
     //        // return e.getMessage();
     //    }
     //    return null;
     // }

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


     @GetMapping("/utilisateur_mise_encours/{id}")
     public ResponseEntity get_mise_encours(@PathVariable int id)
     {
        Detailsenchere[] details=null;
        Enchere enchere=null;
        Categorie categorie=null;
        JSONArray liste=null;
        try {
            details=S_Utilisateur.get_enchere_encours(new S_Utilisateur().get(id));
            liste=new JSONArray();
            for(int i=0;i<details.length;i++){
                enchere=new S_Enchere().get(details[i].getidenchere());
                categorie=new S_Categorie().get(enchere.getidcategorie());
                Detailsenchere[] dern=null;
                dern=S_Enchere.liste_details_enchere(enchere);
                JSONObject obj=new JSONObject();   
                obj.put("mise_dern",dern[dern.length-1].getmise());    
                obj.put("nom_mise",new S_Utilisateur().get(dern[dern.length-1].getidutilisateur()).getnom());    
                obj.put("prenom_mise",new S_Utilisateur().get(dern[dern.length-1].getidutilisateur()).getprenom());    
                obj.put("datemise_dern",dern[dern.length-1].getdatemise()); 
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
