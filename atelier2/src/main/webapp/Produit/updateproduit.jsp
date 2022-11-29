<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Modifier produit</title>
</head>
<body>
<nav><%@ include file="/gabarit.jsp" %></nav>
<div class="row">
    <div class="col-md-6 mx-auto mt-5">
        <form action="produit?action=update" method="POST" enctype="multipart/form-data">
         <input type="hidden" class="form-control" name="id" value="${produit.id_prod}">
                     <div class="mb-3">
                <label class="form-label">Nom</label>
                <input type="text" class="form-control" name="nomProd" value="${produit.nom}">
            </div>
            <div class="mb-3">
                <label class="form-label">PU</label>
                <input type="number" class="form-control" name="pu" value="${produit.pu}">
            </div>
            <div class="mb-3">
                <label class="form-label">QTE</label>
                <input type="number" class="form-control" name="qte" value="${produit.qte}">
                 </div>
                  <div class="mb-3">
                <label class="form-label">Description</label>
               <textarea class="form-control" name="descriptionProd"  >${produit.description}</textarea>                        
                 </div>
         
            <div class="mb-3">
                 <label class="form-label">Categorie</label>
               <select name="idCat" class="form-control" >
                  <c:forEach items="${listcategories}" var="cat"><option  <c:if test="${produit.id_cat==cat.id_cat}">  selected="selected" </c:if>  value="${cat.id_cat}">${cat.nom}</option></c:forEach>   
                   </select>
            </div>  
           <div class="mb-3">
                <label class="form-label">Image</label>
                <input type="file" class="form-control" name="image" >
                 </div>      
            <div class="d-grid gap-2 ">
                <button type="submit" class="btn btn-primary "><i class="fas fa-save"></i> Modifier</button>
            </div>   
        </form>
    </div>
</div>
</div>
</body>
</html>