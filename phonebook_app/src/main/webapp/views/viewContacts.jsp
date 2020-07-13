<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet" type="css/text">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<script>
$(document).ready(function() {
    $('#contactTbl').DataTable( {
        "pagingType": "full_numbers"
    } );
} );
   function deleteConfirmation(){
         return confirm("are you sure, You wanted to delete");
	   }
</script>
</head>

<body>

<a href="loadform">Add Contact</a>


<table border="1" id="contactTbl">
<caption>Retrieving tables</caption>
  <thead>
      <tr>
         <th>S.No</th>
         <th>Name</th>
         <th>Email</th>
         <th>phone number</th>
         <th>Action</th>
      </tr>
  </thead>
  <tbody>
     <c:forEach items="${contacts}" var="c" varStatus="index">
        <tr>
           <td>${index.count}</td>
            <td>${c.contactName}</td>
             <td>${c.contactEmail}</td>
              <td>${c.phoneNumber}</td>
              <td>
                 <a href="editContact?cid=${c.contactId}">Edit</a> |
                 <a href="deleteContact?cid=${c.contactId}" onClick="return deleteConfirmation();">Delete</a>
              </td>
        </tr>
     </c:forEach>
     
  </tbody>
</table>
</body>
</html>