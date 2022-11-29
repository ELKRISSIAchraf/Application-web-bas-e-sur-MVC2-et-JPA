<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-
8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>Produits</title>


</head>
<body>

<nav><%@ include file="/gabarit.jsp" %></nav>

<div class="row d-flex flex-column flex-shrink-0 p-5  ">
    <div class="col-12">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Liste des Produits</h4>
     <a href="produit?action=ajouterProduit"><button type="button" data-bs-toggle="modal" data-bs-target="#AddModal" class="btn btn-default btn-sm"><i class="fas fa-plus-circle"></i>Ajouter nouveau produit</button></a>   
                <div class="table-responsive">
                    <table class="table align-middle text-nowrap">
                        <thead>
                            <tr>
                                <th class="text-dark font-medium fs-4">Image</th>
                                <th class="text-dark font-medium fs-4">Nom</th>
                                <th class="text-dark font-medium fs-4">PU</th>
                                <th class="text-dark font-medium fs-4">QTE</th>
                                <th class="text-dark font-medium fs-4">Description</th>
                                <th class="text-dark font-medium fs-4">Categorie</th> 
                                <th class="text-dark font-medium fs-4">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listproduits}" var="prod">
                            <tr >
                                 <td class="fs-4"><img src="${pageContext.request.contextPath}/images/${prod.image}" style="border-radius: 50%; padding: 5px;width: 150px;"/></td>
                                <td class="fs-4">${prod.nom}</td>	
                                <td class="fs-4">${prod.pu}</td>
                                <td class="fs-4">${prod.qte}</td>
                                 <td class="fs-4">${prod.description}</td>
                                 <td class="fs-4"><c:forEach items="${listcategories}" var="cat">
                               <c:if test="${cat.id_cat==prod.id_cat}">  
                                ${cat.nom} </c:if> </c:forEach></td>
                                <td class="fs-4">  
                                    <h5 class="mb-0 fw-normal">
                                     <a href="produit?action=delete&id=<c:out value='${prod.id_prod}' />"><button type="button" class="btn btn-default btn-sm">delete</button></a>   
                                     <a href="produit?action=updateProduit&id=<c:out value='${prod.id_prod}' />"><button type="button" class="btn btn-default btn-sm">update</button></a>   
                                    </h5>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                        
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
