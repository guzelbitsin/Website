<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>LOGIN</title>
    </head>
    <body>
    <center> <h1><u> Please Login </u></h1> </center> 
    <center>
        <div>
            <form action="login" method="POST">
                <table>
                    <tr>
                        <td>UserName</td>
                        <td><input type="text" class="form-control" name="username" placeholder="username"></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" class="form-control" name="password" placeholder="password"></td>                         
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input class="btn btn-success" type="submit" value="Login"</td>
                    </tr>
                    
                </table>                 
            </form>
            <form action="signUser.jsp" method="POST">
                <tr>
                    <td colspan="2" style="text-align: left"><input class="btn btn-success" type="submit" value="Sign Up!"</td>
                </tr>                
                
            </form>

        </div>
    </center>
    </body>
</html>