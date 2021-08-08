<?php
include "includes/header.php";
if(isset($_POST['signup']))
{
$email=$_POST['email'];
$username=$_POST['username'];
$password=$_POST['password'];
$number=$_POST['phone'];


$sql1="select * from pubs where pub_email='$email'";
$res1=mysqli_query($dbhandle,$sql1);
if(mysqli_num_rows($res1)>0)
{
  header("Location: login.php");
  $_SESSION['message']="<div class='chip red white-text'>Email Already Exist</div>";
}
else
{
  $sql="INSERT INTO `pubs`(`pub_name`, `pub_email`, `pub_number`, `pub_password`) VALUES ('$username','$email','$number','$password')";
  
  $res=mysqli_query($dbhandle,$sql);
  if($res)
  {
      
    $sql34="select publisherId from pubs where pub_email='$email'";
    $res4=mysqli_query($dbhandle,$sql34);
    $row32=mysqli_fetch_assoc($res4);
    $pubId=$row32['pub_email'];

      header("Location: login.php");
      $_SESSION['message']="<div class='chip yellow white-text'>Registered Success,Login To Continue</div>";

  }
  else
  {
    header("Location: login.php");
    $_SESSION['message']="<div class='chip red black-text'>An Error Occurred</div>";
  }
}

}
