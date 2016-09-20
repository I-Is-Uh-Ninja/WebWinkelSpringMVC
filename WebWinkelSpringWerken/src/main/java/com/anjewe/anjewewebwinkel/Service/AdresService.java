/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.Service;


import com.anjewe.anjewewebwinkel.DAOGenerics.GenericDaoImpl;
import com.anjewe.anjewewebwinkel.DAOs.AdresDao;
import com.anjewe.anjewewebwinkel.POJO.Adres;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anne
 */

@Component
public class AdresService implements GenericServiceInterface <Adres, Long>{
     
    
    
    private static final Logger logger = (Logger) LoggerFactory.getLogger("com.anjewe.anjewewebwinkel");
    private static final Logger errorLogger = (Logger) LoggerFactory.getLogger("com.anjewe.anjewewebwinkel.err");
    private static final Logger testLogger = (Logger) LoggerFactory.getLogger("com.anjewe.anjewewebwinkel.test");
   
   @Autowired 
   Adres adres;    
   ArrayList<Adres> adressenLijst = new ArrayList();
   @Autowired
    protected GenericDaoImpl<Adres, Long> adresDao = new AdresDao();
    

    @Override
    public Adres voegNieuweBeanToe(Long Id) {         
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long voegNieuweBeanToe(Adres adres) {
        Long adresId = adresDao.insert(adres);
        return adresId;
    }

    @Override
    public Adres zoekNaarBean(Long Id) {
        adres = (Adres)adresDao.readById(Id);
        return adres; 
    }

    @Override
    public Long zoekNaarBean(Adres t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Adres> zoekAlleBeans() {  
        adressenLijst = (ArrayList<Adres>)adresDao.readAll(Adres.class);
        return adressenLijst; 
    }

    @Override
    public Adres wijzigBeanGegevens(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Adres wijzigBeanGegevens(Adres adres) {
        Adres gewijzigdAdres = (Adres)adresDao.readById(adres.getId());
        if (gewijzigdAdres!= null){
            gewijzigdAdres.setStraatnaam(adres.getStraatnaam());
            gewijzigdAdres.setPostcode(adres.getPostcode());
            gewijzigdAdres.setHuisnummer(adres.getHuisnummer());
            gewijzigdAdres.setToevoeging(adres.getToevoeging());
            gewijzigdAdres.setWoonplaats(adres.getWoonplaats()); 
        }
        adresDao.update(gewijzigdAdres);
        return gewijzigdAdres;
    }
    
        

    @Override
    public boolean verwijderBeanGegevens(Long Id) {
        boolean isDeletedInAdres = adresDao.deleteById(Id);
        return isDeletedInAdres;
    }

    @Override
    public int verwijderAlleBeans() {
        int rowsAffected = adresDao.deleteAll(Adres.class);
        return rowsAffected; 
    }

    @Override
    public boolean isBeanUniek(long id, String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
