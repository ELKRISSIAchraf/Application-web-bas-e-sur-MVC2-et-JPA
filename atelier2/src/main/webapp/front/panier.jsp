<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js" integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V" crossorigin="anonymous"></script>
</head>
<body>
<% 
Long iduser = (Long) session.getAttribute("id");
pageContext.setAttribute("idUser",iduser);
%>
<nav><%@ include file="navbar.jsp" %></nav>


<div class="container  mt-5">
<div class="row">
 <c:forEach items="${listpaniers}" var="pan">
 <c:forEach items="${listproduits}" var="prod">
 <c:if test="${prod.id_prod==pan.id_prod && idUser==pan.id_user}">
  <div class="card mb-5 mx-3 " style="width: 20rem;" >
  <img src="${pageContext.request.contextPath}/images/${prod.image}" class="card-img-top" alt="..." style=" padding: 3px;width: 20rem;height: 15rem">
  <div class="card-body" >
    <h5 class="card-title">${prod.nom}</h5>
    <h5 class="card-title">${prod.pu} DH</h5>
    <p class="card-text">${prod.description}</p>
    <p class="card-text"><small class="text-muted">quantit� rest� en stock : ${prod.qte}  </small></p>
     <a href="frontController?action=deletefrompanier&id=<c:out value='${pan.id_panier}' />" class="mx-5"><button type="button" class="btn btn-default btn-sm mx-5">delete</button></a>   
  </div>
</div>
</c:if>
</c:forEach>
</c:forEach>

</div>
  </div>
  

</body>
</html>