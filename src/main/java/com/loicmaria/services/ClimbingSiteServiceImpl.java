package com.loicmaria.services;


import com.loicmaria.entities.ClimbingSite;
import com.loicmaria.repositories.ClimbingSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <b>Classe service permettant l'ORM de l'objet ClimbingSite.</b>
 * <p>
 *     Elle hérite de la classe Services.
 * </p>
 *
 * @see Services
 * @see ClimbingSite
 *
 * @author Loïc MARIA
 * @version 1.0
 */
@Service
public class ClimbingSiteServiceImpl extends Services<ClimbingSite, ClimbingSiteRepository> {

    @Autowired
    ClimbingSiteRepository climbingSiteRepository;
    @Autowired
    UserAccountServiceImpl userAccountService;

    /**
     * <b>Ajout du site d'escalade dans la base de donnée.</b>
     * Ajoute l'utilisateur connecté.
     * @param climbingSite Le site d'escalade à modifier.
     */
    @Override
    public void add(ClimbingSite climbingSite){
        climbingSite.setUserAccount(this.userAccountService.getLoggedUserAccount());
        super.add(climbingSite);
    }

    /**
     * <b>Mise à jour du site d'escalade dans la base de donnée.</b>
     * Ajoute l'utilisateur connecté.
     * @param climbingSite Le site d'escalade à modifier.
     * @return Le site d'escalade modifié.
     */
    @Override
    public ClimbingSite update(ClimbingSite climbingSite){
        climbingSite.setUserAccount(this.userAccountService.getLoggedUserAccount());
        return repository.save(climbingSite);
    }


    /**
     * <b>Retourne une liste de sites d'escalade de l'utilisateur connecté.</b>
     * Permet de trouver tous les sites qu'il a lui même créés.
     * @param id L'ID de l'utilisateur connecté.
     * @return Une liste de sites d'escalade.
     */
    public Collection<ClimbingSite> findByUserAccount_Id(int id) {
        return climbingSiteRepository.findByUserAccount_Id(id);
    }

    /**
     * <b>Retourne une liste de sites d'escalade en fonction de l'Id du Topo</b>
     * @param id L'ID du Topo.
     * @return Une liste de sites d'escalade.
     */
    public Collection<ClimbingSite> findByTopo_Id(int id){
        return climbingSiteRepository.findByTopo_Id(id);
    }

    /**
     * <b>Retourne une liste de sites d'escalade pour le système de recherche.</b>
     * Permet de trouver les sites d'escalade en prenant le nom + le secteur.
     * @param name Le nom du site d'escalade qui doit être trouvé.
     * @param area Le secteur du site d'escalade qui doit être trouvé.
     * @return Une liste de sites d'escalade.
     */
    public Collection<ClimbingSite> findByNameAndArea(String name, String area){
        return climbingSiteRepository.findByNameAndArea(name,area);
    }
}
