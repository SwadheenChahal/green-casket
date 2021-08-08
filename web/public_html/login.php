<?php
include "includes/header.php";
if(!isset($_SESSION['username']))
{
?>
<!DOCTYPE html>
<html>
<head>
  <!--Import Google Icon Font-->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <!--Import materialize.css-->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css" rel="stylesheet" />
  
  <link rel="icon" type="image/png" href="admin_imgs/icon.png"/>
  <!--Let browser know website is optimized for mobile-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Green Casket</title>
</head>
<body style="background-image:url('admin_imgs/main_bg.jpg');background-size:cover">
<div class="row" style="margin-top:100px">
<div class="col l6 offset-l3 m8 offset-m2 s12">
<div class="card-panel center white" style="margin-bottom:0px">

<ul class="tabs">
<li class="tab">
<a href="#login" class="black-text">Login</a>
</li>
<li class="tab">
<a href="#signup" class="black-text">Sign Up</a>
</li>
</ul>

</div>

</div>

<div class="col l6 offset-l3 m8 offset-m2 s12" id="login" >
<div class="card-panel center green" style="margin-top:1px">
<?php
if(isset($_SESSION['message']))
{
  echo $_SESSION['message'];
  unset($_SESSION['message']);
}
?>
<form action="login_check.php" method="POST">
<div class="input-field">
<input type="text" id="email" name="email" placeholder="Email" class="validate" required>
</div>
<div class="input-field">
<input type="password" name="password" placeholder="Password" class="validate" required>
</div>
<input type="submit" name="login" value="Login" class="btn blue">
</form>
</div>
</div>
<div class="col l6 offset-l3 m8 offset-m2 s12" id="signup">
<div class="card-panel center blue" style="margin-top:1px">
    
<form action="signup.php" method="POST">
    
<div class="input-field">
<input type="text" id="username" name="username" placeholder="Full Name" class="validate" required>
<label for="username" data-error="Invalid Name" ></label>
</div>

<div class="input-field">
<input type="tel" name="phone" id="phone" placeholder="Phone" class="validate" required> 
<label for="phone" data-error="Invalid Number"></label>
</div>


<div class="input-field">
<input type="email" name="email" id="email" placeholder="Email" class="validate" required> 
<label for="email" data-error="Invalid Email Format" data-success="Valid Email"></label>
</div>


<div class="input-field">
<input type="password" name="password" placeholder="Password" class="validate" required>
</div>

<input type="submit" name="signup" class="btn red" value="Sign Up">
</form>
</div>
</div>

<!--Sign up and login area-->
</div>


</body>
</html>

<?php
include "includes/footer.php";
}
else
{
   header("Location: dashboard.php");
}
?>