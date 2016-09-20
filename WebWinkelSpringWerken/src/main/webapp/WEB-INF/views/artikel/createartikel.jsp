<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- 
    Document   : createartikel
    Created on : 15-sep-2016, 11:09:26
    Author     : Wendy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Artikel Pagina</title>
        <link href = "<c:url value = "/resources/css/main.css"/>"  rel = "stylesheet" type = "text/css">
    </head>
    <body>
        <h1>Hello Artikel!</h1>
        <p> Het is vandaag:
            <%=new java.util.Date()%> 
        </p>

        <h3>Artikel gegevens</h3>
        <form method = "POST">
            Artikelnaam <input type = "text" name = "artikelNaam" value = ${artikel.artikelNaam}/> <br/>
            Prijs € <input type = "text" name = "artikelPrijs" value =${artikel.artikelPrijs}/> <br/>
            Omschrijving <input type = "text" name = "omschrijving" value = ${artikel.omschrijving}/><br/>
            Art.nummer   <input type = "text" name = "artikelNummer" value = ${artikel.artikelNummer}/><br/>

            <input type = "submit" value = "opslaan"/> | 
            <a href="<c:url value='/artikel/readallartikel' />">Annuleer</a>
        </form>   


        <a href = "<c:url value = '/'/>"> Terug naar huis</a>
    </body>
</html>
