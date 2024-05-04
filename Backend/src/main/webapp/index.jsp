<%--
  Created by IntelliJ IDEA.
  User: 32578
  Date: 2024/4/21
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="./css/index.css">
</head>
<script src="./js/vue@3.js"></script>
<script src="./js/element-plus.js"></script>
<script src="./js/axios.min.js"></script>


<body>
<div id="app">
    <el-button type="primary">Primary</el-button>
    <h1>{{message}}</h1>
</div>

<script type="text/javascript">

    //Vue3
    Vue.createApp({
        data() {
            return {
                message: '我是xxx'
            }
        },
        created() {
            axios.get('/CourseSelection/TestServlet?action=Add').then(function (res){
                console.log(res)
            })

        },
    }).use(ElementPlus).mount('#app')


</script>
</body>
</html>
