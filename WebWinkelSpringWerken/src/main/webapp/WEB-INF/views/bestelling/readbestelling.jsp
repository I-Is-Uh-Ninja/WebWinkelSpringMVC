<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- 
    Document   : readallartikel
    Created on : 15-sep-2016, 11:10:24
    Author     : Excen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bestellingen</title>
    </head>
    <body>
       <div align = "center">
           
             
           <table> 
                <tr>
                    
                    
                    <td>${bestelling.id}</td>
                    
                    <td>${bestelling.klant.id}</td>
                    <td>${bestelling.bestellingDatum}</td> 
                    <td><c:forEach var="bestellingartikel" items="${bestellingartikellijst}">
                            <c:out value='Artikel: ${bestellingartikel.artikel.artikelNaam}, aantal: ${bestellingArtikel.artikelAantal}'>
                            </c:out><br/></c:forEach></td>
                    <td>${bestelling.factuur.id}</td>
                     
                </tr>
                
           </table>
           <br/>
        <a href = "<c:url value = '/'/>"> Terug naar huis</a>
        </div>
    </body>
</html>




