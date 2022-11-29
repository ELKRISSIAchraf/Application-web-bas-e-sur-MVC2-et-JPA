<% 
String nom = (String) session.getAttribute("nom");

pageContext.setAttribute("nom",nom);
%>
<nav class="navbar navbar-light bg-light">

  <div class="container-fluid">
 
    <a class="navbar-brand "  href="frontController?action=accueil">Home</a>
 
   <form style="background-color:rgb(40,40,40);">
									<select onChange="location = this.options[this.selectedIndex].value;" style="background-color:rgb(40,40,40);color:rgb(200,200,200);" class="input-select">
		                              <option value="frontController?action=acceuil"> All Categories</option>
										<c:forEach items="${listcategories}" var="cat">                               
										<option value="frontController?action=categorie&id=<c:out value='${cat.id_cat}' />">${cat.nom}</option>
										</c:forEach>
									</select>
									<input style="background-color:#F8F8F8;color:#202020;" class="input" placeholder="Search here">
									<button style="background-color:rgb(40,40,40);color:rgb(200,200,200);" class="search-btn">Search</button>
								</form>
								
								
                            <c:if test="${ nom!=null  }">                          
                       <div class="dropdown mx-3">
  <button class="btn btn-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
   ${ nom}
  </button>
  <ul class="dropdown-menu">
    <li><a class="dropdown-item" href="frontController?action=logout">Logout</a></li>
  </ul>
  				<a href="frontController?action=monpanier" ><i class="fa-solid fa-cart-shopping mx-3" style='font-size:30px;color:rgb(40,40,40);'></i></a>
  
</div>
						 </c:if>
                              <c:if test="${ nom==null}">
                              <div>
								<a href="frontController?action=login"  ><i class="fa-solid fa-user mx-1" style='font-size:30px;color:rgb(40,40,40);'></i></a>	
									<a href="frontController?action=monpanier" ><i class="fa-solid fa-cart-shopping mx-3" style='font-size:30px;color:rgb(40,40,40);'></i></a>
						</div>
						 </c:if>
						 
</div>

</nav>
