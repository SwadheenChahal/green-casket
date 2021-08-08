<?php
include "includes/header.php";
if(isset($_POST['login']))
{

$email=$_POST['email'];
$password=$_POST['password'];
$sql="select * from pubs where pub_email='$email' and pub_password='$password'";

if($res=mysqli_query($dbhandle,$sql)){
  $row=mysqli_fetch_assoc($res);
  $_SESSION['userid']=$row['pub_id'];
  $_SESSION['username']=$row['pub_name'];
  header("Location: dashboard.php");
} else {
  $_SESSION['message']="<div class='chip red white-text'> Sorry, Credentials Don't Match</div>";
  header("Location: login.php");}
    
}
else header("Location: login.php");

?>