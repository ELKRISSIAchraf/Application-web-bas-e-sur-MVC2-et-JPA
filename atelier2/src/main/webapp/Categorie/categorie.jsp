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
<title>Categories</title>
</head>
<body>
<nav><%@ include file="/gabarit.jsp" %></nav>
<div class="row">
    <div class="col-12">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Liste des Categories</h4>
     <a href="categorie?action=ajouterCategorie"><button type="button" data-bs-toggle="modal" data-bs-target="#AddModal" class="btn btn-default btn-sm"><i class="fas fa-plus-circle"></i>Ajouter nouvelle categorie</button></a>   
                <div class="table-responsive">
                    <table class="table align-middle text-nowrap">
                        <thead>
                            <tr>
                                <th class="text-dark font-medium fs-4">ID</th>
                                <th class="text-dark font-medium fs-4">Nom</th>              
                                <th class="text-dark font-medium fs-4">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                         <c:forEach items="${listcategories}" var="cat">
                            <tr >
                               <td class="fs-4">${cat.id_cat}</td>
                                <td class="fs-4">${cat.nom}</td>	
                                
                                <td>
                                    <h5 class="mb-0 fw-normal">
                                     <a href="categorie?action=delete&id=<c:out value='${cat.id_cat}' />"><button type="button" class="btn btn-default btn-sm">delete</button></a>   
                                     <a href="categorie?action=updateCategorie&id=<c:out value='${cat.id_cat}' />"><button type="button" class="btn btn-default btn-sm">update</button></a>   
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
</html>