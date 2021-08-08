<?php
include "includes/header.php";
if(isset($_SESSION['username']))

{
?>
<!DOCTYPE html>
<html>
<head>
  
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css" rel="stylesheet" />
  
  <link rel="icon" type="image/png" href="admin_imgs/icon.png"/>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP&display=swap" rel="stylesheet">


<style>


footer,header,.main{
padding-left:300px;
}
@media(max-width:992px)
{
  footer,header,.main{
padding-left:0px;
}
}
</style>
  <!--Let browser know website is optimized for mobile-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Green Casket</title>
</head>
<body style="font-family: 'Noto Sans JP', sans-serif;">

  <nav>
    <div class="nav-wrapper green">
   
  <a href="dashboard.php"> <label style="font-size: 20px;color: white;margin-left: 10px;">Green Casket</label></a>
     
     
      <ul class="right">
          
    
             <li><a href="add_product.php"><i class="material-icons">add_circle</i></a></li>

        
        <li><a href="change_password.php"><i class="material-icons">lock</i></a></li>
      
       <li><a href="logout.php"><i class="material-icons">logout</i></a></li>
       
      
      </ul>
      
      
    </div>
  </nav>



<?php
}
else
{
  $_SESSION['message']="<div class='chip red black-text'>Login To Continue</div>";
  header("Location: ../login.php");
}
?>