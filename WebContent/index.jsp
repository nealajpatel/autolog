<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AutoLogin Authentication</title>
</head>
<body>
<div class="container">  
  <form id="contact" action="Login" method="post">
    <h3>New Vehicle Form</h3>
    <h4>Enter Vehicle Information</h4>
    <fieldset>
      <input placeholder="Username" type="text" name="username" tabindex="1" required autofocus>
    </fieldset>
    <fieldset>
      <input placeholder="Password" type="password" name="password" tabindex="2" required>
    </fieldset>
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Submit</button>
    </fieldset>
    <!-- <p class="copyright">Designed by <a href="https://colorlib.com" target="_blank" title="Colorlib">Colorlib</a></p>-->
  </form>
</div>
</body>
</html>
<!-- <html>
<head>
  <meta charset="UTF-8">
  <title>AutoLog Login</title>
  
  
  
      <link rel="stylesheet" href="css/style.css">

  
</head>

<body>
  <div id="wrap">
  <div id="regbar">
    <div id="navthing">
      <h2><a href="#" id="loginform">Login</a> | <a href="#">Register</a></h2>
    <div class="login">
      <div class="arrow-up"></div>
      <div class="formholder">
        <div class="randompad">
           <fieldset>
           <form method="post" action="Login">
             <label name="username">Username</label>
             <input type="text" value="JohnDoe" />
             <label name="password">Password</label>
             <input type="password" />
             <input type="submit" value="Login" />
 			</form>
           </fieldset>
        </div>
      </div>
    </div>
    </div>
  </div>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body>
</html> -->
