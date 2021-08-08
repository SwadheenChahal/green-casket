<?php
include "includes/header.php";
if(isset($_SESSION['username']))
{
   
    if (isset($_GET['id'])&&isset($_GET['status'])) {
    $id=$_GET['id'];
    $status=$_GET['status'];
    
    if(mysqli_query($dbhandle,"UPDATE `orders` SET `order_status`='$status' WHERE `order_id`='$id'")){
        
        $_SESSION['message']="<div class='chip green white-text'>Order Status Changed Success</div>";
        header("Location: dashboard.php");
        
    }else{
        $_SESSION['message']="<div class='chip red white-text'>Something went wrong</div>";
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