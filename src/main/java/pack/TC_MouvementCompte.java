package pack;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.*;

import model.T_MouvementCompte;
import service.TS_MouvementCompte;
import model.*;
import service.*;


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

    @GetMapping("/get_compte/{id}")
    public ResponseEntity get_compte(@PathVariable int id)
    {
        TV_Compte compte=TS_Compte.getVCompte(id);
        JSONArray liste=null;
        try {
            liste=new JSONArray();
            JSONObject obj=new JSONObject();    
            obj.put("idcompte",compte.getIdCompte());    
            obj.put("argent",compte.getArgent());    
            obj.put("solde",compte.getSolde());    
            obj.put("mise",compte.getMise());   
            liste.add(obj); 
            return ResponseEntity.accepted().body(liste);
            
        } catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage();
        }
        return null;
    }
}
