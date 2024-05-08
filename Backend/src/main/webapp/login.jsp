<%--
  Created by IntelliJ IDEA.
  User: 32578
  Date: 2024/4/20
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="./images/favicon.ico" />
    <title>登录-学生选课管理系统</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        a {
            text-decoration: none;
        }

        input,
        button {
            background: transparent;
            border: 0;
            outline: none;
        }

        body {
            height: 100vh;
            background: linear-gradient(#141e30, #243b55);
            background-image: url('./images/loginbg.jpg');
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 16px;
            color: #03e9f4;
        }

        .loginBox {
            width: 400px;
            height: 460px;
            background-color: #fff;
            margin: 100px auto;
            border-radius: 10px;
            box-shadow: 0 15px 25px 0 rgba(0, 0, 0, .6);
            padding: 60px 40px;
            box-sizing: border-box;
        }

        h2 {
            text-align: center;
            color: #458142;
            margin-bottom: 30px;
            font-family: 'Courier New', Courier, monospace;
        }

        .item {
            height: 45px;
            border-bottom: 1px solid #ccc;
            margin-bottom: 40px;
            position: relative;
        }

        .item input {
            width: 100%;
            height: 100%;
            color: #666;
            padding-top: 20px;
            box-sizing: border-box;
        }

        .item input:focus+label,
        .item input:valid+label {
            top: 0px;
            font-size: 8px;

        }

        .item label {
            position: absolute;
            left: 0;
            top: 12px;
            transition: all 0.5s linear;
            color: #333;
        }

        .btn {
            padding: 10px 30px;
            margin-top: 30px;
            color: #458142;
            background:rgba(69, 129, 66,0.1) ;
            border-radius: 5px;
            position: relative;
            overflow: hidden;
            text-transform: uppercase;
            letter-spacing: 2px;
            cursor: pointer;
            left: 35%;
        }

        .btn:hover {

            color: #fff;
            background: #458142;
            /*box-shadow: 0 0 5px 0 rgba(69, 129, 66,0.2),*/
            /*0 0 25px 0 rgba(69, 129, 66,0.2),*/
            /*0 0 50px 0 rgba(69, 129, 66,0.2),*/
            /*0 0 100px 0 rgba(69, 129, 66,0.2);*/
            /*transition: all 1s linear;*/
        }

        .btn>span {
            position: absolute;
        }

        .btn>span:nth-child(1) {
            width: 100%;
            height: 2px;
            background: -webkit-linear-gradient(left, transparent, #458142);
            left: -100%;
            top: 0px;
            animation: line1 1s linear infinite;
        }

        @keyframes line1 {

            50%,
            100% {
                left: 100%;
            }
        }

        .btn>span:nth-child(2) {
            width: 2px;
            height: 100%;
            background: -webkit-linear-gradient(top, transparent, #458142);
            right: 0px;
            top: -100%;
            animation: line2 1s 0.25s linear infinite;
        }

        @keyframes line2 {

            50%,
            100% {
                top: 100%;
            }
        }

        .btn>span:nth-child(3) {
            width: 100%;
            height: 2px;
            background: -webkit-linear-gradient(left, #458142, transparent);
            left: 100%;
            bottom: 0px;
            animation: line3 1s 0.75s linear infinite;
        }

        @keyframes line3 {

            50%,
            100% {
                left: -100%;
            }
        }

        .btn>span:nth-child(4) {
            width: 2px;
            height: 100%;
            background: -webkit-linear-gradient(top, transparent, #458142);
            left: 0px;
            top: 100%;
            animation: line4 1s 1s linear infinite;
        }

        @keyframes line4 {

            50%,
            100% {
                top: -100%;
            }
        }
        @keyframes lds-spinner {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }

        @-webkit-keyframes lds-spinner {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }
        .lds-css {
            position:fixed;
            top:0;
            width: 100%;
            height: 100%;
            background-color: #eeeeee;
            z-index:9999;
            filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.5;

        }

        .lds-spinner {
            position: relative;
        }

        .lds-spinner div {
            left: 150%;
            top: 49%;
            position: absolute;
            -webkit-animation: lds-spinner linear 2.8s infinite;
            animation: lds-spinner linear 2.8s infinite;
            background: rgba(12.376037979306128%,76%,8.546415094339627%,0.852);
            width: 12px;
            height: 38px;
            border-radius: 58%;
            -webkit-transform-origin: 6px 55px;
            transform-origin: 6px 55px;
        }
        .lds-spinner div:nth-child(1) {
            -webkit-transform: rotate(0deg);
            transform: rotate(0deg);
            -webkit-animation-delay: -2.52s;
            animation-delay: -2.52s;
        }
        .lds-spinner div:nth-child(2) {
            -webkit-transform: rotate(36deg);
            transform: rotate(36deg);
            -webkit-animation-delay: -2.24s;
            animation-delay: -2.24s;
        }
        .lds-spinner div:nth-child(3) {
            -webkit-transform: rotate(72deg);
            transform: rotate(72deg);
            -webkit-animation-delay: -1.96s;
            animation-delay: -1.96s;
        }
        .lds-spinner div:nth-child(4) {
            -webkit-transform: rotate(108deg);
            transform: rotate(108deg);
            -webkit-animation-delay: -1.68s;
            animation-delay: -1.68s;
        }
        .lds-spinner div:nth-child(5) {
            -webkit-transform: rotate(144deg);
            transform: rotate(144deg);
            -webkit-animation-delay: -1.4s;
            animation-delay: -1.4s;
        }
        .lds-spinner div:nth-child(6) {
            -webkit-transform: rotate(180deg);
            transform: rotate(180deg);
            -webkit-animation-delay: -1.12s;
            animation-delay: -1.12s;
        }
        .lds-spinner div:nth-child(7) {
            -webkit-transform: rotate(216deg);
            transform: rotate(216deg);
            -webkit-animation-delay: -0.84s;
            animation-delay: -0.84s;
        }
        .lds-spinner div:nth-child(8) {
            -webkit-transform: rotate(252deg);
            transform: rotate(252deg);
            -webkit-animation-delay: -0.56s;
            animation-delay: -0.56s;
        }
        .lds-spinner div:nth-child(9) {
            -webkit-transform: rotate(288deg);
            transform: rotate(288deg);
            -webkit-animation-delay: -0.28s;
            animation-delay: -0.28s;
        }
        .lds-spinner div:nth-child(10) {
            -webkit-transform: rotate(324deg);
            transform: rotate(324deg);
            -webkit-animation-delay: 0s;
            animation-delay: 0s;
        }
        .lds-spinner {
            width: 120px !important;
            height: 120px !important;
            left:40%;
            right:60%;
            top:38%;
            button:62%;
        }
        .select {
            display: inline-block;
            width: 100%;
            position: relative;
            vertical-align: middle;
            padding: 0;
            overflow: hidden;
            background-color: linear-gradient(#141e30, #243b55);
            color: #03e9f4;
            border: 1px solid #03e9f4;
            text-shadow: none;
            border-radius: 4px;
            transition: box-shadow 0.25s ease;
            z-index: 2;
        }

        .select:hover {

            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.15);
        }

        .select:before {
            content: "";
            position: absolute;
            width: 0;
            height: 0;
            border: 10px solid transparent;
            border-top-color: #03e9f4;
            top: 14px;
            right: 10px;
            cursor: pointer;
            z-index: -2;
        }
        .select select {
            cursor: pointer;
            padding: 10px;
            width: 100%;
            border: none;
            background: transparent;
            background-image: none;
            -webkit-appearance: none;
            -moz-appearance: none;
        }

        .select select:focus {
            outline: none;
        }
        select {
            color: #03e9f4;
        }
        select option{
            line-height: 30px;
        }
    </style>
</head>
<script src="./js/axios.min.js"></script>
<body>

<div id="loadDiv" class="lds-css" style="visibility:hidden">
    <div class="lds-spinner"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div></div>
<div class="loginBox">
    <h2>学生选课系统</h2>

        <div class="item">
            <input id="username" type="text" value="admin" required>
            <label for="">用户名</label>
        </div>
        <div class="item">
            <input id="password" type="password" value="123456" required>
            <label for="">密码</label>
        </div>

        <div class="select">
            <select name="cars" id="select">
                <option value="student">学生</option>
                <option value="teacher">老师</option>
                <option value="admin" selected>管理员</option>
            </select>
        </div>

        <button class="btn" onclick="login();">登录
            <span></span>
            <span></span>
            <span></span>
            <span></span>
        </button>

</div>
<script type="text/javascript">
    function loading() {
        document.getElementById("loadDiv").style.visibility="visible";//显示
    }

    function autoCloseLoading(){
        setTimeout(function(){closeLoading()},15000);
    }

    function closeLoading() {
        document.getElementById("loadDiv").style.visibility="hidden";//隐藏
    }

    function login(){

        var username = document.getElementById('username').value
        var password = document.getElementById('password').value
        var selectVal = document.getElementById('select').value

        if(username===''){
            alert('账号不能为空')
            return
        }
        if (password==='')
        {
            alert('密码不能为空')
            return
        }

        loading();
        var url = "http://localhost:8090/LoginServlet?action=Login";
        var httpRequest = new XMLHttpRequest();
        httpRequest.open('POST', url, true);
        httpRequest.setRequestHeader("Content-type", "application/json");
        var obj = {
            "username": username,
            "password": password,
            "userType":selectVal
        };

        httpRequest.send(JSON.stringify(obj));

        // 响应后的回调函数
        httpRequest.onreadystatechange = function (res) {
            let data =  JSON.parse(res.target.response)
            if(data.code == 200){
                console.log("1111")
                closeLoading();
                window.sessionStorage.setItem("token",data.data)
                 location.href="http://localhost:8089/#/"

            }else if(data.code == 401){
                closeLoading();
                alert(data.message)
            }


        };

    }


</script>
</body>
</html>
