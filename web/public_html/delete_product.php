<?php
include "includes/header.php";
if(isset($_SESSION['username']))
{
   
    if (isset($_GET['id'])) {
      $id=$_GET['id'];
      $user_id=$_SESSION['userid'];
  
    if(mysqli_query($dbhandle,"DELETE FROM `products` WHERE `product_id`='$id' and `product_publisher_id`='$user_id'")){
        
        mysqli_query($dbhandle,"DELETE FROM `orders` WHERE `product_id`='$id'");
        mysqli_query($dbhandle,"DELETE FROM `cart` WHERE `product_id`='$id'");
        mysqli_query($dbhandle,"DELETE FROM `ratings` WHERE `product_id`='$id'");
        
        $_SESSION['message']="<div class='chip green white-text'>Product Successfully Deleted</div>";
        header("Location: dashboard.php");
        
    }else{
        $_SESSION['message']="<div class='chip red white-text'>Some Error While Deleting</div>";
        header("Location: dashboard.php");
    }
    
    }
    else
    {
         header("Location: dashboard.php");
    }
    
}
else {
$_SESSION['message']="<div class='chip red white-text'>Login To Continue</div>";
  header("Location: login.php");
}

?>    