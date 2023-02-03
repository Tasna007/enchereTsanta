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
public class C_Enchere {

    @GetMapping("/actualiser_etat_enchere")
     public void get_utilisateur()
     {
        try{
            S_Gagnant.gagner();
        }
        catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage();
        }

     }
    
     @GetMapping("/enchere_get_utilisateur/{id}")
     public String get_utilisateur(@PathVariable int id)
     {
        Enchere enchere=null;
        Utilisateur utilisateur=null;
        JSONObject obj=new JSONObject();    
        try {
            enchere=new S_Enchere().get(id);
            utilisateur=S_Enchere.get_utilisateur(enchere);
            obj.put("idutilisateur",utilisateur.getidutilisateur());    
            obj.put("nom",utilisateur.getnom());    
            obj.put("prenom",utilisateur.getprenom());    
            obj.put("email",utilisateur.getemail());    
            obj.put("password",utilisateur.getpassword());    
            obj.put("inscription",utilisateur.getinscription());    
            obj.put("naissance",utilisateur.getnaissance());    
            // obj.put("compte",utilisateur.getcompte());    
            
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return obj.toJSONString();
     }

    @GetMapping("/enchere_get_gagnant/{id}")
     public String get_gagnant(@PathVariable int id)
     {
        Enchere enchere=null;
        Utilisateur utilisateur=null;
        Gagnant gagnant=null;
        Detailsenchere details=null;
        JSONObject obj=new JSONObject();    
        try {
            enchere=new S_Enchere().get(id);
            utilisateur=S_Enchere.get_utilisateur(enchere);
            gagnant=S_Enchere.get_gagnant(enchere);
            details=new S_Detailsenchere().get(gagnant.getiddetailsenchere());
            obj.put("idutilisateur",utilisateur.getidutilisateur());    
            obj.put("nom",utilisateur.getnom());    
            obj.put("prenom",utilisateur.getprenom());    
            obj.put("email",utilisateur.getemail());    
            obj.put("idgagnant",gagnant.getidgagnant());    
            obj.put("prix",gagnant.getprix());
            obj.put("iddetailsenchere",details.getiddetailsenchere());       
            obj.put("datemise",details.getdatemise());    
            obj.put("mise",details.getmise());
            
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return obj.toJSONString();
     }

      @GetMapping("/enchere_details/{id}")
     public ResponseEntity get_details(@PathVariable int id)
     {
        Detailsenchere[] details=null;
        Utilisateur utilisateur=null;
        JSONArray liste=null;
        try {
            details=S_Enchere.liste_details_enchere(new S_Enchere().get(id));
            liste=new JSONArray();
            for(int i=0;i<details.length;i++){
                utilisateur=new S_Utilisateur().get(details[i].getidutilisateur());
                JSONObject obj=new JSONObject();    
                obj.put("iddetailsenchere",details[i].getiddetailsenchere());       
                obj.put("datemise",details[i].getdatemise());    
                obj.put("mise",details[i].getmise());    
                obj.put("idutilisateur",utilisateur.getidutilisateur());    
                obj.put("nom",utilisateur.getnom());    
                obj.put("prenom",utilisateur.getprenom());    
                obj.put("email",utilisateur.getemail());    
                liste.add(obj);
            }
            return ResponseEntity.accepted().body(details);
            
        } catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage();
        }
        return null;
     }

    @GetMapping("/get_enchere/{etat}")
     public ResponseEntity get_enchere(@PathVariable int etat)
     {
        Enchere[] enchere=null;
        Categorie categorie=null;
        Utilisateur utilisateur=null;
        JSONArray liste=null;
        try {
            enchere=new S_Enchere().getall(etat);
            liste =new JSONArray();    
            for(int i=0;i<enchere.length;i++){
                categorie=new S_Categorie().get(enchere[i].getidcategorie());
                utilisateur=new S_Utilisateur().get(enchere[i].getidutilisateur());
                JSONObject obj=new JSONObject();
                obj.put("idenchere",enchere[i].getidenchere());    
                obj.put("produit",enchere[i].getproduit());    
                obj.put("prix_planche",enchere[i].getprix_planche());    
                obj.put("duree",enchere[i].getduree());    
                obj.put("ajout",enchere[i].getajout());    
                obj.put("description",enchere[i].getdescription());    
                obj.put("etat",enchere[i].getetat());
                obj.put("categorie",categorie.getcategorie());
                obj.put("idutilisateur",utilisateur.getidutilisateur());    
                obj.put("nom",utilisateur.getnom());    
                obj.put("prenom",utilisateur.getprenom());    
                obj.put("email",utilisateur.getemail()); 
                liste.add(obj);
            }
            return ResponseEntity.accepted().body(liste);
            
        } catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage();
        }
        return null;
     }

     @GetMapping("/getAllEnchere/")
     public ResponseEntity get_enchereRehetra()
     {
        Enchere[] enchere=null;
        Categorie categorie=null;
        Utilisateur utilisateur=null;
        JSONArray liste=null;
        try {
            enchere=new S_Enchere().getRehetra();
            liste =new JSONArray();    
            for(int i=0;i<enchere.length;i++){
                categorie=new S_Categorie().get(enchere[i].getidcategorie());
                utilisateur=new S_Utilisateur().get(enchere[i].getidutilisateur());
                JSONObject obj=new JSONObject();
                obj.put("idenchere",enchere[i].getidenchere());    
                obj.put("produit",enchere[i].getproduit());    
                obj.put("prix_planche",enchere[i].getprix_planche());    
                obj.put("duree",enchere[i].getduree());    
                obj.put("ajout",enchere[i].getajout());    
                obj.put("description",enchere[i].getdescription());    
                obj.put("etat",enchere[i].getetat());
                obj.put("categorie",categorie.getcategorie());
                obj.put("idutilisateur",utilisateur.getidutilisateur());    
                obj.put("nom",utilisateur.getnom());    
                obj.put("prenom",utilisateur.getprenom());    
                obj.put("email",utilisateur.getemail()); 
                liste.add(obj);
            }
            return ResponseEntity.accepted().body(liste);
            
        } catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage();
        }
        return null;
     }

    @GetMapping("/enchere_photos/{id}")
     public ResponseEntity get_photos(@PathVariable int id)
     {
        Encherephoto[] photos=null;
        JSONArray liste=null;
        try {
            photos=S_Enchere.liste_photo(new S_Enchere().get(id));
            liste=new JSONArray();
            for(int i=0;i<photos.length;i++){
                JSONObject obj=new JSONObject();          
                obj.put("photo",photos[i].getphoto());     
                liste.add(obj);
            }
            return ResponseEntity.accepted().body(liste);
            
        } catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage();
        }
        return null;
     }

     @GetMapping("/categories")
     public ResponseEntity getall_categorie()
     {
        Categorie[] categorie=null;
        JSONArray liste=null;
        try {
            categorie=S_Categorie.getall();
            liste=new JSONArray();
            for(int i=0;i<categorie.length;i++){
                JSONObject obj=new JSONObject();          
                obj.put("idcategorie",categorie[i].getidcategorie());     
                obj.put("nom",categorie[i].getcategorie());     
                liste.add(obj);
            }
            return ResponseEntity.accepted().body(liste);
            
        } catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage();
        }
        return null;
     }
     @GetMapping("/get_byid/{id}")
     public String get_byid(@PathVariable int id)
     {
        Enchere enchere=null;
        Utilisateur utilisateur=null;
        Categorie categorie=null;
        JSONObject obj=new JSONObject();    
        try {
            enchere=new S_Enchere().get(id);
            utilisateur=S_Enchere.get_utilisateur(enchere);
            categorie=new S_Categorie().get(enchere.getidcategorie());
            obj.put("idutilisateur",utilisateur.getidutilisateur());    
            obj.put("nom",utilisateur.getnom());    
            obj.put("prenom",utilisateur.getprenom());    
            obj.put("email",utilisateur.getemail());    
            obj.put("categorie",categorie.getcategorie());
            
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return obj.toJSONString();
     }
 }