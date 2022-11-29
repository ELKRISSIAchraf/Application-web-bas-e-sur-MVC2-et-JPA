<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<title>Modifier user</title>
</head>
<body>
<nav><%@ include file="/gabarit.jsp" %></nav>

<div class="row">
    <div class="col-md-6 mx-auto mt-5">
        <form action="user?action=update" method="POST" >
         <input type="hidden" class="form-control" name="id" value="${user.id_user}">
            <div class="mb-3">
                <label class="form-label">Nom</label>
                <input type="text" class="form-control" name="nomUser" value="${user.nom}">
                
            </div>
            <div class="mb-3">
                <label class="form-label">email</label>
                <input type="text" class="form-control" name="emailUser" value="${user.email}">
            </div>
            <div class="mb-3">
                <label class="form-label">password</label>
                <input type="password" class="form-control" name="passwordUser" value="${user.password}">
            </div>
            <div class="mb-3">
                   <select name="roleUser" class="form-control" >
                  <option  value="1"  >admin</option>   
                  <option  value="2"  >Client</option>  
                   </select>
              </div>
            <div class="d-grid gap-2 ">
                <button type="submit" class="btn btn-primary "><i class="fas fa-save"></i> Modifier</button>
            </div>   
        </form>
    </div>
</div>

</body>
</html>