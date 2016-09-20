

package com.anjewe.anjewewebwinkel.Controller;

import com.anjewe.anjewewebwinkel.Service.AccountService;
import com.anjewe.anjewewebwinkel.POJO.Account;
import com.anjewe.anjewewebwinkel.POJO.Klant;
import com.anjewe.anjewewebwinkel.Service.GenericServiceInterface;
import com.anjewe.anjewewebwinkel.Service.KlantService;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.util.Date;

/**
 * @author Wendy
 */


@Controller
public class AccountController {

private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired 
    GenericServiceInterface<Account, Long> accountService= new AccountService();
    @Autowired
    GenericServiceInterface<Klant, Long> klantService= new KlantService(); 
    @Autowired
    MessageSource messageSource;

    @RequestMapping (value = "/account/homeaccount", method = RequestMethod.GET)
        public String account(){
            return "account/homeaccount";
        }
    
    /**
     * Lijst bestaande accounts
     * @param model
     * @return 
     */
    
    @RequestMapping(value = {"accounts", "/account/readallaccount" }, method = RequestMethod.GET)
    public String listAccounts(ModelMap model) { // wat doet modelMap?
 
        List<Account> accounts = accountService.zoekAlleBeans();
        model.addAttribute("accounts", accounts);
        return "account/readallaccount"; // accountlijst.jsp
    }
    
    /**
     * Methode om nieuw account toe te voegen.
     * @param model
     * @return 
     */
    
    @RequestMapping(value = {"account", "/account/createaccount"}, method = RequestMethod.GET)
    public String nieuwAccount() {
        return "klant/createklant";
    }
    
    @RequestMapping(value = { "account", "/account/createaccountklant-{Id}" }, method = RequestMethod.GET) // value= waarvan?
    public String nieuwAccountKlant(ModelMap model, @PathVariable Long Id) {
        Account account = new Account();
        Klant klant = klantService.zoekNaarBean(Id);
        account.setKlant(klant);
        
//        dit wil je kunnen doen
//        begin vanuit account view create
//          
//        eerst klant aanmaken met createklant
//        dan via createadres
//        via klantcontroller naar nieuwe account view create account
//        alles in een view?
//        

        
        model.addAttribute("account", account); 
        model.addAttribute("edit", false); 
        return "account/addaccount"; 
    }
    
     // save        
        @RequestMapping(value = { "/account/createaccountklant-{Id}" }, method = RequestMethod.POST)
        public String saveAccount(@Valid Account account, @PathVariable Long Id, BindingResult result, 
            ModelMap model){
            
                if (result.hasErrors()) {
                    return "account/addaccount"; // aanpassen> aangeven waar error zit
                } 
                
                
                accountService.voegNieuweBeanToe(account); 
                model.addAttribute("success", "Account: Account username " + account.getUsername()
                        + " account password " + account.getPassword() + " is toegevoegd aan het bestand");
            //return "success"; 
            return "account/registratiegelukt"; 
        }
        
    
    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
//    @RequestMapping(value = { "account/createaccount" }, method = RequestMethod.POST)
//    public String saveAccount(@Valid Account account, BindingResult result,
//            ModelMap model) {
// 
//        if (result.hasErrors()) {
//            return "account/createaccount"; // TODO: uiteindelijk naar een andere pagina die aangeeft waarom wel of niet gelukt
//        }
        /*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
         * and applying it on field [sso] of Model class [User].
         * 
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the 
 
validation
         * framework as well while still using internationalized messages.
         * 
         */
//        if(!accountService.isBeanUniek(account.getId(), account.getUsername())){
//            FieldError ssoError = new FieldError("account","username",
//                    messageSource.getMessage("non.unique.username", new String[]{account.getUsername()}, 
//                            Locale.getDefault()));
//            
//            result.addError(ssoError);
//            return "registration";
//        }
////         
//
//
//        accountService.voegNieuweBeanToe(account);
// 
//        model.addAttribute("success", "Account " + account.getUsername()+ " "+ account.getPassword()+
// 
//             "registered successfully");
//        //return "success";
//        
//        return "registratiegelukt";
//    }
// 
// 
    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = { "account/updateaccount-{Id}" }, method = RequestMethod.GET)
    public String editUser(@PathVariable Long Id, ModelMap model) {
        Account account = (Account)accountService.zoekNaarBean(Id);
        model.addAttribute("account", account);
        model.addAttribute("edit", true);
        return "registratie";
    }
     
    
    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "account/updateaccount-{Id}" }, method = RequestMethod.POST)
    public String updateUser(@Valid Account account, BindingResult result,
            ModelMap model, @PathVariable Long Id) {
 
        if (result.hasErrors()) {
            return "registratie";
        }
// 
//        /*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
//        if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
//            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new 
// 
//String[]{user.getSsoId()}, Locale.getDefault()));
//            result.addError(ssoError);
//            return "registration";
//        }*/
// 
        accountService.wijzigBeanGegevens(account);
 
        model.addAttribute("success", "Account " + account.getUsername()+ " "+ account.getPassword()+
 
             "registered successfully");
        //return "success";
        
        return "registratiegelukt";
    }
     
    /**
     * This method will delete an user by it's ID value.
     */
    @RequestMapping(value = { "/deleteallaccount-{Id}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long Id) {
        accountService.verwijderBeanGegevens(Id);
        return "redirect:/list";
    }
     
 
    /**
     * This method will provide UserProfile list to views
     */
    /* @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
    return userProfileService.findAll();
    }
    */

}
