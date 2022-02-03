<%@page import="com.aanass.dao.ProductDao"%>
<%@page import="java.util.*"%>
<%@page import="com.aanass.connection.DBConnection"%>
<%@page import="com.aanass.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}
List<Product> products = ProductDao.getAllProducts();
ArrayList<Cart> cart_list = (ArrayList<Cart>)session.getAttribute("cart-list");
if(cart_list !=null){
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to shopping cart!</title>
<%@ include file="includes/header.jsp"%>
</head>
<body>


	<%@ include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Product</div>
		<div class="row">
		
		<%if(!products.isEmpty()){
			for(Product p:products){%>
				<div class="col-md-3 my-3">
				<div class="card w-100" style="width: 18rem;">
					<img src="product-images/<%=p.getImage() %>" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title"><%= p.getName()%></h5>
						<h6 class="price">Price: $<%= p.getPrice()%></h6>
						<h6 class="category">Category: <%=p.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a href="Add-To-Cart?id=<%=p.getId() %>" class="btn btn-dark">Add
								to cart</a> <a href="order-now?quantity=1&id=<%=p.getId() %>" class="btn btn-primary">Buy now</a>
						</div>
						
					</div>
				</div>
			</div>
			<% }
			}%>
			
		</div>
	</div>
	<%@ include file="includes/footer.jsp"%>
</body>
</html>