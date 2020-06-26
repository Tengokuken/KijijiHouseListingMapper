<html>
<head>
    <link rel="stylesheet" href="/index.css">
    <script type = "text/javascript" src="/index.js"></script>
</head>

<body>
<div class="body">
    <div class="container">
        <form action="result" method="GET">
            <div class="input-group">
                <div class="input-title"><p>Please insert the Kijiji link:</p></div>
                <div class="input-box"><input class="input-txtbox" name="seed" type="text" placeholder="url"></div>
            </div>
            <div class="input-group">
                <div class="input-title"><p>Number of postings:</p></div>
                <div class="input-box"><input class="input-txtbox" name="limit" type="text" placeholder="Number of postings"></div>
            </div>
            <div class="input-group">
                <!-- <input type="submit" value="Submit" class="btn" onclick="submitToCrawler()"> -->
                <input type="submit" value="Submit" class="btn">
            </div>
        </form>
    </div>
</div>
</body>
</html>
