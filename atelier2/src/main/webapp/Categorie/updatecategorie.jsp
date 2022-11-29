<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Modifier categorie</title>
</head>
<body>
<nav><%@ include file="/gabarit.jsp" %></nav>

<div class="row">
    <div class="col-md-6 mx-auto mt-5">
        <form action="categorie?action=update" method="POST" >
         <input type="hidden" class="form-control" name="id" value="${categorie.id_cat}">
            <div class="mb-3">
                <label class="form-label">Nom</label>
                <input type="text" class="form-control" name="nomCat" value="${categorie.nom}">
                
            </div>
            
            <div class="d-grid gap-2 ">
                <button type="submit" class="btn btn-primary "><i class="fas fa-save"></i> Modifier</button>
            </div>   
        </form>
    </div>
</div>

</body>
</html>