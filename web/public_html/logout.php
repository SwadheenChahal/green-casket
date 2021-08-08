<?php
include "includes/header.php";
if(isset($_SESSION['username']))
{
unset($_SESSION['username']);
unset($_SESSION['userid']);
$_SESSION['message']="<div class='chip yellow white-text'>Successfully Logged Out.</div>";
header("Location: login.php");
}
else
{
  $_SESSION['message']="<div class='chip red white-text'>Login To Continue</div>";
  header("Location: login.php");
}
?>